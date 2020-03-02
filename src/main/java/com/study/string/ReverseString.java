package com.study.string;

/**
 * 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        reverse(chars);

        for (char c : chars) {
            System.out.print(c + " ");
        }
    }

    /**
     * 使用前后指针 从首位开始将前后两个元素进行互换
     *
     * @param chars
     * @return
     */
    private static void reverse(char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        // 将前后两个字符进行对调
        while (left < right) { // 如果是奇数, 中间元素不用换位置
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
