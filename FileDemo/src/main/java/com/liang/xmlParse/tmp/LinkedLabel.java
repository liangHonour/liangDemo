package com.liang.xmlParse.tmp;

import java.util.HashMap;
import java.util.Map;

public class LinkedLabel {
    private Node node;

    /**
     * 最后一个节点的坐标
     */
    private Node tailNode;

    public LinkedLabel() {

    }

    private LinkedLabel(Label label) {
        this.node = new Node(label);
    }

    /**
     * 该方法用来解析标签
     *
     * @param labelName 标签的名字
     * @param attrs     标签的属性， 传入结构为name=liang 如果不传入=将默认value值为空。
     */
    public void setLabel(String labelName, String... attrs) {
        //对内容进行拆解
        Map<String, String> attrsMap = new HashMap<>();
        for (String attr : attrs) {
            String key = attr;
            String value = "";
            if (attr.contains("=")) {
                if (attr.startsWith("=") || attr.endsWith("=")) {
                    continue;
                }
                String[] ats = attr.split("=");
                key = ats[0];
                value = ats[1];
            }
            attrsMap.put(key, value);
        }
        Label label = new Label(labelName, attrsMap);
        Node node = new Node(label);
        /*
        因为不论如何我使用的都是第一个节点，
        所以我第一个节点如果label为null的话就说明当前节点还未初始化，
        就需要先对节点进行初始化
        */
        if (this.node == null) {
            this.node = node;
        } else if (this.node.nextNode == null) {
            //专门正对第一个节点进行初始化，初始化nextLabel和tailLabel
            this.node.nextNode = node;
            this.tailNode = this.node.nextNode;
        } else {
            //这里的话节点里面的末尾节点是空的，也就是说如果不是首节点将无法继续添加节点，
            //如果在所有节点里面也添加上末尾节点则需要写一个新的构造函数，将末尾节点传进去，替换下面这个构造函数
            this.tailNode.nextNode = node;
            this.tailNode = tailNode.nextNode;
        }
    }

    private static class Node{
        /**
         * 存储内容
         */
        private Label label;

        /**
         * 下一个节点
         */
        private Node nextNode;

        public Node() {

        }

        private Node(Label label) {
            this.label = label;
        }

    }

}

class Label {
    /**
     * 标签名
     */
    private String labelName;

    /**
     * 标签的属性
     */
    private Map<String, String> attrsMap;

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public Label(String labelName, Map<String, String> attrsMap) {
        this.labelName = labelName;
        this.attrsMap = attrsMap;
    }

}
