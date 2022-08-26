package com.liang.easy.tree.util;

import com.liang.easy.tree.Three;

public class Util {

    private static int flag = 0;

    public Three<Integer> getTestThree(int node) {
        Three<Integer> testThree = new Three<>(flag++, new Three<>(), new Three<>());
        flag = 0;
        return testThree;
    }

    private void getTestThree(Three<Integer> three, int node) {
        if (flag == node) return;
        Three<Integer> leftThree = new Three<>(flag++, new Three<>(), new Three<>());
        Three<Integer> rightThree = new Three<>(flag++, new Three<>(), new Three<>());
        three.setLeftThree(leftThree);
        three.setRightThree(rightThree);
    }
}
