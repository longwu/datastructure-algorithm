package com.study.string;


/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *  
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 *
 */
public class FirstUniqueCharacter {

    /**
     * 方法一: 遍历字符串使用hashmap来存每个字符的个数, 遍历hashmap,找到第一个不重复的字符
     * 方法二: 使用char[256]数组代替hashmap
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "leetcode";

    }
}
