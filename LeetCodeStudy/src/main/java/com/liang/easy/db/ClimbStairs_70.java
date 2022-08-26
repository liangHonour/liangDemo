package com.liang.easy.db;

import org.junit.Test;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs_70 {

    @Test
    public void test() {
        this.climbStairs(9);
    }

    /**
     * f[n] = f[n-1] + f[n-2]
     *
     * @param n 楼梯层数
     * @return 到达的方式次数
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        System.out.println(f[n]);
        return f[n];
    }
}
