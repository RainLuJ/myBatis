package com.lujun61.proxy;

import com.lujun61.mapper.AccountDao;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


public class JavassistTest {

    /**
     * @description javassist的基本使用：在内存（JVM）中创建接口、接口实现类、并实现方法；
     * 实现的方法需要利用反射获取方法的具体声明，并按照这个方法声明利用javassist在内存中创建一个方法，
     * 并将这个方法添加至接口实现类中。
     * 最后，利用反射创建接口实现类对象并调用所实现的方法。
     * @author Jun Lu
     * @date 2022-10-11 17:43:38
     */
    @Test
    public void testJavassistImplInterfacePlus() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // 制造接口
        String interfaceName = "com.lujun61.mapper.AccountDao";
        CtClass anInterface = pool.makeInterface(interfaceName);

        // 制造接口实现类
        String implClassName = "com.lujun61.mapper.AccountDaoImpl";
        CtClass implClass = pool.makeClass(implClassName);

        // 给类添加一个接口：表示让接口实现类实现添加的接口
        implClass.addInterface(anInterface);

        Method[] declaredMethods = AccountDao.class.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            StringBuilder methodName = new StringBuilder();
            methodName.append("public ");
            methodName.append(method.getReturnType().getName()).append(" ");
            methodName.append(method.getName()).append(" (");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                methodName.append(parameterTypes[i].getName());
                methodName.append(" arg").append(i);
                if (i != parameterTypes.length - 1) {
                    methodName.append(", ");
                }
            }
            methodName.append("){\n\tSystem.out.println(\""+ method.getName() +" 代理方法创建成功！！\");\n");
            String returnTypeSimpleName = method.getReturnType().getSimpleName();
            //System.out.println(returnTypeSimpleName);
            // 简单处理一下因为动态拼接的方法没有返回值导致报错的问题
            if ("int".equals(returnTypeSimpleName)) {
                methodName.append("\treturn 1;\n");
            } else if ("String".equals(returnTypeSimpleName)) {
                methodName.append("\treturn \"SimpleReturnValue\";\n");
            }
            methodName.append("}");
            System.out.println(methodName);

            // 将动态拼接所生成的方法添加至接口方法实现类当中
            try {
                CtMethod dynamicMethod = CtMethod.make(methodName.toString(), implClass);
                implClass.addMethod(dynamicMethod);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });

        /* 创建接口实现类对象，调用接口中的方法进行测试 */
        Class<?> aClass = implClass.toClass();
        Constructor<?> constructor = aClass.getConstructor();
        AccountDao accountDao = (AccountDao) constructor.newInstance();
        accountDao.delete();
        accountDao.update("test", 1.1);
        accountDao.insert("test");
        accountDao.selectByActno("test");
    }


    /**
     * @description javassist的基本使用：在内存（JVM）中创建接口、接口实现类、实现方法；利用反射创建对象并调用创建的方法
     * 在这个Demo中，存在缺点：虽然声称在实现接口，但是实现类中的方法实现是写死的！！
     * @author Jun Lu
     * @date 2022-10-11 17:43:38
     */
    @Test
    public void testJavassistImplInterface() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // 制造接口
        String interfaceName = "com.lujun61.mapper.AccountDao";
        CtClass anInterface = pool.makeInterface(interfaceName);

        // 制造接口实现类
        String implClassName = "com.lujun61.mapper.AccountDaoImpl";
        CtClass implClass = pool.makeClass(implClassName);

        // 给类添加一个接口：表示让接口实现类实现添加的接口
        implClass.addInterface(anInterface);

        // 给实现类自定义方法
        String methodName = "public void delete(){System.out.println(\"delete\");}";
        CtMethod ovMethod1 = CtMethod.make(methodName, implClass);

        // 给类添加一个方法：表示在此类中创建一个方法
        implClass.addMethod(ovMethod1);

        // ---------------------------------------------------
        // 在类中创建接口实现类
        Class<?> aClass = implClass.toClass();

        // 利用反射创建对象
        Constructor<?> constructor = aClass.getConstructor();
        AccountDao accountDao = (AccountDao) constructor.newInstance();

        // 利用反射获取方法，调用方法
        Method delete = aClass.getDeclaredMethod("delete");
        delete.invoke(accountDao);
    }

    /**
     * @description javassist的基本使用：在内存（JVM）中创建类、方法；利用反射创建对象并调用创建的方法
     * @author Jun Lu
     * @date 2022-10-11 17:43:38
     */
    @Test
    public void testJavassistToClass() throws Exception {
        /*                使用javassist生成类及方法                   */
        // 获取类池：这个类池用来生成/制造class
        ClassPool pool = ClassPool.getDefault();

        // 制造一个类，前提是需要指定【类名】
        String className = "com.lujun61.mapper.AccountDaoImpl";
        CtClass ctClass = pool.makeClass(className);

        // 为创建的类制造方法
        String methodName = "public void insert(){System.out.println(\"HelloWorld\");}";
        CtMethod methodInsert = CtMethod.make(methodName, ctClass);

        // 将方法添加至类中
        ctClass.addMethod(methodInsert);

        // 在内存中生成类
        ctClass.toClass();


        /*                     调用生成类                           */
        Class<?> aClass = Class.forName("com.lujun61.mapper.AccountDaoImpl");
        Constructor<?> constructor = aClass.getConstructor();
        Object o = constructor.newInstance();
        Method insert = aClass.getDeclaredMethod("insert");
        insert.invoke(o);

    }

}
