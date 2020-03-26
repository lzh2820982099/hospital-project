package com.lb.controller.admin;

import com.lb.entity.LbDoctor;
import com.lb.service.LbDoctorService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName DoctorController.java
 * @Description 医生的后台控制台
 * @createTime 2020年03月26日 13:57:00
 */
@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {
    @Autowired
    private LbDoctorService lbDoctorService;

    @RequestMapping("/manage")
    public String doctorManage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String certId,
                               Model model) {
        //查询医生的集合数据
        PageQuery<LbDoctor> page = lbDoctorService.findList(pageNo,pageSize,name,certId);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("name",name);
        model.addAttribute("certId",certId);
        return "admin/doctorManage";
    }

    /**
     * 医生表单
     */
    @RequestMapping("/form")
    public String doctorForm(LbDoctor lbDoctor,Model model) {
        if (lbDoctor.getId() != null) {
            lbDoctor = lbDoctorService.findOne(lbDoctor.getId());
        }
        model.addAttribute("doctor",lbDoctor);
        return "admin/doctorForm";
    }

    /**
     * 异步保存
     */
    @ResponseBody
    @RequestMapping("/save")
    public ResponseResult save(@RequestBody LbDoctor lbDoctor) {
        return lbDoctorService.saveDoctor(lbDoctor);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ResponseResult delete(Integer id){
        int rows = lbDoctorService.deleteDoctory(id);
        ResponseResult result = new ResponseResult();
        if (rows > 0) {
            result.setCode("401");
            result.setMessage("删除成功");
        } else {
            result.setCode("402");
            result.setMessage("删除失败");
        }
        return result;
    }
}
