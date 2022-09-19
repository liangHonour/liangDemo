package com.liang.xmlParse.w3c;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {

    public static void main(String[] args) {
        try {
            Document doc = CreateXmlFile.createDocument();

            // 此处创建是向document中添加根目录

            Element root = CreateXmlFile.rootElement(doc, "persons1");
            // 创建一个的element节点名称为person2
            Element ele = CreateXmlFile.docCreateElement(doc, "person2");
            // 向根目录中添加子节点(此方法可以重复使用，不断向你需要添加的父节点添加子节点)
            CreateXmlFile.parentAddChild(doc, root, ele);
            for (int i = 0; i < 3; i++) {
                Element ele1 = CreateXmlFile.docCreateElement(doc, "person3");
                CreateXmlFile.addAttrtoElement(doc, ele1, "age", "23-" + i);
                CreateXmlFile.addAttrtoElement(doc, ele1, "name", "张三-" + i);
                // 将组装好的属性对象添加到他的上级节点中
                CreateXmlFile.parentAddChild(doc, ele, ele1);
            }
            // 保存到指定的文件夹中
            CreateXmlFile.saveXml("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml", root,
                    "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
