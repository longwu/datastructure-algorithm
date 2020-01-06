package com.study.number;

/**
 * 计算N的阶层
 *
 * https://time.geekbang.org/course/detail/130-42710
 */
public class FactorialN {

    public static void main(String[] args) {
        int n = 5;
        int result = factorialN(n); // 5 * 4 * 3 * 2 * 1
        System.out.println(String.format("%d的阶层为: %d", n, result));
        result = factorialNByLoop(n);
        System.out.println(String.format("%d的阶层为: %d", n, result));
    }

    /**
     * 计算n的阶层 n! = 1 * 2 * 3... * n 或者 n * (n-1) * (n-2) ... * 2 * 1
     * 使用递归的方式
     *
     * @param n
     * @return
     */
    private static int factorialN(int n) {
        // 递归结束条件是n==1
        if (n == 1)
            return 1;
        // 由于factorialN调用自己, 所以每次进来n都比上一次少1
        return n * factorialN(n - 1); // 通过 n * (n -1) * (n - 2) * .... * 1
    }

    /**
     * 使用迭代的方式来实现递归的效果
     *
     * @param n
     * @return
     */
    private static int factorialNByLoop(int n) {
        int result = 1;
        while (n >= 1) {// 当n减少到1的时候退出循环
            result = result * n;
            n--;// 每次减一
        }
        return result;
    }
}
