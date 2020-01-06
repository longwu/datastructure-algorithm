package com.study.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class ValidAnagram {

    /**
     * 两种解法,
     * 一种是 对字符串中所有字符转成字符数组进行排序, 然后遍历数组对比每个字符是否, 如果都一样则是
     * 一种是 使用hashmap来存储两个字符串里的每个字符以及个数, 判断两个hashmap是否相同.
     *
     * @param args
     */
    public static void main(String[] args) {
        String s1 = "cat";
        String s2 = "tac";
        ValidAnagram va = new ValidAnagram();
        //System.out.printf("%s==%s: %s", s1, s2, va.isAnagram(s1, s2));
        System.out.printf("%s==%s: %s", s1, s2, va.isAnagram2(s1, s2));
        System.out.println();

        s1 = "aaab";
        s2 = "bbaa";
        //System.out.printf("%s==%s: %s", s1, s2, va.isAnagram(s1, s2));
        System.out.printf("%s==%s: %s", s1, s2, va.isAnagram2(s1, s2));
        System.out.println();

        s1 = "anagram";
        s2 = "nagaram";
        System.out.printf("%s==%s: %s", s1, s2, va.isAnagram2(s1, s2));
        System.out.println();
    }


    /**
     * 使用排序的方式进行比较 时间复杂度为O(nlogn)
     * <p>
     * 对字符串中所有字符进行排序, 然后将两个字符串的每个字符进行比较, 如果每个字符都一样,且字符串长度相同 那么是字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        // 判断字符串长度是否一样, 不一样直接返回false
        if (s.length() != t.length())
            return false;

        // 将两个字符串转成char数组
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        // 使用Arrays.sort对char数组进行排序 (内部是快速排序,时间复杂度为O(nlogn))
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        // 将排序好的数组进行比较, 比较每一位的字符是否一样
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i])
                return false;
        }
        return true;
    }

    /**
     * 借助hashmap进行比较 时间复杂度为O(n)
     * <p>
     * 使用hashmap来存储两个字符串里的每个字符以及个数, 判断两个hashmap里面的key和value是否相同.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        // 长度不一样,直接返回false
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mapS.containsKey(c)) {
                mapS.put(c, mapS.get(c) + 1);
            } else {
                mapS.put(c, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (mapT.containsKey(c)) {
                mapT.put(c, mapT.get(c) + 1);
            } else {
                mapT.put(c, 1);
            }
        }

        // 因为前面判断了是否长度一样
        // 所以这里只需要判断每个key对应的value是否一样
        for (Map.Entry<Character, Integer> kv : mapS.entrySet()) {
            if (!kv.getValue().equals(mapT.get(kv.getKey())))
                return false;
        }

        return true;
    }

//
//    public boolean isAnagram2(String s, String t) {
//
//    }
}
