package com.lujun61.proxyDAO;

import com.github.pagehelper.PageHelper;
import com.lujun61.dao.StudentDAO;
import com.lujun61.entity.People;
import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyImpl {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis.xml")).openSession(true);

        StudentDAO dao = sqlSession.getMapper(StudentDAO.class);
        List<Student> students = dao.selectStudent();
        for (Student stu :
             students) {
            System.out.println(stu);
        }

        Student stuUpdate = new Student();
        stuUpdate.setId(1);
        stuUpdate.setAge(50);
        int updateNum = dao.updateStudent(stuUpdate);
        System.out.println(updateNum);

        Student stuDel = new Student();
        stuDel.setId(666);
        stuDel.setAge(50);
        int deleteStudent = dao.deleteStudent(stuDel);
        System.out.println(deleteStudent);

        Student lisi = dao.seleceStudentByAnnotation(2, "李四");
        System.out.println(lisi);


        Student stu = new Student();
        stu.setId(1);
        stu.setName("詹三");
        Student zhansan = dao.seleceStudentByObj(stu);
        System.out.println(zhansan);


        Student lisi2 = dao.seleceStudentByPosit(18, "李四");
        System.out.println(lisi2);


        Map<String,Object> m = new HashMap<>();
        m.put("name", "李四");
        m.put("id", 2);
        Student student = dao.seleceStudentByMap(m);
        System.out.println(student);


        System.out.println(dao.countStudent());


        Map<Object, Object> objMap = dao.selectMap(1);
        System.out.println("map：" + objMap);


        List<People> people1 = dao.selectResultMap();
        for (People p1 :
                people1) {
            System.out.println(p1);
        }

        List<People> people2 = dao.selectResultType();
        for (People p2 :
                people2) {
            System.out.println(p2);
        }


        List<Student> lList1 = dao.likeQueryJava("%陆%");
        for (Student s1 :
                lList1) {
            System.out.println(s1);
        }

        List<Student> lList2 = dao.likeQuerySQL("李");
        for (Student s2 :
                lList2) {
            System.out.println(s2);
        }

        Student stu3 = new Student();
        stu3.setName("李四");
        stu3.setId(1);
        List<Student> stus3 = dao.selectQueryByDynamicSQLIf(stu3);
        for (Student st3 :
                stus3) {
            System.out.println("stu3：" + st3);
        }



        List<Student> stus4 = dao.selectQueryByDynamicSQLWhere(stu3);
        for (Student st4 :
                stus4) {
            System.out.println("stu4：" + st4);
        }


        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Student> listStu1 = dao.selectQueryByDynamicSQLForeach1(list1);
        for (Student lst1 :
                listStu1) {
            System.out.println("lst1：" + lst1);
        }


        List<Student> list2 = new ArrayList<>();
        Student stu4 = new Student();
        //stu4.setName("李四");
        stu4.setId(1);
        list2.add(stu4);
        Student stu5 = new Student();
        //stu5.setName("李四");
        stu5.setId(2);
        list2.add(stu5);
        List<Student> listStu2 = dao.selectQueryByDynamicSQLForeach2(list2);
        for (Student lst2 :
                listStu2) {
            System.out.println("lst2：" + lst2);
        }


        /** 在每次使用查询语句之前（myBatis中就是每次调用执行SQL语句的方法）调用 PageHelper.startPage 静态方法
         *   pageNum：由limit分的第N页
         *   pageSize：每一页想要分多少行数据
         */
        PageHelper.startPage(3, 3);
        System.out.println(45);
        List<Student> limitStu = dao.selectAllLimit();
        for (Student limit :
                limitStu) {
            System.out.println("limit：" + limit);
        }


        sqlSession.close();
    }
}