package com.liang;

import com.liang.xmlHelp2.LabelAttrs;
import com.liang.xmlHelp2.XmlHelp;
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
    public void xmlHelpTest() {
        XmlHelp xmlHelp = new XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        LabelAttrs labelAttrs = new LabelAttrs();
        labelAttrs.addLabel("person2", "aaa=sys");
        labelAttrs.addLabel("person3", "name=two");
        labelAttrs.addLabel("name", "file=sda");
        labelAttrs.addLabel("sss", "file=sda");
        labelAttrs.addLabel("aa", "file=sda");
        labelAttrs.addLabel("cc", "file=sda");
        int a = 5;
//        xmlHelp.updateLabel(labelAttrs);
//        xmlHelp.close();
    }


}
