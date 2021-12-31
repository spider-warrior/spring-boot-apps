package com.study.spring.test;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;

/**
 * PropertyDescriptorTest
 *
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-12-31 16:22
 **/
public class PropertyDescriptorTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setId("1");
        student.setName("xiaoming");
        student.setAge(18);
        final BeanWrapper wrapper = new BeanWrapperImpl(student);
        PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds) {
            Object value = wrapper.getPropertyValue(pd.getName());
            System.out.println("property: " + pd.getName() + ", value: " + value);
        }
    }

    private static class Student {
        private String id;
        private String name;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }
}


