package com.jy.study.spring.study.mybatis.mapper;

import com.jy.study.spring.study.mybatis.entity.Student;

import java.util.List;

/**
 * @author yj
 * @since 2020-05-18 12:58
 **/
public interface StudentMapper {
    void insert(Student student);
    void insertBatch(List<Student> studentList);
}
