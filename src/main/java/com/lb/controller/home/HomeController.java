package com.lb.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName： HomeController
 * @Description： 前台控制台
 * @Author: 蓝莲花
 * @Date： 2020/4/5 下午4:44
 * @Version： V1.0
 **/
@Controller
@RequestMapping("/home")
public class HomeController {
    //主页
    @RequestMapping("/index")
    public String index() {
        return "home/index";
    }
}
