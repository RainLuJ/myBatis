package com.lujun61.dao;


import com.lujun61.entity.People;
import com.lujun61.entity.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * https://www.cnblogs.com/xjs1874704478/p/11841700.html
 */
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

    // 当参数中既有封装的对象参数，又有单个普通类型的参数时：用Param注解，也可以解决问题。
    /**
     * @return com.lujun61.entity.Student
     * @description 使用@Param注解，mybatis框架会自动创建一个Map集合。并且Map集合是以这种方式存储参数的：
     *              map.put("stu", student);
     *              map.put("id", id);
     *              map.put("param1", student);
     *              map.put("param2", id);
     *              注意：加了@Param注解之后，map集合一方面会按照【注解中的value值-所传实际参数】形式存放参数
     *                                      另一方面：会维护一个param的prefix key
     *                                      只不过，不再维护prefix 为 arg 的key了！
     * @author Jun Lu
     * @date 2022-10-10 10:44:45
     */
    Student selectStudentUseObjAndNormalVar(@Param("stu") Student student, @Param("id") String id);

    //使用java对象，传入多个参数
    Student seleceStudentByObj(Student stu);

    /**
     * @return com.lujun61.entity.Student
     * @description 不使用@Param注解，mybatis框架会自动创建一个Map集合。并且Map集合是以这种方式存储参数的：
     *              map.put("arg0", name);
     *              map.put("arg1", sex);
     *              map.put("param1", name);
     *              map.put("param2", sex);
     *              注意：这个map集合就是像这样创建的。不是arg、param分开创建的。
     * @author Jun Lu
     * @date 2022-10-10 10:44:45
     */
    //按位置传参（形参中的行顺序就是sql语句中的0、1……）
    Student seleceStudentByPosit(Integer id, String name);

    //按map传参
    Student seleceStudentByMap(Map<String, Object> map);


    /* =============================查询后单个结果================================ */
    //在sql语句中通过 count(*)函数 查询学生总数
    Integer countStudent();


    /* =============================查询返回Map================================ */
    // @MapKey("id")
    Map<Object, Object> selectMap(Integer id);


    /**
     * 查询所有的Car，返回一个‘大Map集合’。
     * 【Map集合的key是每条记录的主键值，Map集合的value是每条记录。】
     * {
     *      160 = {car_num=3333, id=160, guide_price=32.00, produce_time=2000-10-10, brand=奔驰E300L, car_type=新能源},
     *      161 = {car_num=4444, id=161, guide_price=32.00, produce_time=2000-10-10, brand=奔驰C200, car_type=新能源},
     *      162 = {car_num=9999, id=162, guide_price=30.00, produce_time=2020-10-11, brand=帕萨特, car_type=燃油车},
     *      163 = {car_num=9991, id=163, guide_price=30.00, produce_time=2020-11-11, brand=凯美瑞, car_type=燃油车},
     *      158 = {car_num=1111, id=158, guide_price=3.00, produce_time=2000-10-10, brand=比亚迪汉, car_type=新能源},
     *      159 = {car_num=2222, id=159, guide_price=32.00, produce_time=2000-10-10, brand=比亚迪秦, car_type=新能源}
     * }
     */
    @MapKey("id") // 指定将查询结果的id值作为整个大Map集合的key。
    Map<Long, Map<String,Object>> selectAllRetMap();

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

    int insertCarUseGeneratedKeys(Student stu);
}