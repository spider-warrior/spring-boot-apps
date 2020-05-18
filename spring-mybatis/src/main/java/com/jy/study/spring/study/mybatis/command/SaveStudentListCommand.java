package com.jy.study.spring.study.mybatis.command;

import com.jy.study.spring.study.mybatis.entity.Student;
import com.jy.study.spring.study.mybatis.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yj
 * @since 2020-05-18 13:02
 **/
@Component
public class SaveStudentListCommand implements CommandLineRunner {

    private final StudentService studentService;

    @Override
    public void run(String... args) {
        for(int i=0; i<3; i++) {
            List<Student> studentList = new ArrayList<>();
            for(int j=0; j<5; j++) {
                Student student = new Student();
                student.setName("小明");
                student.setBirthday(new Date());
                studentList.add(student);
            }
            studentService.saveAll(studentList);
        }
    }

    public SaveStudentListCommand(StudentService studentService) {
        this.studentService = studentService;
    }
}
