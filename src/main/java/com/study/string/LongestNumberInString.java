package com.study.string;

/**
 * 找出字符串中最长的连续数字
 */
public class LongestNumberInString {
    public static void main(String[] args) {
        String input = "abcd123cc45678977ed125ss6789";

        String longestNum = getLongestNumber(input);
        System.out.printf("最长连续数字为%s,长度为%d", longestNum, longestNum.length());
    }

    /**
     * 找出最长的连续数字, 无需使用额外集合, 只需创建两个字符串用于记录当前连续数字和最长连续数字
     *
     * @param input
     * @return
     */
    private static String getLongestNumber(String input) {
        String longestNum = "";
        String curNum = "";
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            //判断是否为数字,是否在0-9之间
            if (arr[i] >= '0' && arr[i] <= '9') {
                curNum += arr[i];
            } else {
                //如果遇到不是数字, 则将记录的连续数字长度和 目前最长数字相比
                if (curNum.length() > longestNum.length()) {
                    longestNum = curNum; // 超过则将最长数字替换为当前记录的数字
                }
                curNum = "";//清空当前记录的数字
            }
        }
        return longestNum;
    }
}
