package com.lb.controller.patient;

import com.lb.entity.LbAppointment;
import com.lb.entity.LbUser;
import com.lb.service.LbAppointmentService;
import com.lb.vo.QueryVo;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName PatientController.java
 * @Description 患者控制器
 * @createTime 2020年03月29日 20:54:00
 */
@Controller
@RequestMapping("/patient")
public class FrontPatientController {
    @Autowired
    private LbAppointmentService lbAppointmentService;

    /**
     * 患者主页面，显示当前患者的预约记录
     */
    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        //查询当前登录用户的预约记录
        LbUser user = (LbUser) session.getAttribute("user");
        QueryVo queryVo = new QueryVo();
        queryVo.setUserId(user.getId());
        PageQuery<LbAppointment> page = lbAppointmentService.findList(queryVo);
        model.addAttribute("page",page);
        return "patient/appointmentHistory";
    }
}
