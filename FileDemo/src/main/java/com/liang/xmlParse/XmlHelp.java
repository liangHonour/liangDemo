package com.liang.xmlParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (label == null) {
                return;
            }
            if (label.updateAttributes.isEmpty()) {
                return;
            }
            NodeList nodes = root.getElementsByTagName(labelPath);
            if (nodeSisEmpty(nodes)) {
                return;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (label.checkAttributes.isEmpty()) {
                    updateLabelAttribute(label.updateAttributes, node);
                } else {


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
            if (flag) {

            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                if (label != null) {

                    labels.remove(label);
                    continue;
                }
                updateLabel(element, labelPath, labels);
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
            }
            Element element = (Element) node;
            element.setAttribute(updateAttributesName, updateAttributesValue);

        }
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

    public void setLabelCheckAttributes(Label label, String checkLabelAttributeName, String checkLabelAttributeValue) {
        if (label.checkAttributes.containsKey(checkLabelAttributeName)) {
            System.out.println("不允许传入相同标签");
            return;
        }
        label.checkAttributes.put(checkLabelAttributeName, checkLabelAttributeValue);

    }

    public void setLabelUpdateAttributes(Label label, String updateAttributesName, String updateAttributesValue) {
        if (label.updateAttributes.containsKey(updateAttributesName)) {
            System.out.println("不允许传入相同标签");
            return;
        }
        label.checkAttributes.put(updateAttributesName, updateAttributesValue);
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

    private class Label {
        private final String labelName;
        private final HashMap<String, String> checkAttributes;
        private final HashMap<String, String> updateAttributes;

        private Label(String labelName) {
            this.labelName = labelName;
            this.checkAttributes = new HashMap<>();
            this.updateAttributes = new HashMap<>();
        }
    }
}
