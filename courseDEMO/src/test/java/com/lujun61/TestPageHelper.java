package com.lujun61;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lujun61.dao.StudentDAO;
import com.lujun61.entity.Student;
import com.lujun61.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testSelectAllLimitForPageHelper() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);


        PageHelper.startPage(3, 4);
        List<Student> studentList = studentDAO.selectAllLimit();
        PageInfo<Student> pageInfo = new PageInfo<>(studentList, 5);
        studentList.forEach(System.out::println);
    }

}
