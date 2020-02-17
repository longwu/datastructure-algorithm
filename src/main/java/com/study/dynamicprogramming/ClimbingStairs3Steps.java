package com.study.dynamicprogramming;

/**
 * 动态规划问题 - 爬楼梯 3层
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
public class ClimbingStairs3Steps {

    public static void main(String[] args) {
        System.out.println(climbStairsByRecursion(3));
        System.out.println(climbStairsByRecursion(4));
        System.out.println(climbStairsByRecursion(5));

        System.out.println(climbStairsByLoop(3));
        System.out.println(climbStairsByLoop(4));
        System.out.println(climbStairsByLoop(5));

        // 递归走法
        System.out.println(climbStairsByRecursion2(5));

        //动态规划走法
        System.out.println(climbStairsByArray(5));
    }

    /**
     * 使用迭代的解法, 只用到了3个变量
     * <p>
     * 时间复杂度为O(n),空间复杂度为O(1)
     *
     * @param n
     * @return
     */
    private static int climbStairsByLoop(int n) {
        // 第0-3层的走法 已经知道
        if (n == 0 || n == 1 || n == 2)
            return n;
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
     * 动态规划的解法, 使用数组来存储每n层可以走的方式
     *
     * @param n
     * @return
     */
    private static int climbStairsByArray(int n) {
        // 临界条件
        // 第1层和第2层各有1和2种走法
        if (n == 0 || n == 1 || n == 2)
            return n;
        // 第3层有4种走法
        if (n == 3)
            return 4;

        // 定义状态
        int[] arr = new int[n];

        // 初始状态
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;

        // 状态推导
        // 从第4个层开始计算
        for (int i = 3; i < n; i++) {
            // 将前3个元素相加得到第4个元素
            // 此外下一轮循环由于i+1了,所以这轮的i自动变成了下一轮的i-1,这轮的i-1变成了下一轮的i-2...
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }

        // 得出结果
        // 返回第n个元素, 因为数组是从第0个元素开始的
        return arr[n - 1];
    }

    /**
     * 使用递归的解法
     *
     * 时间复杂度 O(3^n)
     *
     * @param n
     * @return
     */
    private static int climbStairsByRecursion(int n) {
        // 如果是0,1,2层楼梯, 走法都刚好为层数
        if (n == 0 || n == 1 || n == 2) {
            return n;
            // 如果是3层楼梯,走法为4种
        } else if (n == 3) {
            return 4;
        }

        return climbStairsByRecursion(n - 1) + climbStairsByRecursion(n - 2) + climbStairsByRecursion(n - 3);
    }


    private static int climbStairsByRecursion2(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        } else if (n == 3) {
            return 4;
        }

        return climbStairsByRecursion2(n - 1) + climbStairsByRecursion2(n - 2) + climbStairsByRecursion2(n - 3);
    }
}
