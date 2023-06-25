package com.lujun61.traditionImplDAO;

import com.lujun61.dao.StudentDAO;
import com.lujun61.entity.People;
import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StudentDAOImpl implements StudentDAO {
    private static SqlSessionFactory factory;
    static {
        try {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> selectStudent() {
        SqlSession sqlSession = factory.openSession(true);
        String sqlId = "com.lujun61.dao.StudentDAO.selectStudent";
        List<Student> studentList = sqlSession.selectList(sqlId);

        sqlSession.close();
        return studentList;
    }

    @Override
    public int insertStudent(Student stu) {
        SqlSession sqlSession = factory.openSession(true);
        String sqlId = "com.lujun61.dao.StudentDAO.insertStudent";
        int nums = sqlSession.insert(sqlId, stu);
        //提交事务
        sqlSession.commit();

        sqlSession.close();
        return nums;
    }

    @Override
    public int updateStudent(Student stu) {
        return 0;
    }

    @Override
    public int deleteStudent(Student stu) {
        return 0;
    }

    @Override
    public Student selectStudentById(Integer id) {
        SqlSession sqlSession = factory.openSession(true);
        String sqlId = "com.lujun61.dao.StudentDAO.selectStudentById";

        Student stu = sqlSession.selectOne(sqlId, id);

        sqlSession.close();
        return stu;
    }

    @Override
    public Student seleceStudentByPosit(Integer age, String name) {
        return null;
    }

    @Override
    public Student seleceStudentByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public Integer countStudent() {
        return 0;
    }

    @Override
    public Map<Object, Object> selectMap(Integer id) {
        return null;
    }

    @Override
    public Map<Long, Map<String, Object>> selectAllRetMap() {
        return null;
    }

    @Override
    public List<People> selectResultMap() {
        return null;
    }

    @Override
    public List<Student> likeQueryJava(String name) {
        return null;
    }

    @Override
    public List<Student> selectQueryByDynamicSQLIf(Student stu) {
        return null;
    }

    @Override
    public List<Student> selectQueryByDynamicSQLForeach1(List<Integer> list) {
        return null;
    }

    @Override
    public List<Student> selectAllLimit() {
        return null;
    }

    @Override
    public int insertCarUseGeneratedKeys(Student stu) {
        return 0;
    }

    @Override
    public List<Student> selectQueryByDynamicSQLForeach2(List<Student> list) {
        return null;
    }

    @Override
    public List<Student> selectQueryByDynamicSQLWhere(Student stu) {
        return null;
    }

    @Override
    public List<Student> likeQuerySQL(String name) {
        return null;
    }

    @Override
    public List<People> selectResultType() {
        return null;
    }

    @Override
    public Student seleceStudentByObj(Student stu) {
        return null;
    }

    @Override
    public Student seleceStudentByAnnotation(Integer id, String name) {
        return null;
    }

    @Override
    public Student selectStudentUseObjAndNormalVar(Student stu, String id) {
        return null;
    }
}
