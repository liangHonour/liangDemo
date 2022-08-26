package com.liang.easy.tree;

import java.util.ArrayList;

public class Three<T> {
    private T node;
    private Three<T> leftThree;
    private Three<T> rightThree;

    public Three() {

    }

    public Three(T node, Three<T> leftThree, Three<T> rightThree) {
        this.node = node;
        this.leftThree = leftThree;
        this.rightThree = rightThree;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public Three<T> getLeftThree() {
        return leftThree;
    }

    public void setLeftThree(Three<T> leftThree) {
        this.leftThree = leftThree;
    }

    public Three<T> getRightThree() {
        return rightThree;
    }

    public void setRightThree(Three<T> rightThree) {
        this.rightThree = rightThree;
    }

    public ArrayList<T> preorderTraversal(Three<T> three) {
        ArrayList<T> returnList = new ArrayList<>();
        returnList.add(three.getNode());
        preorderTraversal(returnList, three);
        return returnList;
    }

    private void preorderTraversal(ArrayList<T> returnList, Three<T> three) {
        if (three.leftThree != null) {
            Three<T> leftThree = three.leftThree;
            returnList.add(leftThree.getNode());
            preorderTraversal(returnList, leftThree);
        } else if (three.rightThree != null) {
            Three<T> rightNode = three.rightThree;
            returnList.add(rightNode.getNode());
            preorderTraversal(returnList, rightNode);
        }
    }

    public void inorderTraversal() {

    }

    public void PostorderTraversal() {

    }
}
