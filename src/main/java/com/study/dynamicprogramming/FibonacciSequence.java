package com.study.dynamicprogramming;

/**
 * 斐波拉契
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列、因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，故又称为“兔子数列”，
 * 指的是这样一个数列：1、1、2、3、5、8、13、21、34、……
 * <p>
 * 临界值: 第1和2个数
 *
 * <p>
 * https://www.jianshu.com/p/25ca6afa2fa4
 * https://www.cnblogs.com/swfpt/p/6850396.html
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        int n = 1;
        //System.out.println(String.format("第%d个数为: %d", n, fibByRecursion(n));
        //System.out.println(String.format("第%d个数为: %d", n, fibByLoop(n)));
        System.out.println(String.format("第%d个数为: %d", n, fibByDP(n)));

        n = 5;
        //System.out.println(String.format("第%d个数为: %d", n, fibByRecursion(n));
        //System.out.println(String.format("第%d个数为: %d", n, fibByLoop(n)));
        System.out.println(String.format("第%d个数为: %d", n, fibByDP(n)));
        n = 6;
        //System.out.println(String.format("第%d个数为: %d", n, fibByRecursion(n));
        //System.out.println(String.format("第%d个数为: %d", n, fibByLoop(n)));
        System.out.println(String.format("第%d个数为: %d", n, fibByDP(n)));
    }

    /**
     * 求第n个数的值, 使用分治的方法 使用两个递归分别求出n-1和n-2两数的值,然后进行相加得到第n个数的值
     * <p>
     * 缺点: 当n比较大时递归非常慢，因为递归过程中存在很多重复计算。
     * <p>
     * 时间复杂度为O(2^n)
     *
     * @param n
     * @return
     */
    private static int fibByRecursion(int n) {
        if (n == 0)
            return 0;

        // 斐波拉契的第1和第2个数为1
        // 临界条件 n = 1 和 n=2
        if (n == 1 || n == 2) {
            System.out.println(String.format("往内递归结束:第%d个数", n));
            return 1;
        }
        System.out.println(String.format("往内递归,第%d个数", n));
        int result = fibByRecursion(n - 1) + fibByRecursion(n - 2); //第n个数为前两个数之后 比如 5 = 3 +２
        System.out.println(String.format("往外递归计算开始: 第%d个数为%d", n, result));
        return result;
    }

    /**
     * 使用迭代的方式, 保存之前计算的结果, 用空间换时间
     * <p>
     * 时间复杂度为 O(n), 相比递归要快很多
     *
     * @return
     */
    private static int fibByLoop(int n) {

        if (n == 0)
            return 0;

        if (n == 1 || n == 2)
            return 1;

        int f1 = 1;
        int f2 = 1;
        // 1 1 2 3 5 8 ...
        // 从第3个元素开始计算,因为前两个元素值已知
        for (int i = 3; i <= n; i++) {
            int f3 = f1 + f2; // 记录当前两个元素之和,为下轮的第2个元素
            // 每一次循环都将当前两个元素往后移动一位
            f1 = f2;// 下轮的第1个元素为本轮的第2个元素
            f2 = f3; // 下轮的第2个元素为本轮的第3个元素
            System.out.println(String.format("fibByLoop:第%d个数为%d", i, f2));
        }
        return f2;
    }

    /**
     * 使用斐波拉契来计算动态规划, 使用一个数组来存储整个斐波拉契数列, 这样的做法可以节省大量中间变量的创建, 而且时间复杂度和循环一样
     * <p>
     * 时间复杂度为O(n), 空间复杂度为O(n)
     *
     * @return
     */
    private static int fibByDP(int n) {
        if (n == 0)
            return 0;

        if (n == 1 || n == 2)
            return 1;

        int[] arr = new int[n];

        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
            System.out.println(String.format("fibByDP:第%d个数为%d", i + 1, arr[i]));
        }

        return arr[n - 1];
    }
}
