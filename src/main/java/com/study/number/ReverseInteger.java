package com.study.number;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public static void main(String[] args) {

        //int x = 1234567890;
        //int x = -1234567890;
        int x = 0;

        System.out.println(reverse(x));

    }

    /**
     * 将输入的整数变成字符串,然后倒叙输出.
     *
     * 如果是负数,则需要将最后一位负数插入到字符串最前面
     *
     * 最后将字符串转成整数,注意整数的最大值和最小值处理
     * @param x
     * @return
     */
    static int reverse(int x) {
        String intput = String.valueOf(x);

        StringBuffer result = new StringBuffer();
        for (int i = intput.length() - 1; i >= 0; i--) {
            if (x < 0 && i == 0)
                result.insert(0, "-");
            else
                result.append(intput.charAt(i));
        }

        try {
            Long longValue = Long.parseLong(result.toString());
            if (longValue > Integer.MAX_VALUE || longValue < Integer.MIN_VALUE)
                return 0;
            return Integer.valueOf(longValue.toString());
        } catch (Exception e) {
            return 0;
        }
    }
}
