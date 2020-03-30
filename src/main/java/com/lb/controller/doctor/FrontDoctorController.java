package com.lb.controller.doctor;

import com.lb.entity.LbDoctor;
import com.lb.service.LbDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 根据部门查询医生
     */
    @ResponseBody
    @RequestMapping(value = "/getList/{department}")
    public List<LbDoctor> getList(@PathVariable String department){
        return lbDoctorService.getListByDepartment(department);
    }
}
