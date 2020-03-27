package com.lb.service.impl;

import com.lb.dao.LbPatientDao;
import com.lb.entity.LbPatient;
import com.lb.service.LbPatientService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbPatientServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月27日 13:58:00
 */
@Service
public class LbPatientServiceImpl implements LbPatientService {
    @Autowired
    private LbPatientDao lbPatientDao;
    @Override
    public PageQuery<LbPatient> findList(long pageNo, long pageSize, String name, String certId) {
        PageQuery<LbPatient> query = new PageQuery(pageNo,pageSize);
        if (!StringUtils.isEmpty(name)) {
            query.setPara("name",name);
        }
        if (!StringUtils.isEmpty(certId)) {
            query.setPara("certId",certId);
        }
        lbPatientDao.selectList(query);
        return query;
    }

    @Override
    public ResponseResult insertPatient(LbPatient lbPatient) {
        ResponseResult result = new ResponseResult();
        LambdaQuery<LbPatient> query = lbPatientDao.createLambdaQuery();
        if (!StringUtils.isEmpty(lbPatient.getCertId())) {
            query.andEq(LbPatient::getCertId,lbPatient.getCertId());
        }
        LbPatient sysPatient = query.single();
        if (sysPatient != null) {
            result.setCode("301");
            result.setMessage("该身份证已被注册或使用！");
        } else {
            lbPatientDao.insert(lbPatient);
            result.setCode("302");
            result.setMessage("信息保存成功！");
        }
        return result;
    }

    @Override
    public ResponseResult updatePatient(LbPatient lbPatient) {
        ResponseResult result = new ResponseResult();
        lbPatientDao.updateById(lbPatient);
        result.setCode("302");
        result.setMessage("信息保存成功！");
        return result;
    }

    @Override
    public LbPatient findOne(Integer id) {
        return lbPatientDao.single(id);
    }

    @Override
    public int deleteById(Integer id) {
        return lbPatientDao.deleteById(id);
    }
}
