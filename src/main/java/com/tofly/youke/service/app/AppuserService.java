package com.tofly.youke.service.app;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.common.utils.AppConstant;
import com.tofly.youke.domain.po.Appuser;
import com.tofly.youke.persistence.app.AppuserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lyrics
 * @date 2018-01-09
 */
@Service
public class AppuserService extends AbstractService<Appuser> {

    @Autowired
    private AppuserMapper appuserMapper;

    @Autowired
    private SmsService smsService;

    @Override
    protected IMapper<Appuser> getMapper() {
        return appuserMapper;
    }

    public Appuser findAppuser(Appuser appuser) {
        return appuserMapper.findAppuser(appuser);
    }

    public String checkVerifyCode(String phone, String verifyCode) {
        Map<String, Integer> verifyCodeMap = SmsService.getVerifyCodeMap();
        String code;
        if (null == verifyCodeMap) {
            code = AppConstant.BACK_APPUSER_CHECKEVERIFYRROR.value;
        } else if (null == verifyCodeMap.get(phone)) {
            code = AppConstant.BACK_APPUSER_VERIFYCODETIMEOUT.value;
        } else {
            if (verifyCode.equals(String.valueOf(verifyCodeMap.get(phone)))) {
                code = AppConstant.BACK_CODE_SUCCESS.value;
                verifyCodeMap.remove(phone);
                smsService.removeTask(phone);
            } else {
                code = AppConstant.BACK_APPUSER_CHECKEVERIFYRROR.value;
            }
        }

        return code;
    }
}
