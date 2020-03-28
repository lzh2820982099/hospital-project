package com.lb.service;

import com.lb.entity.LbPatient;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbPatientService.java
 * @Description TODO
 * @createTime 2020年03月27日 13:59:00
 */
public interface LbPatientService {
    //查医生集合
    PageQuery<LbPatient> findList(long pageNo, long pageSize, String name, String certId);

    /**
     * 保存
     */
    ResponseResult insertPatient(LbPatient lbPatient);

    /**
     * 更新记录
     */
    ResponseResult updatePatient(LbPatient lbPatient);

    /**
     * 根据主键id查询医生
     */
    LbPatient findOne(Integer id);

    /**
     * 删除医生
     */
    ResponseResult deleteById(Integer id);
}
