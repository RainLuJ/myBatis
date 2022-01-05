使用myBatis的动态代理机制来自动获取dao【接口】的实现类对象

    涉及的方法：
        1、先获取sqlSession对象
        2、使用sqlSession.getMapper();来获取实现类对象
        3、通过获取的【实现类对象】来调用方法，完成对数据库的操作