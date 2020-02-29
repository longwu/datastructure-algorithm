package com.study.string;

import java.util.Scanner;

/**
 * 获取控制台输入的一行字符串, 取最后一个单词的长度
 *
 * 比如hello jack,  输出4
 */
public class LastWordCount {

    public static void main(String[] args) {
        String input = "hello jack";
        //System.out.println(getLastWordCount(input));
        //System.out.println(getLastWordCount2(input));
        System.out.println(getLastWordCount3(input));

        Scanner scan = new Scanner(System.in);// 获取控制台的输入
        // 输出敲回车之前的所有内容
        while (scan.hasNext()) {
            //System.out.println(scan.next());
            System.out.println(getLastWordCount(scan.nextLine())); // 输入整行内容
            //System.out.println(getLastWordCount(scan.next()));// 将输入的内容按空格分割
        }
    }

    /**
     * 获取最后一个单词的长度, 将字符串反向遍历
     * @param input
     * @return
     */
    private static int getLastWordCount(String input) {
        if (input == null || input.length() == 0)
            return 0;
        int count = 0;
        int length = input.length();
        // 将字符串从后往前计算count, 当遇到空格的时候, 中断统计
        char[] chars = input.toCharArray();
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ')
                break;
            count++;
        }
        return count;
    }

    /**
     * 从后往前遍历
     * @param input
     * @return
     */
    private static int getLastWordCount2(String input) {
        if (input == null || input.length() == 0)
            return 0;
        int count = 0;
        int length = input.length();
        // 将字符串从后往前计算count, 当遇到空格的时候, 中断统计
        for (int i = length - 1; i >= 0; i--) {
            if (input.charAt(i) == ' ')
                break;
            count++;
        }
        return count;
    }

    /**
     * 按空格号分割字符串, 将字符串正序遍历
     *
     * @param input
     * @return
     */
    private static int getLastWordCount3(String input) {
        if (input == null || input.length() == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            count++;
            if (input.charAt(i) == ' ') {
                count = 0; // 每次遇到空格 都将count清零, 只有最后一个单词没有空格不会被清零
            }
        }
        return count;
    }
}
