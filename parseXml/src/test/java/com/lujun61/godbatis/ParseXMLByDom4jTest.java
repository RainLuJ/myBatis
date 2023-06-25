package com.lujun61.godbatis;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {

    /**
     * @description 解析MyBatis映射文件
     * @author Jun Lu
     * @date 2022-10-11 15:37:02
     */
    @Test
    public void testParseSqlMapperXML() throws Exception{
        SAXReader reader = new SAXReader();
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("StudentDAO.xml");
        Document document = reader.read(in);

        String xPath = "/mapper";
        /*获取mapper根标签节点*/
        Element mapper = (Element) document.selectSingleNode(xPath);

        String mapperNamespace = mapper.attributeValue("namespace");
        System.out.println("namespace属性值：" + mapperNamespace);

        /* 获取mapper根标签下的所有标签
                重载方法-无参：返回此标签中包含的所有子标签元素。如果不存在任何子标签，则返回null。
        */
        List<Element> mappperEles = mapper.elements();
        mappperEles.forEach(element -> {
            /* 获取当前标签中所写的所有文本信息：Trim表示去除掉文本前后空白 */
            String textTrim = element.getTextTrim();
            //System.out.println(textTrim);
            String castRegText = textTrim.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(castRegText);
        });

    }

    /**
     * @description 解析MyBatis主配置文件
     * @author Jun Lu
     * @date 2022-10-11 15:36:30
     */
    @Test
    public void testParseMyBatisConfigXML() throws Exception {
        //创建解析器对象
        SAXReader reader = new SAXReader();

        // 获取输入流
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");

        // 解析XML文件，返回document对象。document对象是文档对象，代表了整个XML文件。
        Document document = reader.read(in);

        /* 原生DOM4J方法
            // 获取根结点
            Element rootElement = document.getRootElement();
            // 获取根结点名字
            System.out.println("根结点名字；" + rootElement.getName());
        */

        /* 继承XPath */
        /*xPath的根路径使用“/”表示*/
        String xPath = "/configuration/environments";

        /* 使用xPath来获取XML文件中的单个节点 */
        // Element是Node的子接口，提供的方法更多，使用更简洁
        Element environments = (Element) document.selectSingleNode(xPath);

        /* 获取属性的值 */
        String defaultEleVal = environments.attributeValue("default");
        //System.out.println("default属性值：" + defaultEleVal);

        /* environments标签嵌套environment标签，需要根据environments标签的default属性来动态获取environment子标签 */
        // xPath语法：根据属性值来获取节点     ……/tagName[@attributeName='']
        xPath = "/configuration/environments/environment[@id='"+ defaultEleVal + "']";
        // 获取environment父标签
        Element environment = (Element) document.selectSingleNode(xPath);

        /* 获取 单个 子节点 */
        // 获取transactionManager子标签
        Element transactionManager = environment.element("transactionManager");
        // 获取transactionManager子标签的type属性值
        String transactionManagerType = transactionManager.attributeValue("type");
        System.out.println("事务管理器：" + transactionManagerType);

        // 获取dataSource子标签
        Element dataSource = environment.element("dataSource");
        // 获取dataSource子标签的type属性值
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据库连接池：" + dataSourceType);

        /* 获取 多个 子节点 */
        // 获取dataSource标签下所有的子标签节点property
        List<Element> propertyEles = dataSource.elements("property");
        System.out.println("属性配置信息：");
        propertyEles.forEach(propertyEle -> {
            String propertyName = propertyEle.attributeValue("name");
            String propertyValue = propertyEle.attributeValue("value");

            System.out.println(propertyName + "=" + propertyValue);
        });


    }
}





















