package com.study.dynamicprogramming;

/**
 * 最长公共子序列
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "abc";
        String text2 = "def";

//        String text1 = "abcde";
//        String text2 = "ace";

//        String text1 = "oxcpqrsvwf";
//        String text2 = "shmtulqrypy";
        int result = longestCommonSubsequence(text1, text2);
        System.out.println(result);
    }

    /**
     * 使用动态规划 dp方程来解
     * 具体操作: 将两个字符串x和y转到一个二维数组操做.
     *           第一步: 定义basecase: 由于dp方程需要从0开始,让索引为0的长和宽的列表示空字符串,所以数组的长i和宽j为字符串长度+1.
     *                dp[0][i]和dp[j][0]都应该初始化为0.
     *                   比如 dp[0][3] = 0 的含义是, 对于字符串""和"abc", 它们的最长公共子序列lcs为0
     *           第二步: 找状态转移dp方程:
     *                   i=0或者j=0              LCS(i,j)=0     //如果两个字符中有有一个为空, 那LCS=0
     *                   i>0且j>0 且Xi=Yj        LCS(i,j)=LCS(i-1,j-1) + 1  // 如果两个字符相等, 那么LCS为其相邻左斜方值+1
     *                   i>0且j>0 且Xi!=Yj       LCS(i,j)=MAX{(LCS(i-1,j),LCS(i,j-1)} //如果连个字符不相等, 那么LCS为其相邻的左和上方值中最大的一个
     *   就这样不断的将二维数组上各个位置上的值填满.
     *   最后取数组右下角最后一个值为lcs
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        // 创建一个dp二维数组, 长宽为两个字符串的长度+1
        int x = text1.length() + 1;
        int y = text2.length() + 1;
        int[][] dp = new int[x][y];
        // 从数组的索引为1位置开始, 是为了使用根据索引为0的位置(空字符串)的值进行推倒
        // 数组中长和宽为0的位置, 所有值默认为0
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                // 如果字符text1[i] == text2[j], 那么对应的dp[i][j] = dp[i-1][j-1] + 1 相邻左斜方值+1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果字符text1[i] != text2[j], 那么对应的dp[i][j] = max(dp[i-1][j], dp[i][j-1]) 取相邻左和上值中最大的一个
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[x - 1][y - 1];
    }


    public static int longestCommonSubsequence_2(String text1, String text2) {
        int x = text1.length();
        int y = text2.length();

        int[][] dp = new int[x + 1][y + 1];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[x][y];
    }


//    public static int longestCommonSubsequence(String text1, String text2) {
//        int maxLength = 0;
//        // 记录短的字符数组开始计算的位置
//        int shortPosition = 0;
//
//        char[] chars1 = text1.toCharArray();
//        char[] chars2 = text2.toCharArray();
//
//        char[] longChars = new char[]{};
//        char[] shortChars = new char[]{};
//
//        if (chars1.length >= chars2.length) {
//            longChars = chars1;
//            shortChars = chars2;
//        } else {
//            longChars = chars2;
//            shortChars = chars1;
//        }
//
//        int currentLength = 0;
//        for (int i = 0; i < longChars.length; i++) {
//            System.out.println("longChars: " + i);
//            int j = shortPosition;
//            while (j < shortChars.length) {
//                if (longChars[i] == shortChars[j]) {
//                    currentLength++;
//                    shortPosition = j + 1; //修改下一次开始检查的位置
//
//                    maxLength = Math.max(maxLength, currentLength);
//                    break;
//                }
//                j++;
//            }
//        }
//        return maxLength;
//    }
}
