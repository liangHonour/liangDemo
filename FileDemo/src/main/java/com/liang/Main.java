package com.liang;

import com.liang.xmlHelp.XmlHelp;


import org.junit.Test;


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
