package com.study.string;


import java.util.HashMap;
import java.util.Map;

/**
 * 最长回文串 (本题可以将字符串拆解重新构造出字符串)
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 */
public class LongestPalindrome {

    /**
     * 本题允许将字符串拆解后构造字符串, 所以只需要计算每个字符出现的次数即可
     *
     * @param args
     */
    public static void main(String[] args) {
        //String s = "abcbad";
        //String s = "a";
        //String s = "abccccdd";
        String s = "Aabcfecd";
//        System.out.println(1 / 2 * 2);
//        System.out.println(3 / 2 * 2);
//        System.out.println(5 / 2 * 2);
//        System.out.println(0 / 2 * 2);
        System.out.println(1 % 2); // 1 % 2 = 1;
        System.out.println("--------------------");

        //System.out.println(longestPalindrome(s));
        //System.out.println(longestPalindrome_2(s));
        //System.out.println(longestPalindrome2(s));
        //System.out.println(longestPalindrome3(s));
        System.out.println(longestPalindrome_3(s));

    }

    /**
     * 使用贪心法计算每个字符的个数, 取它的偶数个数(实现偶数对称回文数), 在偶数个存在的情况下可以再加上1个字符放在中间(实现奇数对称回文数)
     * 这里用到了char的ASCII值(hash值)来定位每个char在数组中的位置 比如 'A'hash值为65, 'a' hash值为97,  而'z'的hash值为122
     * 由于ASCII一共有128个字符, 所以创建了一个长度为128的数组来存.
     * <p>
     * 当然也可以优化一下, 由于所有的大小写字符所处的位置是65 到 122, 所以只需要使用长度为58的数组来存即可, 存的时候 每个字符需要减去第65个字符'A' 得出范围0-58的索引
     * <p>
     * 使用一个变量 length来计算满足条件的字符个数, 遍历字符数组, 得出每个字符的偶数个(0 2 4 ...), 加到总个数length中, 如果当前length总个数为偶数个, 那么还能再加一个字符作为中间对称字符
     * <p>
     * <p>
     * 时间复杂度O(n), 最多用了一层循环
     * 空间复杂度O(n), 用到了数组
     * <p>
     * ASCII码表 http://ascii.911cha.com/
     *
     * @param s
     * @return
     */
    public static int longestPalindrome(String s) {
        // ASCII总共128个字符, 所以使用长度为128的数组来存
//        int[] count = new int[128];
//        for (char c : s.toCharArray()) {
//            count[c]++; //记录每个char的数量
//        }

        // 也可以对数组进行优化, 使用一个长度为58的数组来存, 因为大小写字母位于ASCII码的第65 到 122位, 刚好是58个, 其中A为第65位
        int[] count = new int[58];
        for (char c : s.toCharArray()) {
            // 每个字符都比A大 0-58
            count[c - 'A']++; //记录每个char的数量
        }

        // 统计满足条件的字符个数
        int length = 0;
        for (int v : count) {
            // 找出每个字符的偶数个, 比如 aaaaa出现了5次,取偶数次为 aaaa 4次    5 / 2 *2 = 2 * 2 = 4
            // 当然如果该字符次数不足2, 则结果为0 比如 1 / 2 * 2 = 0*2 = 0 这里巧妙的用到了整数除法无法整除得到0的技巧
            length += v / 2 * 2;
            // 如果当前length总个数为偶数个, 那么还能再加一个字符作为中间对称字符
            if (v % 2 == 1 && length % 2 == 0) // 判断奇数个的前提是当前字符个数为奇数个, 可以是1 3 5 ...,且满足条件的字符总个数为偶数
                length++;
        }
        return length;
    }

    public static int longestPalindrome_2(String s) {
        int[] chars = new int[58];

        for (char c : s.toCharArray()) {
            chars[c - 'A']++;
        }

        int length = 0;
        for (int c : chars) {
            length += c / 2 * 2; //获取偶数个数字符

            // 在当前满足条件的字符总个数为偶数的情况下, 可以再增加一个奇数作为中间对称字符
            if (length % 2 == 0 && c % 2 == 1)
                length++;
        }

        return length;
    }

    public static int longestPalindrome_3(String s) {
        char[] chars = new char[128];

        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)]++;
        }

        int length = 0;
        // 记录可以做成回文数的字符个数
        for (int c : chars) {
            length += c / 2 * 2; // 如果是偶数得到本身, 如果是奇数则-1, 如果是0得到0

            if (length % 2 == 0 && c % 2 == 1) { //如果当前总数为偶数,而且c本身的个数为奇数, 可以使用这个c
                length++;
            }
        }

        return length;
    }


    /**
     * 贪心算法, 使用hashmap来存储每个字符
     *
     * @param s
     * @return
     */
    public static int longestPalindrome2(String s) {
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
            // 只有之前出现的总个数为偶数(0 2 4), 才允许将出现一次的元素加到总数
            if (count % 2 == 0 && entry.getValue() % 2 == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取最长会问字符串, 没有经过排序处理, 只是得到有效的字符
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        StringBuilder result = new StringBuilder();
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
            int curCount = entry.getValue() / 2 * 2;
            count += curCount;
            if (curCount >= 2) {
                for (int i = 0; i < curCount; i++) {
                    result.append(entry.getKey());
                }
            }
            // 只有之前出现的总个数为偶数, 才允许将出现一次的元素加到总数
            if (count % 2 == 0 && entry.getValue() % 2 == 1) {
                result.insert(count / 2, entry.getKey());
                count++;
            }
        }
        return result.toString();
    }
}
