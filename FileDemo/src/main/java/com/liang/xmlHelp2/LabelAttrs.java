package com.liang.xmlHelp2;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LabelAttrs {

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

    private LabelAttrs(Label node) {
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
        Label label = null;
        if (attrs.length == 0) {
            label = new Label(labelName, attrMap);
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
            label = new Label(labelName, attrMap);
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

    public Label getNode() {
        return node;
    }

    public void setNode(Label node) {
        this.node = node;
    }

    public LabelAttrs getNext() {
        return next;
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

