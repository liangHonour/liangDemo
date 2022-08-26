package com.liang.xmlParse.sax;

import com.liang.xmlParse.sax.help.BookHandler;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        // 创建sax解析器工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 创建sax解析器
        SAXParser saxParser = factory.newSAXParser();
        // 创建xml读取器
        XMLReader reader = saxParser.getXMLReader();
        BookHandler bh = new BookHandler();
        // 绑定事件处理器
        reader.setContentHandler(bh);
        reader.parse("FileDemo/src/main/resources/beans.xml");
    }
}