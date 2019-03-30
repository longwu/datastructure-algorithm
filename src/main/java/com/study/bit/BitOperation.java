package com.study.bit;

/**
 * 什么是位运算, 程序中的所有数在计算机内存中都是以二进制的形式储存的,位运算说穿了,就是直接对整数在内存中的二进制位进行操作.
 * 比如, and运算本来是一个逻辑运算符,但整数与整数之间也可以进行and运算.
 * 举个例子, 6的二进制是110, 11的二进制是1011, 那么6 and 11的结果就是2, 它是二进制对应位进行逻辑运算的结果(0表示False,1表示True,空位都当0处理)
 * <p>
 * 由于计算机本身是使用二进制进行计算, 所以位运算处理速度非常之快
 * <p>
 * 常用的二进制操作:
 * <p>
 * 1. 判断奇偶数  X & 1 ==1 OR ==0 直接判断二进制最后一位数是0还是1 就知道是偶数还是奇数, 比如6是110 偶数  11是1011 奇数
 * <p>
 * <p>
 * <p>
 * https://time.geekbang.org/course/detail/130-67645
 * <p>
 * 干货！史上最强位运算面试题大总结！
 * https://www.colabug.com/598140.html
 */
public class BitOperation {

    public static void main(String[] args) {
        // 110 AND 1011 -> 0010(b) --> 2(d)
//        System.out.println(6 & 11); // 6的二进制是110, 11的二进制是1011, 那么6 and 11的结果就是2

        // 左移操作A<<B
        // 将A的二进制表示的每一位向左移B位，左边超出的位截掉，右边不足的位补0
        int A = 12; //二进制为 1100
        int B = 2;
        int C = 1;
        // 将1100 往左移动两位  变成 110000
        System.out.println(A << B); // 48
        System.out.println(decimalToBinary(A << B)); //110000

        // 将1100 往左移动1位  变成 11000
        System.out.println(A << C); // 24
        System.out.println(decimalToBinary(A << C)); // 11000

        // 右移操作 A>>B
        // 算术右移：将A的二进制表示的每一位向右移B位，右边超出的位截掉，左边不足的位补符号位的数。
        // 1100 往右移动两位  变成  11
        System.out.println(A >> B); //3
        System.out.println(decimalToBinary(A >> B)); // 11

        // 1100 往右移动一位 变成 110
        System.out.println(A >> C); // 6
        System.out.println(decimalToBinary(A >> C)); // 110

        // 逻辑右移 A>>>B
        // 逻辑右移：将A的二进制表示的每一位向右移B位，右边超出的位截掉，左边不足的位补0。
        // 1100 往右移动两位 变成 0011
        System.out.println(A >>> B); //3
        System.out.println(decimalToBinary(A >>> B)); // 0011

        // 1100 往右移动一位 变成 0110
        System.out.println(A >>> C); // 6
        System.out.println(decimalToBinary(A >>> C)); // 0110

        // 按位与操作 A & B
        // 将A和B的二进制表示的每一位进行与操作，只有两个对应的二进制位都为1时，结果位才为1，否则为0.
        A = 10; // 001010
        B = 44; // 101100
        // A & B = 001000 //只有全部为1才是1,否则为0
        System.out.println(A & B); // 8
        System.out.println(decimalToBinary(A & B)); // 001000

        // 按位或操作 A | B
        // 将A和B的二进制表示的每一位进行或操作，只要两个对应的二进制位有一个为1，结果位就为1，否则为0.
        // A | B = 101110
        System.out.println(A | B); // 46
        System.out.println(decimalToBinary(A & B)); // 101110

        // 按位非操作~A
        // 将A的二进制最后一位+1 然后取反
        //  A = 10;      // 001010
        // ~A = -(10+1); //-001011
        System.out.println(~A); // -(10+1) = -11
        System.out.println(decimalToBinary(~A)); // -1011

        // 按位异或操作A^B
        // 将A和B的二进制表示的每一位进行异或操作，如果对应的二进制位不同，结果位为1，否则为0.
        // A = 001010;
        // B = 101100;
        // A^B=100110;
        System.out.println(A ^ B); // 38
        System.out.println(decimalToBinary3(A ^ B)); // 100110

//        System.out.println(isOdd(10));
//        System.out.println(isOdd(11));
//
//        System.out.println(isEven(10));
//        System.out.println(isEven(11));
//
//        System.out.println(removeLastBitOne(12)); // 8
//        System.out.println(removeLastBitOne(11)); // 10
//        System.out.println(removeLastBitOne(16)); // 0
//
//        System.out.println(getLastBitOne(12)); // 4
//        System.out.println(getLastBitOne(11)); // 1
//        System.out.println(getLastBitOne(16)); // 16


        // https://www.cnblogs.com/vsign/p/7290594.html
//        System.out.println(decimalToBinary(11));
//        System.out.println(decimalToBinary2(11));
//        System.out.println(decimalToBinary3(11));
//
//
//        System.out.println(decimalToBinary(1111111111)); //error 输出的二进制超出int最大长度
//        System.out.println(decimalToBinary2(1111111111)); //error 输出的二进制超出int最大长度
//        System.out.println(decimalToBinary3(1111111111));//ok
    }

    /**
     * 通过位运算判断是否为奇数
     * <p>
     * 判断整数二进制最后一位是否为1
     * <p>
     * 比如
     * 6是110  最后一位是0 为偶数
     * 11是1011 最后一位是1  为奇数
     *
     * @param num
     * @return
     */
    private static boolean isOdd(int num) {
        return (num & 1) == 1; //判断二进制最后一位是否为1
    }


    /**
     * 通过位运算判断是否为奇数
     * <p>
     * 判断整数二进制最后一位是否为0
     *
     * @param num
     * @return
     */
    private static boolean isEven(int num) {
        return (num & 1) == 0; //二进制最后一位为0的是偶数
    }


    /**
     * 清除最低位的1,如果1不在最后一位则清除倒数第2位,否则倒数第3位,依次...
     * <p>
     * 比如 11 二进制是 1011  清除后为  1010  得到10
     * <p>
     * 12 二进制是  1100  清除后为 1000  得到8
     * <p>
     * 16 二进制是  10000  清除后为 00000 得到 0
     *
     * @param num
     * @return
     */
    private static int removeLastBitOne(int num) {
        return num & (num - 1);
    }

    /**
     * 得到最低位的1对应的十进制数
     * <p>
     * 比如
     * 11 二进制是 1011  1是最后一位 得到1
     * <p>
     * 12 二进制是  1100  1是第2位  得到4
     * <p>
     * 16 二进制是  10000  1是第1位 得到16
     *
     * @param num
     * @return
     */
    private static int getLastBitOne(int num) {
        return num & -num;
    }


    /**
     * 十进制转二进制
     * <p>
     * 由于int最大值有限, 转换成2进制后 会存在超出最大长度的问题
     *
     * @param n
     */
    private static int decimalToBinary(int n) {
        int t = 0;  //用来记录位数
        int bin = 0; //用来记录最后的二进制数
        int r = 0;  //用来存储余数
        while (n != 0) {
            r = n % 2;
            n = n / 2;
            bin += r * Math.pow(10, t);
            t++;
        }
        return bin;
    }

    /**
     * 十进制转二进制, 调用Integer内置的方法toBinaryString()进行转换
     * <p>
     * 由于int最大值有限, 转换成2进制后 会存在超出最大长度的问题
     *
     * @param n
     * @return
     */
    private static int decimalToBinary2(int n) {
        String result = Integer.toBinaryString(n);
        return Integer.parseInt(result);
    }


    /**
     * 十进制转二进制
     * <p>
     * int型最大只能表示2^31-1 的正数，所以，存储的二进制数位数有限；
     * 我们都知道，int在java中的存储范围是32位，则可以使用字符串的拼接（+）来实现
     * <p>
     * 注意: 负数转成字符串会有问题
     *
     * @param n
     * @return
     */
    private static String decimalToBinary3(int n) {
        String str = "";
        while (n != 0) {
            str = n % 2 + str;
            n = n / 2;
        }
        return str;
    }
}
