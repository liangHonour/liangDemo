package com.liang.xmlHelp2;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LabelAttrs {
    private final LinkedList<Label> labels;

    public LabelAttrs() {
        this.labels = new LinkedList<>();
    }

    /**
     * 获取当前的标签
     *
     * @return 标签的属性
     */
    public Label getAttrs() {
        if (isEmpty()) {
            return null;
        }
        return labels.pop();
    }

    public boolean isEmpty() {
        return labels.isEmpty();
    }


    public void addLabel(String labelName, String... attrs) {
        HashMap<String, String> attrMap = new HashMap<>();
        if (attrs.length == 0) {
            labels.add(new Label(labelName, attrMap));
            return;
        }
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
        labels.add(new Label(labelName, attrMap));
    }

    class Label {
        public String labelName;
        public Map<String, String> attrs;

        Label(String labelName, Map<String, String> attrs) {
            this.labelName = labelName;
            this.attrs = attrs;
        }
    }
}
