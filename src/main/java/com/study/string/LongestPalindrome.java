package com.study.string;


import java.util.HashMap;
import java.util.Map;

/**
 * 计算最长回文串的字符个数
 *
 * 回文串是一个正读和反读都一样的字符串。对一个左边的字符 i 右边一定会有一个对称 i。比如 'abcba'， 'aa'，'bb' 这几个回文串。其中第一个有点特殊，中间的 c 是唯一的。
 * 如果让你来造一个回文串你会怎么造？ 首先让它左右两边绝对对称，如果可能的话再加上一个唯一的中心。
 * <p>
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-palindrome/solution/zui-chang-hui-wen-chuan-by-leetcode/
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        //String s = "abcbad";
        //String s = "a";
        String s = "abccccdd";
        System.out.println(1 / 2 * 2);
        System.out.println(3 / 2 * 2);
        System.out.println(5 / 2 * 2);
        System.out.println(0 / 2 * 2);
        System.out.println("--------------------");

        LongestPalindrome lp = new LongestPalindrome();
        //System.out.println(lp.longestPalindrome(s));
        System.out.println(lp.count(s));
    }

    public int longestPalindrome(String s) {
        // ASCII总共128个字符
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++; //记录每个char的数量
        }

        int ans = 0;
        for (int v : count) {
            // 统计元素出现的总个数, 必须是出现个数>=2以上的才满足, 而且取其最大偶数次数, 比如2次的ans=2, 3次的ans=2,5次的ans=4
            ans += v / 2 * 2;
            // 用于找出中心字符, 条件是中心字符出现次数为奇数次比如1 3 5等,且当前其他元素出现的总次数为偶数(如 0 2 4等)就满足条件
            if (v % 2 == 1 && ans % 2 == 0)
                ans++;
        }
        return ans;
    }


    public int count(String s) {
        // 记录每个char出现的次数
        Map<Character, Integer> kv = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!kv.containsKey(c))
                kv.put(c, 1);
            else
                kv.put(c, kv.get(c) + 1);
        }

        // 计算回文数总个数
        int count = 0;
        for (Map.Entry<Character, Integer> entry : kv.entrySet()) {
            // 统计元素出现的总个数, 必须是出现个数>=2以上的才满足, 而且取其最大偶数次数, 比如2次的count=2, 3次的count=2,5次的count=4
            count += entry.getValue() / 2 * 2;
            // 只有之前出现的总个数为偶数, 才允许将出现一次的元素加到总数
            if (count % 2 == 0 && entry.getValue() % 2 == 1) {
                count++;
            }
        }
        return count;
    }
}
