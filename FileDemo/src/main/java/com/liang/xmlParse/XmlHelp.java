package com.liang.xmlParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XmlHelp {
    private final String url;
    private Document document;
    private Element root;
    private final String ENCODING = "utf-8";


    public XmlHelp(String url) {
        this.url = url;
        initXmlHelp();
    }

    public void updateLabel(String labelPath, LabelAttrs labels) {
        if (labelPath.indexOf("/") == 0) {
            labelPath = labelPath.substring(1);
        }
        if (labels == null) {
            labels = new LabelAttrs();
        }
        updateLabel(this.root, labelPath, labels);
    }

    private void updateLabel(Element root, String labelPath, LabelAttrs labels) {
        boolean flag = labelPath.contains("/");
        HashMap<String, String> updateAttributes;
        HashMap<String, String> checkAttributes;
        if (!flag) {
            updateAttributes = labels.getUpdateAttributes(labelPath);
            checkAttributes = labels.getCheckAttributes(labelPath);
            //如果该集合不属于修改列表则返回
            if (updateAttributes.isEmpty()) {
                return;
            }
            NodeList nodes = root.getElementsByTagName(labelPath);
            if (nodeSisEmpty(nodes)) {
                return;
            }
            Set<String> checkLabelAttributeNames = checkAttributes.keySet();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                //如果该标签不需要校验则直接进行修改
                if (checkLabelAttributeNames.isEmpty()) {
                    updateLabelAttribute(updateAttributes, node);
                } else {
                    //进行标签校验
                    Element element = (Element) node;
                    for (String checkLabelAttributeName : checkLabelAttributeNames) {
                        String checkLabelAttributeValue = checkAttributes.get(checkLabelAttributeName);
                        if (checkLabelAttributeValue.equals((element.getAttribute(checkLabelAttributeName)))) {
                            updateLabelAttribute(updateAttributes, node);
                        }
                    }
                }
            }
        } else {
            String temp = labelPath.substring(0, labelPath.indexOf("/"));
            labelPath = labelPath.substring(labelPath.indexOf("/") + 1);
            NodeList nodes = root.getElementsByTagName(temp);
            if (nodeSisEmpty(nodes)) {
                return;
            }
            checkAttributes = labels.getCheckAttributes(temp);
            Set<String> checkLabelAttributeNames = checkAttributes.keySet();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                if (!checkAttributes.isEmpty()) {
                    for (String checkLabelAttributeName : checkLabelAttributeNames) {
                        String checkLabelAttributeValue = checkAttributes.get(checkLabelAttributeName);
                        if (checkLabelAttributeValue.equals(element.getAttribute(checkLabelAttributeName))) {
                            updateLabel(element, labelPath, labels);
                        }
                    }
                } else {
                    //如果该标签不需要检验则直接进入下一次递归
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

    public void createLabel(String labelPath, LabelAttrs labels) {
        if (labels == null) {
            labels = new LabelAttrs();
        }
        createLabel(root, labelPath, labels);
    }

    public static void main(String[] args) {
        XmlHelp xmlHelp = new XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");
        LabelAttrs labelAttrs = new LabelAttrs();
        HashMap<String, String> peoplesMap = new HashMap<>();
        peoplesMap.put("name", "人");
        labelAttrs.setLabelCheckAttributes("peoples", peoplesMap);
        HashMap<String, String> people1Map = new HashMap<>();
        people1Map.put("age", "21");
        people1Map.put("name", "梁荣耀");
        people1Map.put("text", "喜欢美女");
        labelAttrs.setLabelUpdateAttributes("people2", people1Map);
        xmlHelp.createLabel("peoples/people2", labelAttrs);
        xmlHelp.close();
    }

    private boolean nodeDetection(Node node, HashMap<String, String> checkAttributes) {
        if (node == null || checkAttributes == null || checkAttributes.isEmpty()) {
            return false;
        }
        Element element = (Element) node;
        for (String checkLabelAttributeName : checkAttributes.keySet()) {
            String checkLabelAttributeValue = checkAttributes.get(checkLabelAttributeName);
            if (checkLabelAttributeValue == null) {
                return false;
            }
            if (!checkLabelAttributeValue.equals(element.getAttribute(checkLabelAttributeName))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 创建一个子节点
     *
     * @param labelName 子节点的名称
     * @return 子节点的Element
     */
    private Element createChildElement(String labelName) {
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

    public void createLabel(Element parentElement, String labelPath, LabelAttrs labels) {
        //首先我需要获得零时标签temp
        String temp;
        if (!labelPath.contains("/")) {
            //如果路径中不包括"/"的话说明已经到节点末尾了，不需要再次进行递归，制造出口条件
            temp = labelPath;
            labelPath = null;
        } else {
            temp = labelPath.substring(0, labelPath.indexOf("/"));
            labelPath = labelPath.substring(labelPath.indexOf("/") + 1);
        }
        HashMap<String, String> updateAttributes = labels.getUpdateAttributes(temp);
        HashMap<String, String> checkAttributes = labels.getCheckAttributes(temp);
        Element childElement;
        //判断下一个符合标准的子节点是否存在，如果不存在则创建一个新的子节点
        NodeList nodes = parentElement.getElementsByTagName(temp);
        if (nodes != null && nodes.getLength() > 0) {
            //如果子节点的下一个节点不需要创建的话进入下一个子节点也没用，所以直接退出递归
            if (labelPath == null) {
                return;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (nodeDetection(node, checkAttributes)) {
                    childElement = (Element) node;
                    createLabel(childElement, labelPath, labels);
                }
            }
        } else {
            //如果没有符合标准的子元素则直接创建一个
            childElement = createChildElement(temp);
            //将子元素添加到父元素下面
            parentAddChild(parentElement, childElement);
            //给子元素添加属性
            if (!updateAttributes.isEmpty()) {
                for (String updateAttributesName : updateAttributes.keySet()) {
                    String updateAttributesValue = updateAttributes.get(updateAttributesName);
                    if ("text".equals(updateAttributesName)){
                        childElement.setTextContent(updateAttributesValue);
                    }else {
                        childElement.setAttribute(updateAttributesName, updateAttributesValue);
                    }
                }
            }
            //出发出口条件，开始结束递归。
            if (labelPath == null) {
                return;
            }
            createLabel(childElement, labelPath, labels);
        }


    }

    private boolean nodeSisEmpty(NodeList nodes) {
        return (nodes == null || nodes.getLength() == 0);
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
            transformer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
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

    public String getFileXml() {
        File file = new File(url);
        InputStream is;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int temp;
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
