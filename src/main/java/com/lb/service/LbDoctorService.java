package com.lb.service;

import com.lb.entity.LbDoctor;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbDoctorService.java
 * @Description TODO
 * @createTime 2020年03月26日 13:59:00
 */
public interface LbDoctorService {
    //查医生集合
    PageQuery<LbDoctor> findList(Integer pageNo, Integer pageSize,String name,String certId);

    /**
     * 保存
     */
    ResponseResult saveDoctor(LbDoctor lbDoctor);

    /**
     * 根据主键id查询医生
     */
    LbDoctor findOne(Integer id);

    /**
     * 删除医生
     */
    int deleteDoctory(Integer id);
}
