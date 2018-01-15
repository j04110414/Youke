package com.tofly.youke.service.app;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tofly.youke.common.constants.GlobalConstant;
import com.tofly.youke.common.utils.DataConfig;
import com.tofly.youke.common.utils.VerfyCodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lyrics
 * @date 2018-01-11
 */
@Service
public class SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    /**
     * 验证码超时时间设置,单位：毫秒
     */
    private final int TIMEOUT = 5 * 60 * 1000;
    /**
     * 生成的验证码存放在此处，超时后从此处删除
     */
    private static Map<String, Integer> verifyCodeMap = new ConcurrentHashMap<>();
    /**
     * 验证码超时任务存放在此处，超时任务执行后从此处删除
     */
    private static Map<String, VerifyCodeTask> taskCenterMap = new ConcurrentHashMap<>();

    public boolean transaProcessVerifyCode(String phone, String source, String type) throws Exception {
        if (GlobalConstant.VERIFY_CODE.equals(type)) {
            if (verifyCodeMap.get(phone) != null) {
                verifyCodeMap.remove(phone);
                removeTask(phone);
            }

            int verifyCode = Integer.valueOf(VerfyCodeUtil.random(VerfyCodeUtil.DIGIT, 6));
            verifyCodeMap.put(phone, verifyCode);
            VerifyCodeTask verifyCodeTask = new VerifyCodeTask(phone);
            taskCenterMap.put(phone, verifyCodeTask);
            verifyCodeTask.runTask();

            return sendMessage(phone, String.valueOf(verifyCode));
        } else {
            throw new Exception("目前仅处理验证码问题");
        }
    }

    private boolean sendMessage(String phone, String verifyCode) {
        final String product = DataConfig.get("product");
        final String domain = DataConfig.get("domain");
        final String regionId = DataConfig.get("regionId");

        final String accessKeyId = DataConfig.get("accessKeyId");
        final String accessKeySecret = DataConfig.get("accessKeySecret");

        String signName = DataConfig.get("sign.name");
        String templateCode = DataConfig.get("verify.template.code");

        try {
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint(regionId, regionId, product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setSignName(signName);
            request.setPhoneNumbers(phone);
            request.setTemplateCode(templateCode);
            request.setTemplateParam("{ \"code\":\"" + verifyCode + "\"}");

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && GlobalConstant.OK.equals(sendSmsResponse.getCode())) {
                logger.info("短信发送成功");
                return true;
            } else {
                logger.error("短信发送失败，错误码：" + sendSmsResponse.getCode());
                return false;
            }
        } catch (ClientException e) {
            logger.error("短信发送异常", e);
        }
        return false;
    }


    public void removeTask(String phone) {
        VerifyCodeTask oldTask = taskCenterMap.get(phone);
        oldTask.cancel();
    }

    public static Map<String, Integer> getVerifyCodeMap() {
        return verifyCodeMap;
    }

    class VerifyCodeTask {
        private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);
        private String phone;

        public VerifyCodeTask(String phone) {
            this.phone = phone;
        }

        public void runTask() {
            executor.schedule(() -> {
                verifyCodeMap.remove(phone);
                taskCenterMap.remove(phone);
                executor.shutdown();
            }, TIMEOUT, TimeUnit.SECONDS);
        }

        public void cancel() {
            executor.shutdown();
        }
    }

}

