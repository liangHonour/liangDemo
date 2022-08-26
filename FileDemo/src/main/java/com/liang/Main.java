package com.liang;

import com.liang.xmlParse.util.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Main {

    @Test
    public void main() throws Exception {
        String xmlMessage = Util.getXmlMessage("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\logback.xml");
        System.out.println(xmlMessage);
    }


}
