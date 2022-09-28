package com.liang;

import com.liang.xmlHelp2.LabelAttrs;
import com.liang.xmlHelp2.XmlHelp;
import com.liang.xmlParse.tmp.LinkedLabel;
import com.liang.xmlParse.util.Util;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;


public class Main {

    @Test
    public void main() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('a');
        System.out.println(stringBuilder);
    }

    @Test
    public void linkdTest(){

    }

    @Test
    public void LinkedTest() {
        com.liang.xmlParse.tmp.XmlHelp xmlHelp = new com.liang.xmlParse.tmp.XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        LinkedLabel linkedLabel = new LinkedLabel();
        linkedLabel.setLabel("person2", "aaa=sys");
        linkedLabel.setLabel("person3", "name=two");
        linkedLabel.setLabel("name", "file=sda");
        xmlHelp.updateLabel(linkedLabel);
        xmlHelp.close();
    }


    @Test
    public void LabelAttrs(){
        XmlHelp xmlHelp = new XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        XmlHelp.LabelAttrs labelAttrs = new XmlHelp.LabelAttrs();
        labelAttrs.addLabel("person2", "aaa=sys");
        labelAttrs.addLabel("personas", "name=two");
        labelAttrs.addLabel("persssaonas", "name=two");
        labelAttrs.addLabel("persasdssaonas", "name=two");
        labelAttrs.addLabel("named", "file=sda");
        labelAttrs.addLabel("naasdmed", "text=sda");
        xmlHelp.updateLabel(labelAttrs,true);
        xmlHelp.close();
    }

}
