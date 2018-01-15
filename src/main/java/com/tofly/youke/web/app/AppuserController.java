package com.tofly.youke.web.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.utils.AppConstant;
import com.tofly.youke.domain.po.Appuser;
import com.tofly.youke.service.app.AppuserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyrics
 * @date 2018-01-09
 */
@Controller
public class AppuserController {
    private static final Logger logger = LoggerFactory.getLogger(AppuserController.class);

    @Autowired
    private AppuserService appuserService;

    /**
     * app端用户登录
     */
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<Appuser> appLogin(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        BackResult<Appuser> result = new BackResult<>();
        Appuser appuser = new Appuser();
        appuser.setPhone(phone);
        appuser.setPassword(password);
        appuser.setUserType(AppConstant.APP_USER_TYPE_STUDENT.value);
//        appuser.setStatus(AppConstant.STATUS_ACTIVE.value);
        Appuser user = appuserService.findAppuser(appuser);

        if (null != user) {
            result.setCode(AppConstant.BACK_APPUSER_NOTEXIST.value);
            return result;
        }

        String code;
        if (password.equals(user.getPassword())) {
            if (AppConstant.STATUS_ACTIVE.value.equals(user.getStatus())) {
                code = AppConstant.BACK_CODE_SUCCESS.value;
            } else {
                code = AppConstant.BACK_APPUSER_LOCKED.value;
            }
        } else {
            code = AppConstant.BACK_APPUSER_PASSWORDERROR.value;
        }

        result.setData(user);
        result.setCode(code);

        return result;
    }

    /**
     * app用户端注册
     */
    @RequestMapping(value = "/appuser/register", method = RequestMethod.POST)
    @ResponseBody
    public BackResult<Boolean> appuserRegister(HttpServletRequest request) {
        String appuser = request.getParameter("appuser");
        String verifycode = request.getParameter("verifycode");

        BackResult<Boolean> result = new BackResult<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Appuser user = mapper.readValue(appuser, Appuser.class);
            if (StringUtils.hasText(user.getPhone()) && StringUtils.hasText(verifycode)) {
                String code = appuserService.checkVerifyCode(user.getPhone(), verifycode);
                if (!code.equals(AppConstant.BACK_CODE_SUCCESS.value)) {
                    result.setCode(code);
                    return result;
                }
            }

            user.setUserType(AppConstant.APP_USER_TYPE_STUDENT.value);
            appuserService.insert(user);
        } catch (IOException e) {
            logger.error("app用户注册失败");
        }

        return null;
    }


    /**
     *
     * @param appuser
     * @return
     */
    @RequestMapping(value = "/appuser/update", method = RequestMethod.POST)
    @ResponseBody
    public BackResult updateAppuser(@RequestParam("appuser") Appuser appuser) {
        BackResult<String> result = new BackResult<>();
        result.setCode(AppConstant.BACK_CODE_FAIL.value);

        if (null == appuser) {
            return result;
        }

        appuserService.updateByPrimaryKeySelective(appuser);
        result.setCode(AppConstant.BACK_CODE_SUCCESS.value);

        return result;
    }

}
