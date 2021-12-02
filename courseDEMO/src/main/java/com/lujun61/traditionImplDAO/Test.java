package com.lujun61.traditionImplDAO;

import com.lujun61.entity.Student;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentDAOImpl dl = new StudentDAOImpl();
        List<Student> students = dl.selectStudent();

        for (Student stu :
                students) {
            System.out.println(stu);
        }

        System.out.println("--------------------------------");

        //System.out.println(dl.insertStudent(new Student(888, "lujun", "123456", 18)));

        System.out.println("--------------------------------");
        System.out.println(dl.selectStudentById(1));
    }
}
