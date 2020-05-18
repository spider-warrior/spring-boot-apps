package com.jy.study.spring.study.mybatis.service.impl;

import com.jy.study.spring.study.mybatis.entity.Student;
import com.jy.study.spring.study.mybatis.mapper.StudentMapper;
import com.jy.study.spring.study.mybatis.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yj
 * @since 2020-05-18 13:00
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Override
    public void save(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void saveAll(List<Student> studentList) {
        studentMapper.insertBatch(studentList);
    }


    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
