package com.study.bit;

/**
 * 十进制数字转换成二进制, 并求出二进制上面1的个数
 * <p>
 * 推荐使用位运算
 * <p>
 * 什么是位运算, 程序中的所有数在计算机内存中都是以二进制的形式储存的,位运算说穿了,就是直接对整数在内存中的二进制位进行操作.
 * 比如, and运算本来是一个逻辑运算符,但整数与整数之间也可以进行and运算.
 * 举个例子, 6的二进制是110, 11的二进制是1011, 那么6 and 11的结果就是2, 它是二进制对应位进行逻辑运算的结果(0表示False,1表示True,空位都当0处理)
 * <p>
 * 由于计算机本身是使用二进制进行计算, 所以位运算处理速度非常之快
 * <p>
 * https://leetcode-cn.com/problems/number-of-1-bits
 */
public class NumberOfOneBits {
    public static void main(String[] args) {

        System.out.println(count(10)); // 1010   2
        System.out.println(count(11)); // 1011   3

        System.out.println(count2(10)); // 1010   2
        System.out.println(count2(11)); // 1011   3


        System.out.println(count3(10)); // 1010   2
        System.out.println(count3(11)); // 1011   3
    }

    private static int count(int num) {
        int result = 0;

        //整数的最大值为2的32次方, 二进制最大长度为2的32次方
        for (int i = 0; i < 32; i++) {
            //判断移位后的最后一位是否为1
            //if (num % 2  == 1) {  //使用取模的方法判断最后一位是否为1
            /**
             * 比如 num = 5
             * 5:   101
             * 1:     1
             * 5&1: 001 //全部为1才是1,否则为0
             * 一个数和1进行 &与 操作的时候,只有二进制最后1位是1的时候结果的最后一位才能得到1
             */
            if ((num & 1) == 1) { //使用位运算判断最后一次是否为1
                result++;
            }
            // 将num的每1位往右边移动1位
            // 比如 1010 >> 1 第一次移位变成: 101(5)  第2次移位变成: 10(2)  第3次移位变成: 1(1) 第4次移位变成 0(0)
            num = (num >> 1);
        }
        return result;
    }

    private static int count2(int num) {
        int result = 0;

        //当数字等于0的时候退出循环
        while (num != 0) {
            if ((num & 1) == 1) {
                result++;
            }
            //往右逻辑移动一位, 每次移动后数字都会变小, 左边不足的用0补全
            // 比如 1010 >> 1 第一次移位变成: 0101(5)  第2次移位变成: 0010(2)  第3次移位变成: 0001(1) 第4次移位变成 0000(0)
            //num = (num >>> 1);
            num >>>=1;
        }
        return result;
    }

    /**
     * 最快速
     *
     * @param tag
     * @return
     */
    public static int count3(int tag) {
        int count = 0;
        while (tag != 0) {
            /**
             *
             *  10   1010
             *  9    1001
             *  5&4: 1000  = 8
             *
             *  8 & (8-1) = 8 & 7
             *
             *  8    1000
             *  7    0111
             *  8&7: 0000  = 0
             *
             */
            tag = (tag & (tag - 1));
            count++;
        }
        return count;
    }
}
