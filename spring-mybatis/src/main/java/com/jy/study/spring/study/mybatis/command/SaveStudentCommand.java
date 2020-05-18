package com.jy.study.spring.study.mybatis.command;

import com.jy.study.spring.study.mybatis.entity.Student;
import com.jy.study.spring.study.mybatis.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yj
 * @since 2020-05-18 13:02
 **/
//@Component
public class SaveStudentCommand implements CommandLineRunner {

    private final StudentService studentService;

    @Override
    public void run(String... args) {
        Student student = new Student();
        student.setName("小明");
        student.setBirthday(new Date());
        studentService.save(student);
    }

    public SaveStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }
}
