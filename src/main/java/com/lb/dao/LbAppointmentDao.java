package com.lb.dao;
import com.lb.entity.LbAppointment;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface LbAppointmentDao extends BaseMapper<LbAppointment> {
    //查询患者的预约信息关系查询对应的医生
    void selectList(PageQuery<LbAppointment> page);

    //根据主键查询
    LbAppointment selectOne(Integer id);
}