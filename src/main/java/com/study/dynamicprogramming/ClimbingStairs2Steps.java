package com.study.dynamicprogramming;

/**
 * 动态规划问题 - 爬楼梯
 * <p>
 * 问题: 有n层台阶, 每次可以走1层或2层 求有多少种走法
 * <p>
 *
 * 每层楼梯的走法为前两层走法之和
 *
 * 和斐波拉契数列是相同的解法
 * f(n) = f(n-1) + f(n-2)
 * f(0) = f(1) = 1
 */
public class ClimbingStairs2Steps {

    public static void main(String[] args) {

        int n =5;
        System.out.println(calcStepsByRecursion(n));
        System.out.println(calcStepsByLoop(n));
        System.out.println(calcStepsByArray(n));
    }

    /**
     * 使用递归的方式进行计算 f(n) = f(n-1) + f(n-2)
     * 缺点: 重复创建了大量元素 且每一层计算都需要递归2次
     * <p>
     * 时间复杂度为O(2^n)
     *
     * @param n
     * @return
     */
    private static int calcStepsByRecursion(int n) {
        if (n == 0 || n == 1 || n == 2)
            return n;

        return calcStepsByRecursion(n - 1) + calcStepsByRecursion(n - 2);
    }

    /**
     * 使用迭代的方式, 时间复杂都为O(n), 只用了3个变量 空间复杂度为O(1)
     * @param n
     * @return
     */
    private static int calcStepsByLoop(int n) {
        if (n == 0 || n == 1 || n == 2)
            return n;

        int f1 = 1;
        int f2 = 2;
        int f3 = 0;

        // 从第3层开始计算, 每层结果为前两层之和
        for (int i = 2; i < n; i++) {
            f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    /**
     * 使用动态规划的方式, 数组来存储每个元素,节省了大量临时变量的创建
     *
     * 时间复杂度为O(n),空间复杂度为O(n)
     *
     * @param n
     * @return
     */
    private static int calcStepsByArray(int n) {
        if (n == 0 || n == 1 || n == 2)
            return n;

        // 创建n个元素的数组
        int[] arr = new int[n];

        arr[0] = 1;
        arr[1] = 2;

        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        // 返回第n个元素, 数组是从0开始的
        return arr[n - 1];
    }
}
