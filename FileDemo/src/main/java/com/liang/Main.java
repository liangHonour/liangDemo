package com.liang;

import com.liang.xmlParse.XmlHelp;
import com.liang.xmlParse.util.Util;

import org.junit.Test;

import java.util.ArrayList;


public class Main {

    @Test
    public void main() throws Exception {
        String xmlMessage = Util.getXmlMessage("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\logback.xml");
        System.out.println(xmlMessage);
    }

    @Test
    public void xmlHelpTest(){
        String url = "D:\\stduy\\CloudeProject\\liangDemo\\FileDemo\\src\\main\\resources\\logback.xml";
        XmlHelp xmlHelp = new XmlHelp(url);
        XmlHelp.Label appender = xmlHelp.getLabel("appender");
        appender.setLabelCheckAttributes("name","file");
        XmlHelp.Label file = xmlHelp.getLabel("file");
        file.setLabelUpdateAttributes("text","${logFile}/liang.log");
        ArrayList<XmlHelp.Label> labels = new ArrayList<>();
        labels.add(appender);
        labels.add(file);
        xmlHelp.updateLabel("appender/file",labels);
        labels.clear();

        XmlHelp.Label property = xmlHelp.getLabel("property");
        property.setLabelCheckAttributes("name","logFile");
        property.setLabelUpdateAttributes("value","/aa/aa/aaass/aaa");
        labels.add(property);
        xmlHelp.updateLabel("property",labels);
        xmlHelp.close();
    }


}
