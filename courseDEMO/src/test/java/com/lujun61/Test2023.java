package com.lujun61;

import com.lujun61.dao.StudentDAO;
import com.lujun61.entity.Student;
import com.lujun61.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test2023 {


    @Test
    public void testInsertCarUseGeneratedKeys() {
        try {

            String path = "mybatis.xml";
            InputStream resource = Resources.getResourceAsStream(path);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
            Student student = new Student();
            student.setName("2023测试insert中的标签属性age");
            student.setAge(66);
            student.setEmail("lsajdflaage");
            int incId = studentDAO.insertCarUseGeneratedKeys(student);
            System.out.println("自增后的id：" + incId);
            System.out.println(student);
            List<Student> students = studentDAO.selectStudent();
            for (Student student1 : students) {
                System.out.println(student1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Test
    public void testLikeQuerySQL() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
        List<Student> res = studentDAO.likeQuerySQL("陆");
        res.forEach(System.out::println);
    }

    @Test
    public void testSelectAllRetMap() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
        Map<Long, Map<String, Object>> map = studentDAO.selectAllRetMap();
        Set<Map.Entry<Long, Map<String, Object>>> entrySet = map.entrySet();
        for (Map.Entry<Long, Map<String, Object>> longMapEntry : entrySet) {
            System.out.println(longMapEntry.getKey());
            System.out.println("\r\t");
            Map<String, Object> childMap = longMapEntry.getValue();
            Set<Map.Entry<String, Object>> childEntrySet = childMap.entrySet();
            for (Map.Entry<String, Object> stringObjectEntry : childEntrySet) {
                System.out.println(stringObjectEntry.getKey() + " : " + stringObjectEntry.getValue());
            }
        }
    }

}
