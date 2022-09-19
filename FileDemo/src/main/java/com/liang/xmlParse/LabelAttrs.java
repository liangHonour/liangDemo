package com.liang.xmlParse;


import java.util.HashMap;
import java.util.LinkedList;

class LabelAttrs {
    private final HashMap<String, Attrs> labelAttrs;

    public LabelAttrs() {
        labelAttrs = new HashMap<>();
    }

    public void setLabelUpdateAttributes(String labelUrl, HashMap<String, String> updateAttributes) {
        if (updateAttributes.isEmpty()) {
            return;
        }
        if (!labelAttrs.containsKey(labelUrl)) {
            Attrs attrs = new Attrs();
            labelAttrs.put(labelUrl, attrs);
        }
        Attrs attrs = labelAttrs.get(labelUrl);
        attrs.updateAttributes.putAll(updateAttributes);

    }

    /**
     * @param labelUrl 标签名
     * @param args     标签的参数 传入的规范应该是 "user=liang" user 是标签中的属性名，liang则是该属性的参数。
     */
    public void setLabelUpdateAttributes(String labelUrl, String... args) {

    }

    public void setLabelCheckAttributes(String labelUrl, HashMap<String, String> checkAttributes) {
        if (checkAttributes.isEmpty()) {
            return;
        }
        if (!labelAttrs.containsKey(labelUrl)) {
            Attrs attrs = new Attrs();
            labelAttrs.put(labelUrl, attrs);
        }
        Attrs attrs = labelAttrs.get(labelUrl);
        attrs.checkAttributes.putAll(checkAttributes);


    }

    public HashMap<String, String> getCheckAttributes(String labelName) {
        if (labelAttrs.isEmpty()) {
            return new HashMap<>();
        }
        return labelAttrs.get(labelName).checkAttributes;
    }

    public HashMap<String, String> getUpdateAttributes(String labelName) {
        if (labelAttrs.isEmpty()) {
            return new HashMap<>();
        }
        return labelAttrs.get(labelName).updateAttributes;
    }

    private boolean propertySplit(String arg, String key, String value) {
        if (arg == null || !arg.contains("=")) {
            return false;
        }
        key = arg.split("=")[0];
        value = arg.split("=")[1];
        return true;
    }

    private class Attrs {
        private final HashMap<String, String> checkAttributes;
        private final HashMap<String, String> updateAttributes;

        private Attrs() {
            this.checkAttributes = new HashMap<>();
            this.updateAttributes = new HashMap<>();
        }
    }

}
