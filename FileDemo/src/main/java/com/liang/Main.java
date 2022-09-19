package com.liang;

import com.liang.xmlHelp2.LabelAttrs;
import com.liang.xmlHelp2.XmlHelp;
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
    public void xmlHelpTest() {
        XmlHelp xmlHelp = new XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        LabelAttrs labelAttrs = new LabelAttrs();
        labelAttrs.addLabel("person2", "aaa=sys");
        labelAttrs.addLabel("person3", "name=two");
        labelAttrs.addLabel("age", "file=sda");
        xmlHelp.updateLabel(labelAttrs);
        xmlHelp.close();
    }


}
