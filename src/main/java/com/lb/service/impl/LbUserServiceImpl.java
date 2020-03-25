package com.lb.service.impl;

import com.lb.dao.LbUserDao;
import com.lb.entity.LbUser;
import com.lb.service.LbUserService;
import com.lb.vo.ResponseResult;
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
}
