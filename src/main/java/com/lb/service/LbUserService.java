package com.lb.service;

import com.lb.entity.LbUser;
import com.lb.vo.ResponseResult;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbUserService.java
 * @Description TODO
 * @createTime 2020年03月25日 18:18:00
 */
public interface LbUserService {
    //校验登录
    ResponseResult checkUser(LbUser user);
}
