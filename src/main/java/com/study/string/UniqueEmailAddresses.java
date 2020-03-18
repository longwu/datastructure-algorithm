package com.study.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 独特的电子邮件地址
 * <p>
 * 可以同时使用这两个规则。
 * <p>
 * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= emails[i].length <= 100  每个邮件地址长度不超过100
 * 1 <= emails.length <= 100     邮件地址总数不超过100
 * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
 * <p>
 * https://leetcode-cn.com/problems/unique-email-addresses/
 */
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        String[] emails =  {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};

        System.out.println(numUniqueEmails(emails));
    }

    /**
     * 遍历所有邮件,将每一封邮件@前面的'.'号移除, 以及取"+"号之前的字符串
     *
     * 这里使用了hashset,它的add()的时间复杂度为O(1), 并且不会存在相同的元素
     *
     * 时间复杂度O(n)
     * @param emails
     * @return
     */
    private static int numUniqueEmails(String[] emails) {
        Set<String> receivedEmails = new HashSet<String>(100);
        for (int i = 0; i < emails.length; i++) {
            String prefix = emails[i].substring(0, emails[i].indexOf("@"));
            String suffex = emails[i].substring(emails[i].indexOf("@"));
            prefix = prefix.replace(".", "");
            prefix = prefix.substring(0,prefix.indexOf("+"));
            String realEmail = prefix + suffex;
            receivedEmails.add(realEmail); // O(1)
        }
        return receivedEmails.size();
    }
}
