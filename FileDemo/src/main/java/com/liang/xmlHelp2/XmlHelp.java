package com.liang.xmlHelp2;

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
    private final String ENCODING = "utf-8";
    private Document document;
    private Element root;


    public XmlHelp(String url) {
        this.url = url;
        initXmlHelp();
    }

    public static void main(String[] args) {
        XmlHelp xmlHelp = new XmlHelp("D:\\work\\Project\\liangDemo\\FileDemo\\src\\main\\resources\\ssss.xml");


        xmlHelp.close();
    }

    public void updateLabel(LabelAttrs labels) {
        updateLabel(this.root, labels, false);
    }

    private void updateLabel(Element root, LabelAttrs labels, boolean flag) {
        LabelAttrs.Label label = labels.getAttrs();
        if (label == null) {
            return;
        }
        String labelName = label.labelName;
        Map<String, String> attrs = label.attrs;
        //获取当前标签下所有相似的标签，标签集
        NodeList nodes = root.getElementsByTagName(labelName);
        if (nodeSisEmpty(nodes)) {
            return;
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            //如果为空说明到底了，说明这个是需要修改的元素，反正则继续向下寻找需要修改的元素
            if (labels.isEmpty()) {
                //只要最后一个标签存在就把他的属性全部覆盖掉
                updateLabelAttribute(attrs, node);
            } else {
                Element element = (Element) node;
                //如果没有找到符合的标签则直接结束递归
                if (attrs.isEmpty()) {
                    //如果判断标签为空说明该标签不需要判断
                    updateLabel(element, labels, flag);
                } else {
                    boolean checkFlag = true;
                    for (Map.Entry<String, String> attr : attrs.entrySet()) {
                        String key = attr.getKey();
                        String value = attr.getValue() == null ? "" : attr.getValue();
                        //如果有一个校验不通过就不用继续校验了，直接退出。
                        if (!value.equals(element.getAttribute(key))) {
                            checkFlag = false;
                            break;
                        }
                    }
                    //如果校验通过了则继续进行递归
                    if (checkFlag) {
                        updateLabel(element, labels, flag);
                    }
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

    private boolean nodeSisEmpty(NodeList nodes) {
        return (nodes == null || nodes.getLength() == 0);
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
