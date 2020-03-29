package com.lb.service;

import com.lb.entity.LbPatient;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbPatientService.java
 * @Description TODO
 * @createTime 2020年03月27日 13:59:00
 */
public interface LbPatientService {
    //查集合
    PageQuery<LbPatient> findList(long pageNo, long pageSize, String name, String certId);

    /**
     * 查询所有病人
     */
    List<LbPatient> findAll();

    /**
     * 保存
     */
    ResponseResult insertPatient(LbPatient lbPatient);

    /**
     * 更新记录
     */
    ResponseResult updatePatient(LbPatient lbPatient);

    /**
     * 根据主键id查询
     */
    LbPatient findOne(Integer id);

    /**
     * 删除
     */
    ResponseResult deleteById(Integer id);
}
