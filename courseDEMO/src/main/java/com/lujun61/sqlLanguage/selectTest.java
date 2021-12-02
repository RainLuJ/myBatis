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

        /* 3、创建SqlSessionFactoryBuilder对象 */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        /* 4、创建SqlSessionFactory对象 */
        SqlSessionFactory factory = builder.build(in);

        /* 5、获取SqlSession对象：从SqlSessionFactory中获取SqlSession对象 */
        /* openSession()的方法重载：
            方法重载：关闭mybatis非自动提交事务机制
            SqlSession sqlSession = factory.openSession(true);
         */
        SqlSession sqlSession = factory.openSession();

        /* 6、指定要执行的sql语句标识：sql映射文件中的namespace + . + sql标签中的id */
        String sqlID = "com.lujun61.dao.StudentDAO.selectStudent";

        /* 7、执行sql语句：通过sqlID找到语句 */
        List<Student> studentList = sqlSession.selectList(sqlID);

        /* 8、输出结果 */
        for (Student stu :
                studentList) {
            System.out.println(stu);
        }

        /* 9、关闭SqlSession对象 */
        sqlSession.close();
    }
}