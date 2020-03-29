package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbMedicalHistoryDao;
import com.lb.entity.LbMedicalHistory;
import com.lb.service.LbMedicalHistoryService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbMedicalHistoryServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月27日 13:58:00
 */
@Service
public class LbMedicalHistoryServiceImpl implements LbMedicalHistoryService {
    @Autowired
    private LbMedicalHistoryDao lbMedicalHistoryDao;
    @Override
    public PageQuery<LbMedicalHistory> findList(long pageNo, long pageSize, String patientName, String doctorName) {
        PageQuery<LbMedicalHistory> query = new PageQuery(pageNo,pageSize);
        if (!StringUtils.isEmpty(patientName)) {
            query.setPara("patientName",patientName);
        }
        if (!StringUtils.isEmpty(doctorName)) {
            query.setPara("doctorName",doctorName);
        }
        query.setOrderBy("m.id desc");
        lbMedicalHistoryDao.selectList(query);
        return query;
    }

    @Override
    public ResponseResult insertMedicalHistory(LbMedicalHistory lbMedicalHistory) {
        ResponseResult result = new ResponseResult();
        lbMedicalHistoryDao.insert(lbMedicalHistory);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public ResponseResult updateMedicalHistory(LbMedicalHistory lbMedicalHistory) {
        ResponseResult result = new ResponseResult();
        lbMedicalHistoryDao.updateById(lbMedicalHistory);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbMedicalHistory findOne(Integer id) {
        return lbMedicalHistoryDao.single(id);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        ResponseResult result = new ResponseResult();

        int rows = lbMedicalHistoryDao.deleteById(id);
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
