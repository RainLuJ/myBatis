package com.lujun61.sqlLanguage;

import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class updateTest {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis.xml"))
                .openSession(true);

        String sqlId = "com.lujun61.dao.StudentDAO.insertStudent";
        Student stu = new Student(666, "lujun", "123456", 18);
        int nums = sqlSession.insert(sqlId, stu);
        //提交事务
        sqlSession.commit();

        System.out.println(nums);
        sqlSession.close();
    }
}