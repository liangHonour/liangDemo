package com.liang.xmlParse;


import java.util.HashMap;

public class LabelAttrs {
    private HashMap<String, Attrs> labelAttrs;

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

    private class Attrs {
        private final HashMap<String, String> checkAttributes;
        private final HashMap<String, String> updateAttributes;

        private Attrs() {
            this.checkAttributes = new HashMap<>();
            this.updateAttributes = new HashMap<>();
        }

        private void setLabelUpdateAttributes(String updateAttributesName, String updateAttributesValue) {
            if (updateAttributes.containsKey(updateAttributesName)) {
                System.out.println("不允许传入相同标签");
                return;
            }
            this.updateAttributes.put(updateAttributesName, updateAttributesValue);
        }

        private void setLabelCheckAttributes(String checkLabelAttributeName, String checkLabelAttributeValue) {
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

}
