package com.lb.controller.doctor;

import com.lb.entity.LbAppointment;
import com.lb.entity.LbDoctor;
import com.lb.entity.LbPatient;
import com.lb.entity.LbUser;
import com.lb.service.LbAppointmentService;
import com.lb.service.LbDoctorService;
import com.lb.service.LbPatientService;
import com.lb.vo.QueryVo;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName DoctorController.java
 * @Description 医生管理端控制台
 * @createTime 2020年03月26日 13:57:00
 */
@Controller
@RequestMapping("/doctor")
public class FrontDoctorController {
    @Autowired
    private LbDoctorService lbDoctorService;
    @Autowired
    private LbPatientService lbPatientService;
    @Autowired
    private LbAppointmentService lbAppointmentService;

    /**
     * 医生控制台
     */
    @RequestMapping("/index")
    public String index(QueryVo queryVo, HttpSession session, Model model){
        LbUser user = (LbUser) session.getAttribute("user");
        queryVo.setUserId(user.getId());

        //获取患者信息
        PageQuery<LbAppointment> page = lbAppointmentService.findListByDoctor(queryVo);
        model.addAttribute("page", page);
        return "doctor/index";
    }

    /**
     * 根据部门查询医生
     */
    @ResponseBody
    @RequestMapping(value = "/getList/{department}")
    public List<LbDoctor> getList(@PathVariable String department){
        return lbDoctorService.getListByDepartment(department);
    }
}
