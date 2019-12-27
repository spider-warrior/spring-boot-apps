package com.study.spring.service.impl;

import com.study.spring.anno.InjectionByName;
import com.study.spring.dao.TestDao;
import com.study.spring.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TestServiceImpl implements TestService, ApplicationContextAware {

    @Autowired
    private TestDao testDao;

    @InjectionByName("testDaoImpl")
    private TestDao anotherDao;

    @Override
    public String query() {
        return "123";
    }

    public TestServiceImpl() {
        System.out.println("TestServiceImpl 实例化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware#setApplicationContext() running");
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public void setTestDao(TestDao testDao) {
        System.out.println("TestServiceImpl#setTestDao() running");
        this.testDao = testDao;
    }

    public TestDao getAnotherDao() {
        return anotherDao;
    }

    public void setAnotherDao(TestDao anotherDao) {
        this.anotherDao = anotherDao;
    }
}
