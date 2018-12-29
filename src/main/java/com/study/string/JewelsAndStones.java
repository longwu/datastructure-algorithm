package com.study.string;

import com.sun.glass.ui.Size;

import java.util.*;

/**
 * 宝石与石头
 * <p>
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 * <p>
 * S 和 J 最多含有50个字母。
 * J 中的字符不重复。
 * <p>
 * https://leetcode-cn.com/problems/jewels-and-stones/
 */
public class JewelsAndStones {

    final static int SIZE = 200 * 1000;

    public static void main(String[] args) {

        //String j = "aA", s = "aAAbbbb";
        //String j = "z", s = "ZZ";
        //System.out.println(numJewelsInStones(j,s));

        String j = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", s = "";
        for (int i = 0; i < SIZE; i++) {
            if (i == SIZE - 2) {
                s += 'Y';
            } else if (i == SIZE - 1) {
                s += 'Z';
            } else {
                s += i;
            }
        }

        long start = System.currentTimeMillis();

        //System.out.println(numJewelsInStones(j, s));
        System.out.println(numJewelsInStones2(j,s));

        long end = System.currentTimeMillis();

        System.out.printf("time cost: %s ms\r\n", end - start);
    }

    /**
     * 遍历所有石头, 然后查看是否有石头是宝石.
     * <p>
     * 里行了2层嵌套遍历
     * <p>
     * 时间复杂度为O(n2)
     *
     * @param J
     * @param S
     * @return
     */
    private static int numJewelsInStones(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;

        int count = 0;
        //使用循环,查询时间为O(n)
        for (int i = 0; i < S.length(); i++) {
            // 字符串的indexof()底层用了遍历, 查询时间为O(n)
            if (J.indexOf(S.charAt(i)) != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * 使用了一层遍历, 时间复杂度为O(n)
     *
     * @param J
     * @param S
     * @return
     */
    private static int numJewelsInStones2(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;

        Set<Character> set = new HashSet<Character>(J.length());

        //J中char不重复,所以可以使用hashset来存储
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }

        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            //HashSet的查询时间是O(1)
            if (set.contains(S.charAt(i))) {
                count++;
            }
        }

        return count;
    }
}
