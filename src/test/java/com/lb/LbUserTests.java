package com.lb;

import com.lb.dao.LbUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;


@SpringBootTest
class LbUserTests {
    @Autowired
    private LbUserDao lbUserDao;

    @Test
    public void userLoads() {
        lbUserDao.all().forEach(System.out::println);
    }
}
