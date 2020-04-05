package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbOptionDao;
import com.lb.dao.LbSeekDao;
import com.lb.entity.LbSeek;
import com.lb.service.LbSeekService;
import com.lb.utils.DrugsUtils;
import com.lb.utils.OptionUtils;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName： LbSeekServiceImpl
 * @Description：
 * @Author: 蓝莲花
 * @Date： 2020/4/5 下午1:48
 * @Version： V1.0
 **/
@Service
public class LbSeekServiceImpl implements LbSeekService {
    @Autowired
    private LbSeekDao lbSeekDao;
    @Autowired
    private LbOptionDao lbOptionDao;

    @Override
    public ResponseResult save(Map map, HttpSession session) {
        LbSeek seek = new LbSeek();
        seek.setPatientId(Integer.valueOf(String.valueOf(map.get("patientId"))));
        seek.setDays(Integer.valueOf(String.valueOf(map.get("days"))));
        seek.setDescribes(String.valueOf(map.get("describes")));
        seek.setIllname(String.valueOf(map.get("illname")));
        seek.setOptions(OptionUtils.getOptionIds(map));

        //根据检查项，计算出所需的费用
        seek.setPrice(lbOptionDao.getTotalPrice(OptionUtils.getOptionIds(seek.getOptions())));
        KeyHolder keyHolder = lbSeekDao.insertReturnKey(seek);
        //将新记录的id存储到session中
        session.setAttribute("seek_" + map.get("patientId"),keyHolder.getInt());
        return new ResponseResult(Global.SAVE_CODE_SUCCESS,Global.SAVE_MSG_SUCCESS);
    }

    @Override
    public ResponseResult update(Map map,HttpSession session) {
        Integer seekId = (Integer) session.getAttribute("seek_" + map.get("patientId"));
        LbSeek seek = new LbSeek();
        seek.setId(seekId);
        seek.setDrugs(DrugsUtils.getDrugsInfo(map));
        lbSeekDao.upsertByTemplate(seek);
        return new ResponseResult(Global.SAVE_CODE_SUCCESS,Global.SAVE_MSG_SUCCESS);
    }

    @Override
    public LbSeek findOneByPatientId(Integer patientId,HttpSession session) {
        Integer seekId = (Integer) session.getAttribute("seek_" + patientId);
        return lbSeekDao.single(seekId);
    }
}
