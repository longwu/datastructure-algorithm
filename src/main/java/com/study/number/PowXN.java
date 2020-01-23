package com.study.number;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 */
public class PowXN {

    /**
     * 解法: 一种是暴力解法, 利用循环或者递归的方式 将x乘以自身n次.  时间复杂度O(n)
     *       另一种是用递归+分治的解法, 将递归的次数减少到logN次.
     * 注意: 需要考虑n为负数和奇偶数的情况.
     *
     * @param args
     */
    public static void main(String[] args) {
        double result = myPow(2.1000, 10);
        System.out.println(result);
//
//        double result = myPow2(2.1000, 3);
//        System.out.println(result);

        //double result = myPow3(2.1000, -3);
//        double result = myPow3(2.1000, 10);
//        System.out.println(result);

//        result = myPowRecursion(2.1000, 10);
//        System.out.println(result);

        //result = myPowRecursion(2, 10);
        result = myPowRecursion(2.1000, 10);
        System.out.println(result);

//        result = myPowRecursion2(2, 10);
//        System.out.println(result);
    }

    /**
     * 暴力法, 循环将x乘以自己n次, 时间复杂度O(n)
     * 空间复杂度O(1) 只需要一个变量来保存最终x的连乘结果
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        double result = 1;

        // 正数次幂
        if (n > 0) {
            while (n > 0) {
                result *= x;
                n--;
            }
            // 负数次幂
        } else {
            n = -n;
            while (n > 0) {
                result *= x;
                n--;
            }
            result = 1 / result;
        }
        return result;
    }

    /**
     * 暴力法, 优化n为负数的情况.
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            // 当n为负数的情况下, 实际上就是 1/x 的n次方
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;// 将x乘以自身n次
        return ans;
    }

    /**
     * 暴力法(优化), 乘以自己的次数改为n/2次, x的2n次方 = x的n次方 * x的n次方.
     * 如 temp = x的n/2次方 然后结果为temp * temp即可. 比如 5的10次方 改为 a = 5的5次方, a * a = 5的10次方
     * 这里需要考虑n为偶数还是奇数, 如果为偶数, 那结果为temp * temp; 如果为奇数需要 n/2实际上为(n-1)/2, 所以结果为 temp * temp * x;
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            // 当n为负数的情况下, 实际上就是 1/x 的n次方
            x = 1 / x;
            N = -N;
        }
        double half = 1;
        // 循环 n / 2次, 这里如果n为偶数, 则结果为n的一半, 如果是奇数, 结果为n-1的一半
        for (long i = 0; i < N / 2; i++)
            half = half * x;// 将x乘以自身 n/2 次

        // 如果n是偶数
        if (n % 2 == 0) {
            half = half * half; //直接temp的平方即可
        } else {
            // 如果n为奇数
            half = half * half * x; // temp的平方还需要乘以x
        }
        return half;
    }


    /**
     * 使用递归 + 分治法, 时间复杂度O(logn), 每一次我们使用公式 x的n/2次, n都为原来的一半, 所以之多O(log n)次即可求出结果
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPowRecursion(double x, int n) {
        // 处理n为负数的情况
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
        //return fastPow2(x, N);
        //return fastPowByLoop(x, N);
    }

    public static double fastPow(double x, long n) {
        // 当n/2 不断递归除尽后, n==0, 返回1.0
        if (n == 0) {
            System.out.println("往内递归结束 n= " + n);
            return 1.0;
        }
        System.out.println("往内递归 n= " + n);
        // 使用递归, 将循环乘以x的次数减少到logn次 (类似于二分查找)
        double half = fastPow(x, n / 2);//每次递归的n都是上一次的1/2
        double beforeHalf = half;
        // 每一次执行会将上一递归的half结果拿来相乘,这样每次的half都是上一次的half的平方或者平方乘以x
        // 需要考虑n为偶数和奇数的情况
        if (n % 2 == 0) {
            half = half * half;
            System.out.println(String.format("往外递归 n = %d, half = %s * %s = %s", n, beforeHalf, beforeHalf, half));
        } else {
            half = half * half * x;
            System.out.println(String.format("往外递归 n = %d, half = %s * %s * %s = %s", n, beforeHalf, beforeHalf, x, half));
        }
        return half;
    }

//    /**
//     * 迭代实现分治的算法有问题, 需要修改
//     * @param x
//     * @param n
//     * @return
//     */
////    public static double fastPowByLoop(double x, long n) {
//        double result = x;
//        while (n >= 2) {
//            if (n % 2 == 0) {
//                result = result * result;
//            } else {
//                result = result * result * x;
//            }
//            // n = 1的时候 n /2 没有任何意义
//            n = n / 2;
//        }
//        return result;
//    }


    public static double fastPow2(double x, long n) {
        if (n == 0) {
            return 1.0;
        }

        double half = fastPow2(x, n / 2);

        if (n % 2 == 0) {
            half = half * half;
        } else {
            half = half * half * x;
        }
        return half;
    }


    /**
     * 暴利法(递归) 利用递归的方式将 x乘以自己n次, 效果和迭代一样. 时间复杂度O(n)
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPowRecursion2(double x, int n) {
        // 处理n为负数的情况
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return slowPow(x, N);
    }

    public static double slowPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }

        // 从外往内 找终止条件
        double result = slowPow(x, n - 1);
        // 从内往外递归 真正开始计算
        result *= x;

        return result;
    }
}
