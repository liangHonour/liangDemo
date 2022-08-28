package com.liang.xmlParse.w3c;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CreateXmlFile {

    /**
     * 将指定的Node写到指定的OutputStream流中。
     *
     * @param encoding
     *            编码。
     * @param os
     *            OutputStream流。
     * @param node
     *            Node节点。
     * @throws TransformerException
     *             如果转换过程中发生不可恢复的错误时，抛出此异常。
     */
    private static void writeXml(OutputStream os, Node node, String encoding)
            throws TransformerException {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, encoding);

        DOMSource source = new DOMSource();
        source.setNode(node);
        StreamResult result = new StreamResult();
        result.setOutputStream(os);

        transformer.transform(source, result);
    }

    /**
     * 创建文档之前，现将document创建出来,并且将document的根节点创建出来
     *
     * @return document对象
     * @throws ParserConfigurationException
     */
    public static Document createDocument() throws ParserConfigurationException {
        // 定义工厂 API，使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 定义 API， 使其从 XML 文档获取 DOM 文档实例。使用此类，应用程序员可以从 XML 获取一个 Document
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Document 接口表示整个 HTML 或 XML 文档。从概念上讲，它是文档树的根，并提供对文档数据的基本访问
        return builder.newDocument();
    }

    /**
     * 向document添加根节点
     *
     * @param document
     * @param rootName
     *            根节点的名称
     * @return 根节点对应的element对象
     */

    public static Element rootElement(Document document, String rootName) {
        Element element = document.createElement(rootName);
        document.appendChild(element);
        return element;
    }

    /**
     * 创建一个element
     *
     * @param document
     * @param elementName
     *            名称
     * @return 返回element对象
     */
    public static Element docCreateElement(Document document, String elementName) {
        return document.createElement(elementName);
    }

    /**
     * 向父级element添加子element
     *
     * @param document
     * @param parentElement
     *            父级element对象
     * @param childName
     *            子级element对象的<b>名称</b>
     * @return 子级element对象
     */
    public static void parentAddChild(Document document, Element parentElement,
                                      Element childName) {
        parentElement.appendChild(childName);
    }

    /**
     * 向最底层的element添加元素属性
     *
     * @param document
     * @param element
     *            操作的对象
     * @param attrName
     *            属性名称
     * @param attrValue属性对应的值
     */
    public static void addAttrtoElement(Document document, Element element,
                                        String attrName, String attrValue) {
        Element name = document.createElement(attrName);
        name.appendChild(document.createTextNode(attrValue));
        element.appendChild(name);
    }

    /**
     * 将Document输出到指定的文件中。
     *
     * @param fileName
     *            文件名。
     * @param node
     *            要保存的对象。
     * @param encoding
     *            保存的编码。
     * @throws FileNotFoundException
     *             指定的文件名不存在时，抛出此异常。
     * @throws TransformerException
     *             如果转换过程中发生不可恢复的错误时，抛出此异常。
     */
    public static void saveXml(final String fileName, final Node node,
                               String encoding) throws FileNotFoundException, TransformerException {
        writeXml(new FileOutputStream(fileName), node, encoding);
    }
}