package com.tofly.youke.web.system;

import com.tofly.youke.common.constants.GlobalConstant;
import com.tofly.youke.domain.po.SysUser;
import com.tofly.youke.service.system.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lyrics on 2017-12-20.
 */
@Controller
//@RequestMapping(value = "/sys")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 登录请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码不正确");
        } catch (ExcessiveAttemptsException e) {
            model.addAttribute("message", "用户名或密码错误次数过多");
        } catch (LockedAccountException e) {
            model.addAttribute("message", "账户已锁定");
        } catch (UnknownAccountException e) {
            model.addAttribute("message", "未知账户");
        } catch (AuthenticationException e) {
            model.addAttribute("message", "用户名或密码不正确");
        }


        if (subject.isAuthenticated()) {
            Session session = subject.getSession();
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            SysUser user = userService.findSysUserBySelective(sysUser);
            session.setAttribute(GlobalConstant.CURR_USER_SESSION_KEY, user);

            return "redirect:/";
        }

        token.clear();
        return "login";
    }
}
