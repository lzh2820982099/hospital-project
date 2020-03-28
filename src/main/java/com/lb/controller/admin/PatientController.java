package com.lb.controller.admin;

import com.lb.entity.LbPatient;
import com.lb.service.LbPatientService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName PatientController.java
 * @Description 患者后台控制器
 * @createTime 2020年03月27日 13:48:00
 */
@Controller
@RequestMapping("/admin/patient")
public class PatientController {
    @Autowired
    private LbPatientService lbPatientService;

    @RequestMapping("/manage")
    public String manage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) String certId,
                         Model model) {
        //查询医生的集合数据
        PageQuery<LbPatient> page = lbPatientService.findList(pageNo,pageSize,name,certId);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("name",name);
        model.addAttribute("certId",certId);
        return "admin/patientManage";
    }

    /**
     * 病人新增
     */
    @RequestMapping("/")
    public String doctorAddForm(LbPatient lbPatient,Model model) {
        model.addAttribute("patient",lbPatient);
        return "admin/patientForm";
    }

    /**
     * 病人编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String doctorEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("patient",lbPatientService.findOne(id));
        return "admin/patientForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbPatient lbPatient) {
        return lbPatientService.insertPatient(lbPatient);
    }

    /**
     * 异步更新记录
     * @param lbPatient
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbPatient lbPatient) {
        return lbPatientService.updatePatient(lbPatient);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbPatientService.deleteById(id);
    }
}
