package com.liang.xmlParse;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class XmlHelp {
    private final String url;
    private Document document;
    private Element root;


    public XmlHelp(String url) {
        this.url = url;
        initXmlHelp();
    }

    public void updateLabel(String labelPath, List<Label> labels) {
        if (labelPath.indexOf("/") == 0) {
            labelPath = labelPath.substring(1);
        }
        updateLabel(this.root, labelPath, labels);
    }

    private void updateLabel(Element root, String labelPath, List<Label> labels) {
        boolean flag = labelPath.contains("/");
        if (!flag) {
            Label label = getLabel(labels, labelPath);
            //如果该集合不属于修改列表则返回
            if (label == null) {
                return;
            }
            //如果集合为空则不需要修改直接返回
            if (label.updateAttributes.isEmpty()) {
                return;
            }
            NodeList nodes = root.getElementsByTagName(labelPath);
            if (nodeSisEmpty(nodes)) {
                return;
            }
            Set<String> checkLabelAttributeNames = label.checkAttributes.keySet();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                //如果该标签不需要校验则直接进行修改
                if (checkLabelAttributeNames.isEmpty()) {
                    updateLabelAttribute(label.updateAttributes, node);
                } else {
                    //进行标签校验
                    Element element = (Element) node;
                    for (String checkLabelAttributeName : checkLabelAttributeNames) {
                        String checkLabelAttributeValue = label.checkAttributes.get(checkLabelAttributeName);
                        if (checkLabelAttributeValue.equals((element.getAttribute(checkLabelAttributeName)))) {
                            updateLabelAttribute(label.updateAttributes, node);
                        }
                    }
                }
            }
        } else {
            String tem = labelPath.substring(0, labelPath.indexOf("/"));
            labelPath = labelPath.substring(labelPath.indexOf("/") + 1);
            NodeList nodes = root.getElementsByTagName(tem);
            if (nodeSisEmpty(nodes)) {
                return;
            }
            Label label = getLabel(labels, tem);
            flag = label != null;
            Set<String> checkLabelAttributeNames = null;
            if (flag) {
                checkLabelAttributeNames = label.checkAttributes.keySet();
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                //如果label为空则直接进入下一个节点不需要进行校验
                if (flag) {
                    for (String checkLabelAttributeName : checkLabelAttributeNames) {
                        String checkLabelAttributeValue = label.checkAttributes.get(checkLabelAttributeName);
                        if (checkLabelAttributeValue.equals(element.getAttribute(checkLabelAttributeName))) {
                            updateLabel(element, labelPath, labels);
                        }
                    }
                } else {
                    updateLabel(element, labelPath, labels);
                }
            }
        }
    }

    private void updateLabelAttribute(Map<String, String> updateAttributes, Node node) {
        if (updateAttributes == null || updateAttributes.isEmpty()) {
            return;
        }
        for (String updateAttributesName : updateAttributes.keySet()) {
            String updateAttributesValue = updateAttributes.get(updateAttributesName);
            if (updateAttributesName.equals("text")) {
                node.setTextContent(updateAttributesValue);
                continue;
            }
            Element element = (Element) node;
            element.setAttribute(updateAttributesName, updateAttributesValue);

        }
    }

    public void createLabel(String labelPath, List<Label> labels) {
        createLabel(root, labelPath, labels);
    }

    public void createLabel(Element parentElement, String labelPath, List<Label> labels) {
        //首先我需要获得零时标签temp
        String temp = null;
        if (!labelPath.contains("/")) {
            //如果路径中不包括"/"的话说明已经到节点末尾了，不需要再次进行递归，制造出口条件
            temp = labelPath;
            labelPath = null;
        } else {
            temp = labelPath.substring(0, labelPath.indexOf("/"));
            labelPath = labelPath.substring(labelPath.indexOf("/") + 1);
        }
        //创建一个子元素
        Element childElement = createChildElement(temp);
        //将子元素添加到父元素下面
        parentAddChild(parentElement, childElement);
        //给子元素添加属性
        Label label = getLabel(labels, temp);
        if (label!=null){
            for (String updateAttributesName : label.updateAttributes.keySet()) {
                String updateAttributesValue = label.updateAttributes.get(updateAttributesName);
                childElement.setAttribute(updateAttributesName,updateAttributesValue);
            }
        }
        //出发出口条件，开始结束递归。
        if (labelPath == null) {
            return;
        }
        createLabel(childElement, labelPath, labels);
    }


    /**
     * 创建一个子节点
     *
     * @param labelName 子节点的名称
     * @return 子节点的Element
     */
    private Element createChildElement(String labelName) {
        Attr name = document.createAttribute("name");
        return document.createElement(labelName);
    }

    /**
     * 向父节点中添加一个子节点
     *
     * @param parentElement 父元素
     * @param childElement  子元素
     */
    private void parentAddChild(Element parentElement, Element childElement) {
        parentElement.appendChild(childElement);
    }


    private void initXmlHelp() {
        StringReader reader = null;
        try {
            String fileXml = getFileXml();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource inputSource = new InputSource();
            reader = new StringReader(fileXml);
            inputSource.setCharacterStream(reader);
            this.document = builder.parse(inputSource);
            root = document.getDocumentElement();
        } catch (Exception e) {
            System.out.println("对象初始化失败");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void close() {
        FileOutputStream out = null;
        try {
            File file = new File(this.url);
            TransformerFactory transFactor = TransformerFactory.newInstance();
            Transformer transformer = transFactor.newTransformer();
            DOMSource domSource = new DOMSource(document);
            out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
        } catch (Exception e) {
            System.out.println("文件保存失败");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean nodeSisEmpty(NodeList nodes) {
        return (nodes == null || nodes.getLength() == 0);
    }

    public Label getLabel(String labelName) {
        return new Label(labelName);
    }

    private Label getLabel(List<Label> labels, String labelName) {
        if (labels == null || labels.isEmpty()) {
            return null;
        }
        for (Label label : labels) {
            if (labelName.equals(label.labelName)) {
                return label;
            }
        }
        return null;
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

    public class Label {
        private final String labelName;
        private final HashMap<String, String> checkAttributes;
        private final HashMap<String, String> updateAttributes;

        private Label(String labelName) {
            this.labelName = labelName;
            this.checkAttributes = new HashMap<>();
            this.updateAttributes = new HashMap<>();
        }

        public void setLabelUpdateAttributes(String updateAttributesName, String updateAttributesValue) {
            if (updateAttributes.containsKey(updateAttributesName)) {
                System.out.println("不允许传入相同标签");
                return;
            }
            this.updateAttributes.put(updateAttributesName, updateAttributesValue);
        }

        public void setLabelCheckAttributes(String checkLabelAttributeName, String checkLabelAttributeValue) {
            if (checkAttributes.containsKey(checkLabelAttributeName)) {
                System.out.println("不允许传入相同标签");
                return;
            }
            if (checkLabelAttributeValue == null) {
                System.out.println("不允许传入空值");
            }
            if (updateAttributes.containsKey(checkLabelAttributeName)) {
                System.out.println("不允许传入相同标签");
                return;
            }
            this.updateAttributes.put(checkLabelAttributeName, checkLabelAttributeValue);
        }
    }


    public static void main(String[] args) {
        XmlHelp xmlHelp = new XmlHelp("D:\\stduy\\CloudeProject\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        ArrayList<Label>  labels= new ArrayList<>();
        Label peoples = xmlHelp.getLabel("peoples");
        peoples.setLabelUpdateAttributes("name","人");
        labels.add(peoples);
        Label people1 = xmlHelp.getLabel("people1");
        people1.setLabelUpdateAttributes("name","梁荣耀");
        people1.setLabelUpdateAttributes("age","21");
        people1.setLabelUpdateAttributes("text","喜欢美女");
        labels.add(people1);
        xmlHelp.createLabel("peoples/people1", labels);
        xmlHelp.close();
    }
}
