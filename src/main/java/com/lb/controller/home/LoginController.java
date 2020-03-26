package com.lb.controller.home;

import com.lb.entity.LbUser;
import com.lb.service.LbUserService;
import com.lb.vo.ActiveUser;
import com.lb.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO
 * @createTime 2020年03月25日 17:48:00
 */
@Controller
@RequestMapping("/home")
public class LoginController {
    @Autowired
    private LbUserService lbUserService;

    /**
     * 登录页面
     */
    @RequestMapping("/loginRegiterPage")
    public String loginRegiterPage() {
        return "home/login&regist";
    }

    /**
     * 请求登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody LbUser user, HttpSession session) {
        ResponseResult result = lbUserService.checkUser(user);
        if (result.getCode().equals("202")) {
            session.setAttribute("user",user);
        }
        return result;
    }

    /**
     * 注册用户
     */
    @ResponseBody
    @RequestMapping("/regist")
    public ResponseResult regist(@RequestBody ActiveUser activeUser){
        return lbUserService.registUser(activeUser);
    }
}
