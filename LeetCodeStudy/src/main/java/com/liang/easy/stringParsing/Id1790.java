package com.liang.easy.stringParsing;

/**
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 *<p>
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 *
 *
 *<p>
 * 示例 1：
 *<p>
 * 输入：s1 = "bank", s2 = "kanb"
 * <p>
 * 输出：true
 * <p>
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 * <p>
 *
 * 示例 2：
 *<p>
 * 输入：s1 = "attack", s2 = "defend"
 * <p>
 * 输出：false
 * <p>
 * 解释：一次字符串交换无法使两个字符串相等
 * <p>
 * 示例 3：
 *<p>
 * 输入：s1 = "kelb", s2 = "kelb"<p>
 * 输出：true<p>
 * 解释：两个字符串已经相等，所以不需要进行字符串交换<p>
 * 示例 4：<p>
 * 输入：s1 = "abcd", s2 = "dcba"<p>
 * 输出：false<p>
 */
public class Id1790 {
    public static void main(String[] args) {
        System.out.println(areAlmostEqual("bank", "kanb"));
    }

    public static boolean areAlmostEqual(String s1, String s2) {
        int a = 0, b = 0, flag = 0;
        for (int i = 0; i < s1.length(); i++) {
            int c = s1.charAt(i) - 97;
            int d = s2.charAt(i) - 97;
            if (c != d) {
                flag++;
                if (flag > 2) {
                    return false;
                }
                a = a * 100 + c;
                if (flag == 2) {
                    d *= 100;
                }
                b = b + d;
            }
        }
        return a == b;
    }
}
