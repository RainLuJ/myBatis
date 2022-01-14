package com.lujun61;

import com.lujun61.dao.StudentDAO;
import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class Test {
    SqlSession sqlSession;

    {
        try {
            sqlSession = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis.xml")).openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    StudentDAO dao = sqlSession.getMapper(StudentDAO.class);
    @org.junit.Test
    public void test(){
        List<Student> lList2 = dao.likeQuerySQL("陆");
        for (Student s2 :
                lList2) {
            System.out.println(s2);
        }
    }
}
