                            这是第一个MyBatis入门例子
实现步骤：
    1、加入mybatis、mysql驱动的依赖。

    2、根据数据库中的数据创建实体类。

    3、创建持久层的dao接口，定义操作数据库的方法。

    4、创建一个供mybatis使用的配置文件：sql.xml映射文件。
        sql.xml映射文件：写sql语句的。一般一个表对应一个sql映射文件。
        1）写在dao接口所在的目录之中
        2）文件名需和接口名保持一致

    5、创建mybatis的主配置文件：
        一个项目就是一个主配置文件。
        主配置文件提供了 数据库的连接信息 和 sql映射文件的位置 信息……
        1）写在main/resources目录之下
        2）文件名自定义。一般写成《mybatis-config.xml》

    6、创建使用mybatis类：通过mybatis访问数据库。