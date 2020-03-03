package com.study.string;


/**
 * 翻转字符串里的单词
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //String s = "the sky is blue";
        String s = "hello world!";
        //s = reverse(s);
        s = reverse2(s);
        System.out.println(s);
    }

    /**
     * 方法一: 先用trim()去除字符串首位的空格, 并分割成单词数组
     * 将单词数组反向遍历 放入新字符串中, 从第一个单词之后放入空格
     *
     * @param s
     * @return
     */
    private static String reverse(String s) {
        // 将字符串首位去除空格
        // 并分割成单词数组
        String[] words = s.trim().split(" ");

        String result = "";
        // 将单词数组反向遍历 放入新字符串中
        for (int i = words.length - 1; i >= 0; i--) {
            if (i != words.length - 1) {
                result += " ";// 从第一个单词之后放入空格
            }
            result += words[i];
        }
        return result;
    }


    /**
     * 方法二:
     * <p>
     * 先用trim()去除字符串首位的空格, 将整个字符串进行翻转, 翻转后再将每个单词进行翻转
     *
     * @param s
     * @return
     */
    private static String reverse2(String s) {
        s = s.trim();

        // 将字符串进行翻转, 可以使用前后指针进行对调
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        // 将字符串里的字符进行翻转
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }

        // 将每个单词进行翻转
        String result = "";
        String newString = new String(chars);
        String[] words = newString.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (int j = words[i].length() -1; j >= 0; j--) {
                result += words[i].charAt(j);
            }
            if (i != words.length - 1)
                result += " "; //最后一个单词末尾没有空格
        }
        return result;
    }
}
