package com.study.number;

/**
 * 测试一个数是否是质数(素数)
 * <p>
 * 质数是指只能那些只能整除1和自己的数字, 比如 2,3,5,7,11,13,17...(1不算质数)
 */
public class Prime {
    public static void main(String[] args) {
        int a = 2;
        //int a = 11;
        //int a = 4;

        //System.out.println(a + (isPrime(a) ? "是" : "不是") + "质数");

        //System.out.println(a + (isPrime2(a) ? "是": "不是") + "质数");

        System.out.println(a + (isPrime2_1(a) ? "是": "不是") + "质数");

        //System.out.println(a + (isPrime3(a) ? "是" : "不是") + "质数");
    }

    public static boolean isPrime(int n) {
        int x = 0;
        for (int i = 2; i < n; i++) {
            // 从2开始计算每次n无法整除数字的个数
            if (n % i != 0) {
                x++;
            }
        }
        // 如果无法整数的数字个数刚好是n-2个, 说明从2-n之间没有能被整除的数字, 则这个数字为质数
        return x == n - 2;
    }


    public static boolean isPrime2(int n) {
        // 1不是质数
        if (n <= 1) {
            return false;
        }

        // 将n除以比自己小的数(不包括1),如果不能整除,则为质数
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * 判断n是否为质数, 只要n对任何2-n的数取余都不为0即可
     *
     * @param n
     * @return
     */
    private static boolean isPrime2_1(int n) {
        if (n <= 1)
            return false;

        // 遍历2到n的所有元素, 如果有n能取余为0的元素, 那么n都不是质数
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }


    /**
     * 优化后的算法, 只需要判断从2到该数的根号之前的整数即可
     * 求开方根 会导致结果不准
     *
     * @param n
     * @return
     */
//    public static boolean isPrime3(int n) {
//        // 1不是质数
//        if (n <= 1) {
//            return false;
//        }
//
//        // 求开方根 会导致结果不准
//        // 将n开根号
//        double s = Math.sqrt(n);
//        // 如果 2到n的根号数之内的数 都不能被n整除, 那么n为质数
//        for (int i = 2; i <= s; i++) {
//            if (n % i == 0)
//                return false;
//        }
//        return true;
//    }
}
