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


public class XmlHelp {
    private final String url;
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

    public void updateLabel(LabelAttrs labels, boolean flag) {
        updateLabel(this.root, labels, flag);
    }

    /**
     * 对xml进行处理
     *
     * @param root   节点
     * @param labels 当前需要处理的标签
     * @param flag   如果ture 没有该标签的情况下会进行创建
     */
    private void updateLabel(Element root, LabelAttrs labels, boolean flag) {
        if (labels == null) {
            return;
        }
        LabelAttrs.Label label = labels.getNode();
        if (label == null) {
            return;
        }
        String labelName = label.getLabelName();
        Map<String, String> attrs = label.getAttrs();
        //获取当前标签下所有相似的标签，标签集
        NodeList nodes = root.getElementsByTagName(labelName);
        //如果节点为空并且不需要创建新节点则直接返回
        if (nodeSisEmpty(nodes)) {
            if (flag) {
                Element childElement = createChildElement(root, label);
                updateLabel(childElement, labels.next(), true);
                return;
            } else {
                return;
            }
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            //如果为空说明到底了，说明这个是需要修改的元素，反之则继续向下寻找需要修改的元素
            if (labels.isEmpty()) {
                //只要最后一个标签存在就把他的属性全部覆盖掉
                updateLabelAttribute(attrs, node);
            } else {
                Element element = (Element) node;
                //如果没有找到符合的标签则直接结束递归
                if (attrs.isEmpty()) {
                    //如果判断标签为空说明该标签不需要判断
                    updateLabel(element, labels.next(), flag);
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
                        updateLabel(element, labels.next(), flag);
                    } else if (flag) {
                        Element childElement = createChildElement(root, label);
                        updateLabel(childElement, labels.next(), true);
                    }
                }
            }
        }
    }

    /**
     * 修改节点内容
     *
     * @param updateAttributes 节点内容
     * @param node             需要修改的节点
     */
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

    /**
     * 根据Label创建一个子节点
     *
     * @param parentElement 父节点
     * @param label         子节点
     * @return 子节点
     */
    private Element createChildElement(Element parentElement, LabelAttrs.Label label) {
        Element childElement = document.createElement(label.getLabelName());
        parentElement.appendChild(childElement);
        for (Map.Entry<String, String> attr : label.getAttrs().entrySet()) {
            childElement.setAttribute(attr.getKey(), attr.getValue());
        }
        return childElement;
    }

    /**
     * 初始化xmlHelp对象
     */
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

    /**
     * 关闭资源同时保存文件
     */
    public void close() {
        FileOutputStream out = null;
        try {
            File file = new File(this.url);
            TransformerFactory transFactor = TransformerFactory.newInstance();
            Transformer transformer = transFactor.newTransformer();
            DOMSource domSource = new DOMSource(document);
            out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            String ENCODING = "utf-8";
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

    /**
     * 判断这个节点是不是空
     *
     * @param nodes 节点
     * @return true：空节点、false：不为空
     */
    private boolean nodeSisEmpty(NodeList nodes) {
        return (nodes == null || nodes.getLength() == 0);
    }

    /**
     * 获取xmlHelp初始化的xml
     *
     * @return String类型Xml
     */
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


    public static class  LabelAttrs {

        /**
         * 首节点
         */
        private Label node;
        /**
         * 下一个节点
         */
        private LabelAttrs next;
        /**
         * 尾巴节点
         */
        private LabelAttrs tailNode;

        public LabelAttrs() {

        }

        private LabelAttrs(LabelAttrs.Label node) {
            this.node = node;
        }

        /**
         * 获取下一个标签内容
         *
         * @return 标签
         */
        public LabelAttrs getAttrs() {
            if (isEmpty()) {
                return null;
            }
            return this.next;
        }

        public LabelAttrs next() {
            return this.next;
        }

        public boolean isEmpty() {
            return this.next == null;
        }

        public void addLabel(String labelName, String... attrs) {
            HashMap<String, String> attrMap = new HashMap<>();
            LabelAttrs.Label label = null;
            if (attrs.length == 0) {
                label = new LabelAttrs.Label(labelName, attrMap);
            } else {
                for (String attr : attrs) {
                    if (attr == null) {
                        continue;
                    }
                    String key;
                    String value;
                    if (!attr.contains("=")) {
                        key = attr;
                        value = "";
                    } else {
                        String[] split = attr.split("=");
                        key = split[0];
                        value = split[1];
                    }
                    attrMap.put(key, value);
                }
                label = new LabelAttrs.Label(labelName, attrMap);
            }
            /**
             * 如果node未空则说明这是一个新对象，对他进行初始化
             */
            if (node == null) {
                node = label;
                tailNode = this;
            } else {
                LabelAttrs labelAttrs = new LabelAttrs(label);
                tailNode.setNext(labelAttrs);
                tailNode = tailNode.next;
            }
        }

        public LabelAttrs.Label getNode() {
            return node;
        }

        public void setNext(LabelAttrs next) {
            this.next = next;
        }

        class Label {
            public String labelName;
            public Map<String, String> attrs;

            Label(String labelName, Map<String, String> attrs) {
                this.labelName = labelName;
                this.attrs = attrs;
            }

            public String getLabelName() {
                return labelName;
            }

            public Map<String, String> getAttrs() {
                return attrs;
            }


        }


    }

    static class Label {
        public String labelName;
        public Map<String, String> attrs;

        Label(String labelName, Map<String, String> attrs) {
            this.labelName = labelName;
            this.attrs = attrs;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public Map<String, String> getAttrs() {
            return attrs;
        }

        public void setAttrs(Map<String, String> attrs) {
            this.attrs = attrs;
        }

    }
}
