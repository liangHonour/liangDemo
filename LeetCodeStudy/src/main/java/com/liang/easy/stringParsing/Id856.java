package com.liang.easy.stringParsing;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 * <p>
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 * <p>
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 * <p>
 * 输入： "(()(()))"
 * 输出： 6
 */


public class Id856 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String value;
        Solution solution = new Solution();
        while (true) {
            value = sc.next();
            if (value.equals("break")) {
                break;
            }
            int i = solution.scoreOfParentheses(value);
            System.out.println(i);
        }
    }

    /**
     * 解题思路，可以当作一课树，一个()就是一个外层的树，里面的()就是子树，则可以采用深度优先搜索算法进行解决
     */
    static class Solution {
        int idx = 0;

        public int scoreOfParentheses(String s) {
            int value = 0;
            while (idx < s.length() && s.charAt(idx) == '(') {
                //跳过进入的'('
                idx++;
                //没有子元素
                if (s.charAt(idx) == ')') {
                    //内容加一
                    value++;
                } else {
                    //说明有子元素，需要对子元素里面的数值*2
                    value += scoreOfParentheses(s) << 1;
                }
                //跳过出来的‘）’
                idx++;
            }
            return value;
        }
    }
}
