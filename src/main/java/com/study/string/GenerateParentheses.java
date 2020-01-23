package com.study.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class GenerateParentheses {

    /**
     * 有几个需要注意的条件:
     * 1. 左括号和右括号数量必须一致
     * 2. 必须先有左括号
     *
     * @param args
     */
    public static void main(String[] args) {
        //List<String> resultList = generateParenthesis(3);
        List<String> resultList = generateParenthesis2(3);

        for (String result : resultList) {
            System.out.println(result);
        }
    }

    private static List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        //gen(0, 0, n, "", resultList);
        //gen2(0, 0, n, "", resultList);
        gen3(0, 0, n, "", resultList);

        return resultList;
    }

    /**
     * 闭合数
     * 思路
     *
     * 为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。
     *
     * 考虑有效括号序列 S 的 闭包数：至少存在 index >= 0，使得 S[0], S[1], ..., S[2*index+1]是有效的。 显然，每个括号序列都有一个唯一的闭包号。 我们可以尝试单独列举它们。
     *
     * 算法
     *
     * 对于每个闭合数 c，我们知道起始和结束括号必定位于索引 0 和 2*c + 1。然后两者间的 2*c 个元素一定是有效序列，其余元素一定是有效序列。
     *
     * https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode/
     *
     * @param n
     * @return
     */
    private static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis2(c))
                    for (String right: generateParenthesis2(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }


    /**
     * 使用递归枚举所有左右组合的情况, 增加一些限定条件(必须先有左括号再有右括号, 而且过程中, 右括号数量不能大于左括号)
     * <p>
     * 时间复杂度O
     *
     * @param left
     * @param right
     * @param n
     * @param result
     * @param resultList
     */
    private static void gen(int left, int right, int n, String result, List<String> resultList) {
        // 当左右括号都满足n个的时候, 就达到了结果要求
        if (left == n && right == n) {
            resultList.add(result);
            System.out.println("递归从外往内终止: result = " + result);
            return;
        }

        // 左括号增加条件: 左括号数量不到n个
        if (left < n) {
            System.out.println("左括号递归从外往内开始: result = " + result);
            gen(left + 1, right, n, result + "(", resultList);
        }

        System.out.println("左括号递归从内往外开始: result = " + result);

        // 右括号增加条件: 数量少于左括号, 并且不满足n个(这里因为left小于n, 所以right必然小于n)
        if (left > right) {
            System.out.println("右括号递归从外往内开始: result = " + result);
            gen(left, right + 1, n, result + ")", resultList);
        }

        System.out.println("右括号递归从内往外开始: result = " + result);
    }


    private static void gen2(int left, int right, int n, String result, List<String> resultList) {
        // 结束递归的条件
        if (left == n && right == n) {
            resultList.add(result);
            return;
        }

        // 先有左括号 再有右括号, 而且右括号一定不能大于左括号数量
        if (left < n) {
            gen2(left + 1, right, n, result + "(", resultList);
        }

        if (right < left) {
            gen2(left, right + 1, n, result + ")", resultList);
        }
    }

    /**
     * 递归中使用剪枝 或者也叫回溯
     *
     * @param left
     * @param right
     * @param n
     * @param result
     * @param resultList
     */
    private static void gen3(int left, int right, int n, String result, List<String> resultList) {
        // 结束递归的条件
        if (left == n && right == n) {
            resultList.add(result);
            return;
        }

        // 使用剪枝的方式 去除右括号大于左括号的所有情况
        // 剪枝 也是回溯法的一种解题思路, 因为当前做法不满足条件,所以直接返回避免无效操作
        if(left < right)
            return;

        // 先有左括号 再有右括号, 而且右括号一定不能大于左括号数量
        if (left < n) {
            gen2(left + 1, right, n, result + "(", resultList);
        }

        if (right < n) {
            gen2(left, right + 1, n, result + ")", resultList);
        }
    }
}
