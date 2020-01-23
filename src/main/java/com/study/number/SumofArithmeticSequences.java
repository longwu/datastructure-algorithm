package com.study.number;

/**
 * n的等差数列求和
 */
public class SumofArithmeticSequences {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(sum(n));
        System.out.println(sum2(n));
    }

    /**
     * 使用递归实现 n的等差数列求和
     *
     * @param n
     * @return
     */
    private static int sum(int n) {
        // 当n=1的时候 结束迭代
        if (n == 1){
            System.out.println("递归往内终止, n = 1");
            return 1;
        }
        System.out.println("递归往内直到终止条件, n = " + n);
        int result =  n + sum(n - 1); // n + (n-1) + (n-2) + ... + 1;
        System.out.println(String.format("开始真正计算结果, n= %d, result = %d", n, result));
        return result;
    }

    /**
     * 使用迭代实现 n的等差数列求和
     *
     * @param n
     * @return
     */
    private static int sum2(int n) {
        int result = 0;
        while (n >= 1) {
            result += n;
            n--;
        }
        return result;
    }

}
