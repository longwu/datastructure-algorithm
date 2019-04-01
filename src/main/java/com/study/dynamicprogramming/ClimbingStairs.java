package com.study.dynamicprogramming;

/**
 * 动态规划问题 - 爬楼梯
 * <p>
 * 问题: 有n层台阶, 每次可以走1层或2层或3层, 求有多少种走法
 * <p>
 * 可以通过回溯法或者动态规划来解
 * <p>
 * <p>
 * 台阶数 --> 走法数量
 * 1 --> 1
 * 2 --> 11 2
 * 3 --> 111 12 21 3
 * <p>
 * https://time.geekbang.org/course/detail/130-69779
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs2(3));
        System.out.println(climbStairs2(4));
        System.out.println(climbStairs2(5));

        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

    private static int climbStairs(int n) {
        // 第0-3层的走法 已经知道
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 3;
        if (n == 3)
            return 4;

        int f1 = 1;
        int f2 = 2;
        int f3 = 4;

        int result = 0;
        // 从第4层开始计算
        for (int i = 4; i <= n; i++) {
            result = f1 + f2 + f3;
            f1 = f2;
            f2 = f3;
            f3 = result;
        }

        return result;
    }

    /**
     * 使用递归的解法
     *
     * @param n
     * @return
     */
    private static int climbStairs2(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        } else if (n == 3) {
            return 4;
        }

        return climbStairs2(n - 1) + climbStairs2(n - 2) + climbStairs2(n - 3);
    }
}
