package com.lujun61.sqlLanguage;

import com.lujun61.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class selectTest {
    public static void main(String[] args) throws IOException {
        /*
            SqlSessionFactoryBuilder：工人
            build()：工人手中的工具
            Resources.getResourceAsStream(config)：工人需要的生产原料
            SqlSessionFactory：工人建造出来的SqlSession工厂
            openSession()：工厂里的生产机器
            SqlSession：SqlSessionFactory工厂使用生产机器加工出来的产品
        */


        //访问mybatis读取student数据
        /* 1、定义mybatis主配置文件的名称，从类路径的根目录(target/classes)开始 */
        String config = "mybatis.xml";

        /* 2、读取这个config表示的文件：Resources.getResourceAsStream()专门用来读取资源文件 */
        InputStream in = Resources.getResourceAsStream(config);
        /*         底层是通过如下代码实现的
            InputStream configStream = ClassLoader.getSystemClassLoader().getResourceAsStream(config);
        */

       /*
            在MyBatis当中，负责执行SQL语句的对象是【SqlSession】。
            SqlSession是专门用来执行SQL语句的，是一个【Java程序和数据库之间的一次会话。】
            要想获取SqlSession对象，需要先获取SqlSessionFactory对象，通过SqlSessionFactory工厂来生产SqlSession对象。

            mybatis的核心对象包括：
            SqlSessionFactoryBuilder
            SqlSessionFactory
            SqlSession

            SqlSessionFactoryBuilder -创建-> SqlSessionFactory -创建-> SqlSession
        */
        /* 3、创建SqlSessionFactoryBuilder对象 */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        /*
            4、创建SqlSessionFactory对象
            SqlSessionFactory是重量级的对象，程序创建一个对象耗时比较长，使用资源比较多。在整个项目中，有一个就够用了。
            SqlSessionFactory是MyBatis中重要的对象，它是用来创建SqlSession对象的，而SqlSession用来操作数据库的。
            SqlSessionFactory是线程安全的，它一旦被创建，应该在应用运行期间都存在。在应用运行期间不要重复创建多次，建议使用单例模式。
        */
        SqlSessionFactory factory = builder.build(in);

        /* 5、获取SqlSession对象，这个对象中拥有操作数据库的方法；从SqlSessionFactory中获取SqlSession对象 */
        /* openSession()的方法重载：
            无参/参数为false：开启mybatis事务。<==> setAutoCommit(false);----需要手动提交DML
            参数为true：不开启mybatis事务。<==> setAutoCommit(true);----执行一条DML，提交一条DML
         */
        // 开启mybatis事务
        SqlSession sqlSession = factory.openSession();

        /* 6、指定要执行的sql语句标识：sql映射文件中的namespace + . + sql标签中的id */
        String sqlID = "com.lujun61.dao.StudentDAO.selectStudent";

        /* 7、执行sql语句：通过sqlID找到语句 */
        List<Student> studentList = sqlSession.selectList(sqlID);

        /* 8、输出结果 */
        for (Student stu : studentList) {
            System.out.println(stu);
        }

        /* 9、关闭SqlSession对象 */
        sqlSession.close();
    }
}