package com.liang.xmlParse.sax.help;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.*;

/**
 * xml事件处理器
 * 可以实现ContentHandler 或者继承DefaultHandler
 * 最好继承DefaultHandler，因为实现ContentHandler需要实现所有的方法
 * 而DefaultHandler本身是一个实现了ContentHandler 接口的类，空实现了所有的方法
 * 继承DefaultHandler只需要重写需要的方法即可
 */
public class BookHandler extends DefaultHandler {

    private List<Object> objList;
    private String clazz;
    private Map<String, String> map;
    private String content = StringUtils.EMPTY;
    private String name;

    /**
     * 文档解析前调用，只会被调用一次
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("==开始解析xml文件==");
    }

    /**
     * 标签节点开始解析时调用
     *
     * @param uri
     * @param localName
     * @param qName      标签名称
     * @param attributes 标签的属性集
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        // 解析至名字为bean的节点时，创建对象
        if (StringUtils.equalsIgnoreCase(qName, "beans")) {
            objList = new ArrayList<>();
            map = new HashMap<>();
        } else if (StringUtils.equalsIgnoreCase(qName, "bean")) {
            String id = attributes.getValue("id");
            clazz = attributes.getValue("class");
            map.put("id", id);
        } else if (StringUtils.equalsIgnoreCase(qName, "property")) {
            name = attributes.getValue("name");
            String value = attributes.getValue("value");
            map.put(name, value);
        }
    }

    /**
     * 解析标签内容的时候调用
     *
     * @param ch     当前读取内容的字节数组
     * @param start  字节开始位置
     * @param length 当前读取内容的长度
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 获取节点内容
        content = new String(ch, start, length);
    }

    /**
     * 标签节点结束时调用
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (StringUtils.equalsIgnoreCase(qName, "bean")) {
            setProperty();
        } else if (StringUtils.equalsIgnoreCase(qName, "property")) {
            if (StringUtils.isNotBlank(content)) {
                map.put(name, content);
            }
            name = StringUtils.EMPTY;
        }
    }

    /**
     * 文档解析结束后调用，只会调用一次
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("==结束解析xml文件==");
        System.out.println(JSON.toJSON(objList));
    }

    private void setProperty() {
        if (StringUtils.isBlank(clazz)) {
            return;
        }
        try {
            Class<?> aClass = Class.forName(clazz);
            Object obj = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                Object object = MapUtils.getObject(map, field.getName());
                if (Objects.nonNull(object)) {
                    field.setAccessible(true);
                    if (Objects.equals(field.getType(), Integer.class)) {
                        field.set(obj, Integer.valueOf((String) object));
                    } else if (Objects.equals(field.getType(), String.class)) {
                        field.set(obj, object);
                    }
                }
            }
            objList.add(obj);
            map.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}