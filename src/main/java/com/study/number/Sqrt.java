package com.study.number;

/**
 * x 的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
public class Sqrt {

    public static void main(String[] args) {
        //int a = (0 + 2 + 1) / 2;
        //int a = (0 + 2 + 1) >>> 1;
        //System.out.println(a);


        int x = 1;
        //int x = 3;
        //int x = 4;
        //int x = 8;
        //int x = 2;
        //int x = 2147395599;
        //System.out.println(String.format("%d的平方根为: %d", x, (int)Math.sqrt(x)));
        //System.out.println(String.format("%d的平方根为: %d", x, mySqrt(x)));
        //System.out.println(String.format("%d的平方根为: %d", x, mySqrt2(x)));
        //System.out.println(String.format("%d的平方根为: %d", x, mySqrtForcely(x)));

        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
        x = 2;
        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
        x = 3;
        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
        x = 4;
        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
        x = 5;
        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
        x = 9;
        System.out.println(String.format("%d的平方根为: %d", x, mySqrt_2(x)));
    }

    /**
     * 使用暴力法一个个尝试, 范围为从 x/2 +1 到  0
     * 时间复杂度为O(N)
     * 不推荐, 容易超时
     *
     * @param x
     * @return
     */
    private static int mySqrtForcely(int x) {
        // 考虑到result * result 会超过int 最大值范围, 这里需要使用long类型来存储
        // 平方根一定小于平方值的一半+1
        long result = x / 2 + 1;    // +1是为了处理x为1的特殊情况

        while (result > 0) {
            long square = result * result;

            // 如果平方和大于x, 就每次减1, 一个个尝试直到成功
            if (square > x) {
                result--;
            } else {
                break;
            }
        }
        return (int) result;
    }

    /**
     * 通过二分查找提高快速定位值
     * <p>
     * 时间复杂度为O(logN)
     *
     * @param x
     * @return
     */
    private static int mySqrt(int x) {
        // 为了照顾到0,将左边界值设为0
        long left = 0;
        // 为了照顾到1,将有右界值设为1
        long right = x / 2 + 1;

        while (left < right) {
            // 这里中位数 左边范围值+右边范围值+1, 往右取中位数, 往左取中位数可能会因为少取 导致获取不到结果
            long mid = (left + right + 1) / 2;
            long square = mid * mid;
            // 如果中位数的平方大于该数, 就将最大范围缩小,改为中间值-1
            if (square > x)
                right = mid - 1;
            else // 由于x的平方根很难刚好为整数,所以有时候取不到相等的值, 所以只能是无限接近左边
                // 如果中位数的平方小于该数, 就将最小范围调大,改为中位数
                left = mid; //给结果赋值中位数是因为结果可能是小数, 它大于中位数,但是小于中位数+1. 而且该中位数折半之前也是+1的,比实际中位数大一点
        }
        // 返回一个相等或者最接近的值
        return (int) left;
    }


    private static int mySqrt_2(int x){
        long left = 0;// 处理左边界x=0的情况
        long right = x/2 +1; //处理右边界x=1的情况

        while(left < right){
            long mid = (left + right + 1) /2; //往右取中位数
            long square = mid * mid;
            if(square > x){
                right = mid -1;
            }else
                left = mid;
        }
        return (int)left;
    }


    private static int mySqrt3(int x) {
        // 处理1的特殊情况
        if (x == 1)
            return x;

        long left = 0;
        long right = x / 2; //这里无法处理x=1的情况

        while (left < right) {
            // 这里中位数 左边范围值+右边范围值+1, 往右取中位数, 往左取中位数可能会因为少取 导致获取不到结果(出现死循环)
            long mid = (left + right +1) / 2;
            long square = mid * mid;
            // 如果中位数的平方大于该数, 就将最大范围缩小,改为中间值-1
            if (square > x)
                right = mid - 1;
            else // 由于x的平方根很难刚好为整数,所以有时候取不到相等的值, 所以只能是无限接近左边
                // 如果中位数的平方小于该数, 就将最小范围调大,改为中位数
                left = mid; //给结果赋值中位数是因为结果可能是小数, 它大于中位数,但是小于中位数+1. 而且该中位数折半之前也是+1的,比实际中位数大一点
        }
        // 返回一个相等或者最接近的值
        return (int) left;
    }
}
