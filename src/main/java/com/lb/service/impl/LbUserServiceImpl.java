package com.lb.service.impl;

import com.lb.dao.LbDoctorDao;
import com.lb.dao.LbPatientDao;
import com.lb.dao.LbUserDao;
import com.lb.entity.LbDoctor;
import com.lb.entity.LbPatient;
import com.lb.entity.LbUser;
import com.lb.service.LbUserService;
import com.lb.vo.ActiveUser;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbUserServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月25日 18:20:00
 */
@Service
public class LbUserServiceImpl implements LbUserService {
    @Autowired
    private LbUserDao lbUserDao;
    @Autowired
    private LbDoctorDao lbDoctorDao;
    @Autowired
    private LbPatientDao lbPatientDao;

    @Override
    public ResponseResult checkUser(LbUser user) {
        //从数据库中查询用户
        ResponseResult result = new ResponseResult();
        LbUser sysUser = lbUserDao.findUserByUsername(user.getUsername());
        if (sysUser == null) {
            result.setCode("201");//用户不存在
            result.setMessage("用户名或密码错误");
        } else {
            //校验密码
            if (sysUser.getPassword().equals(user.getPassword())) {
                result.setCode("202");
                result.setMessage(String.valueOf(sysUser.getRole()));//绑定登录角色
            } else {
                result.setCode("203");//密码错误
                result.setMessage("用户名或密码错误");
            }
        }
        return result;
    }

    @Override
    public ResponseResult registUser(ActiveUser activeUser) {
        ResponseResult result = new ResponseResult();
        //查询用户（数据库）
        LbUser sysUser = lbUserDao.findUserByUsername(activeUser.getUsername());

        //查询医生
        Query<LbDoctor> doctorQuery = lbDoctorDao.createQuery();
        doctorQuery.andEq("cert_id",activeUser.getCertId());
        LbDoctor lbDoctor = doctorQuery.single();

        //查询病人
        LambdaQuery<LbPatient> lambdaQuery = lbPatientDao.createLambdaQuery();
        lambdaQuery.andEq(LbPatient::getCertId,activeUser.getCertId());
        LbPatient lbPatient = lambdaQuery.single();

        if (sysUser != null) {//用户已经存在
            result.setCode("101");
            result.setMessage("用户已存在！");
        } else if (sysUser == null && activeUser.getCertId().isEmpty()) {//注册成管理员
            sysUser = new LbUser();
            sysUser.setRole(1);//管理员
            sysUser.setUsername(activeUser.getUsername());
            sysUser.setPassword(activeUser.getPassword());
            lbUserDao.insert(sysUser);
            result.setCode("102");
            result.setMessage("管理员账号注册成功！");
        } else if (lbDoctor != null) {//注册成医生
            if (lbDoctor.getUserId() == null) {
                sysUser = new LbUser();
                sysUser.setRole(2);
                sysUser.setUsername(activeUser.getUsername());
                sysUser.setPassword(activeUser.getPassword());
                lbUserDao.insert(sysUser);
                //更新医生基本信息表
                lbDoctor.setUserId(lbUserDao.findUserByUsername(activeUser.getUsername()).getId());
                lbDoctorDao.updateById(lbDoctor);
                result.setCode("103");
                result.setMessage("医生账号注册成功！");
            } else {
                result.setCode("105");
                result.setMessage("身份证被占用！");
            }
        } else if (lbPatient != null) { //注册成病人
            if (lbPatient.getUserId() == null) {
                sysUser = new LbUser();
                sysUser.setRole(3);
                sysUser.setUsername(activeUser.getUsername());
                sysUser.setPassword(activeUser.getPassword());
                lbUserDao.insert(sysUser);
                lbPatient.setUserId(lbUserDao.findUserByUsername(activeUser.getUsername()).getId());
                lbPatientDao.updateById(lbPatient);
                result.setCode("104");
                result.setMessage("患者账号注册成功！");
            } else {
                result.setCode("105");
                result.setMessage("身份证被占用！");
            }
        }
        return result;
    }
}
