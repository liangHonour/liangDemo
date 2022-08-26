package com.liang.xmlParse.dom4j.help;

import com.liang.xmlParse.util.Util;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogXmlHelp {

    private String url = null;
    private Document document;
    private Element root = null;

    private List<Element> property;
    private List<Element> appender;

    public LogXmlHelp(String url) {
        this.url = url;
        getDocument();
        this.root = document.getRootElement();
    }

    public static void main(String[] args) {
        LogXmlHelp logXmlHelp = new LogXmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\logback.xml");
        logXmlHelp.updateLogPath("cccc/aaa.log");
        logXmlHelp.close();
    }

    public void updateLogPath(String path) {
        String logName = path.substring(path.lastIndexOf("/") + 1);
        String logPath = path.substring(0, path.lastIndexOf("/"));
        if (property == null) {
            this.property = root.elements("property");
        }
        for (Element element : property) {
            if ("logFile".equals(element.attributeValue("name"))) {
                element.addAttribute("value", logPath);
                break;
            }
        }
        if (appender == null) {
            this.appender = root.elements("appender");
        }
        for (Element element : appender) {
            if ("FILE".equals(element.attributeValue("name"))) {
                Element file = element.element("File");
                file.setText("${logFile}" + "/" + logName);
                break;
            }
        }
    }

    /**
     * 表示xml修改已经完成，将文件输出至读取处的url
     */
    public void close() {
        try {
            FileOutputStream outputStream = new FileOutputStream(url);
            XMLWriter xmlWriter = new XMLWriter(outputStream);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDocument() {
        String fileXml = getFileXml();
        try {
            this.document = DocumentHelper.parseText(fileXml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String getFileXml() {
        File file = new File(url);
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int temp = -1;
        try {
            is = new FileInputStream(file);
            while ((temp = is.read(buffer)) != -1) {
                bos.write(buffer, 0, temp);
            }
            return bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
