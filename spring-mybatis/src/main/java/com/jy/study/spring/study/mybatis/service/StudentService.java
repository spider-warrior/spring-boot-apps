package com.jy.study.spring.study.mybatis.service;

import com.jy.study.spring.study.mybatis.entity.Student;

import java.util.List;

/**
 * @author yj
 * @since 2020-05-18 12:59
 **/
public interface StudentService {
    void save(Student student);
    void saveAll(List<Student> studentList);
}
