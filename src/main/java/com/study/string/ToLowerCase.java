package com.study.string;

/**
 * 转换成小写字母
 * <p>
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "Hello"
 * 输出: "hello"
 * 示例 2：
 * <p>
 * 输入: "here"
 * 输出: "here"
 * 示例 3：
 * <p>
 * 输入: "LOVELY"
 * 输出: "lovely"
 * <p>
 *
 * https://leetcode-cn.com/problems/to-lower-case/
 */
public class ToLowerCase {
    public static void main(String[] args) {

        //String str = "LOVELY";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(toLowerCase(str));
    }

    /**
     * 将字符串转换成char数组
     *
     * 遍历数组, 将每个大写字母转换成小写
     *
     * 利用ascii大小写字母对应的差值进行转换
     *
     * @param str
     * @return
     */
    private static String toLowerCase(String str) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            //如果是大小字母
            //if (chars[i] >= 'A' && chars[i] <= 'Z') {
            if (chars[i] >= 64 && chars[i] <= 91) {
                //转换成对应的小写字母
                chars[i] += 32;
            }
        }

        return new String(chars);
    }
}
