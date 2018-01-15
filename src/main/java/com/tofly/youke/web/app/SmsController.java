package com.tofly.youke.web.app;

import com.tofly.youke.common.constants.GlobalConstant;
import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.utils.AppConstant;
import com.tofly.youke.domain.po.Appuser;
import com.tofly.youke.service.app.AppuserService;
import com.tofly.youke.service.app.SmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信网关接口
 *
 * @author lyrics
 * @date 2018-01-10
 */
@Controller
public class SmsController {
    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private AppuserService appuserService;

    @Autowired
    private SmsService smsService;

    /**
     * 发送验证码
     */
    @RequestMapping(value = "/send/verifyCode", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<Boolean> sendVerifyCode(@RequestParam("phone") String phone,
                                              @RequestParam("source") String source,
                                              @RequestParam("userType") String userType) {

        BackResult<Boolean> result = new BackResult<>();

        Appuser appuser = new Appuser();
        appuser.setUserType(userType);
        appuser.setPhone(phone);

        Appuser user = appuserService.findAppuser(appuser);

        boolean status = false;
        if (null != user) {
            if (!AppConstant.STATUS_ACTIVE.value.equals(user.getStatus())) {
                result.setCode(AppConstant.BACK_APPUSER_LOCKED.value);
                return result;
            }

            if (GlobalConstant.REGISTER_STATUS.equals(source)) {
                result.setCode(AppConstant.BACK_APPUSER_APPUSEREXIST.value);
                return result;
            }
        } else {
            if (GlobalConstant.REGISTER_STATUS.equals(source)) {
                try {
                    status = smsService.transaProcessVerifyCode(phone, source, GlobalConstant.VERIFY_CODE);
                } catch (Exception e) {
                    logger.error("");
                }
            }
        }

        result.setData(status);
        result.setCode(AppConstant.BACK_CODE_SUCCESS.value);
        return result;
    }

}
