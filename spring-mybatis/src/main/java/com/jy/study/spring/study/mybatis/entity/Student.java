package com.jy.study.spring.study.mybatis.entity;

import java.util.Date;

/**
 * @author yj
 * @since 2020-05-18 12:46
 **/
public class Student {

    private Long id;
    private String name;
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
