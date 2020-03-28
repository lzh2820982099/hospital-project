package com.lb.service.impl;

import com.lb.common.Global;
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
            result.setCode(Global.SAVE_CODE_ERROR);
            result.setMessage(Global.SAVE_MSG_ERROR);
        } else {
            lbPatientDao.insert(lbPatient);
            result.setCode(Global.SAVE_CODE_SUCCESS);
            result.setMessage(Global.SAVE_MSG_SUCCESS);
        }
        return result;
    }

    @Override
    public ResponseResult updatePatient(LbPatient lbPatient) {
        ResponseResult result = new ResponseResult();
        lbPatientDao.updateById(lbPatient);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbPatient findOne(Integer id) {
        return lbPatientDao.single(id);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        ResponseResult result = new ResponseResult();

        int rows = lbPatientDao.deleteById(id);
        if (rows > 0) {
            result.setCode(Global.DEL_CODE_SUCCESS);
            result.setMessage(Global.DEL_MSG_SUCCESS);
        } else {
            result.setCode(Global.DEL_CODE_ERROR);
            result.setMessage(Global.DEL_MSG_ERROR);
        }
        return result;
    }
}
