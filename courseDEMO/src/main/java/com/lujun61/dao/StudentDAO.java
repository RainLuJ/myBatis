package com.lujun61.dao;


import com.lujun61.entity.People;
import com.lujun61.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDAO {
    //查询Student表中的所有数据
    List<Student> selectStudent();

    //插入学生信息
    int insertStudent(Student stu);

    //更新学生信息
    int updateStudent(Student stu);

    //删除信息
    int deleteStudent(Student stu);

    //根据学生id查询Student表中的对应学生
    Student selectStudentById(Integer id);



    /* =============================多个参数查询================================ */

    //使用@param注解，传入多个参数
    Student seleceStudentByAnnotation(@Param("annotateId") Integer id,
                                      @Param("annotateName") String name);

    //使用java对象，传入多个参数
    Student seleceStudentByObj(Student stu);

    //按位置传参（形参中的行顺序就是sql语句中的0、1……）
    Student seleceStudentByPosit(Integer id, String name);

    //按map传参
    Student seleceStudentByMap(Map<String, Object> map);


    /* =============================查询后单个结果================================ */
    //在sql语句中通过 count(*)函数 查询学生总数
    Integer countStudent();


    /* =============================查询返回Map================================ */
    Map<Object, Object> selectMap(Integer id);



    /* ====================列名与属性名不一致时的解决方案========================== */
    //使用resultMap
    List<People> selectResultMap();

    //使用resultType
    List<People> selectResultType();



    /* =============================模糊查询================================ */
    //第一种方案：在java代码中写好 'like' 的内容，传入到SQL语句中
    //推荐使用：更方便、灵活
    List<Student> likeQueryJava(@Param("name") String name);

    //第二种方案：name就是某个字段，（在mapper文件中）由myBatis拼接 【like "%" 字段 "%"】
    //不灵活
    List<Student> likeQuerySQL(@Param("name") String name);



    /* =============================动态SQL================================ */
    //<if标签>
    List<Student> selectQueryByDynamicSQLIf(Student stu);

    //<where标签>
    List<Student> selectQueryByDynamicSQLWhere(Student stu);

    //<foreach>标签
    //List集合中存放的是简单的数据类型
    List<Student> selectQueryByDynamicSQLForeach1(List<Integer> list);

    //<foreach>标签
    //List集合中存放的是自定义的数据类型
    List<Student> selectQueryByDynamicSQLForeach2(List<Student> list);



    /* =============================limit分页查询================================ */
    List<Student> selectAllLimit();
}