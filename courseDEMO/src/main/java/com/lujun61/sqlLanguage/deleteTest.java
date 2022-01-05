package com.lujun61.sqlLanguage;

import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class deleteTest {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis.xml"))
                .openSession(true);

        String sqlId = "com.lujun61.dao.StudentDAO.deleteStudent";
        Student stu = new Student();
        stu.setId(888);
        stu.setAge(100);
        int nums = sqlSession.delete(sqlId, stu);
        //提交事务
        sqlSession.commit();

        System.out.println(nums);
        sqlSession.close();
    }
}