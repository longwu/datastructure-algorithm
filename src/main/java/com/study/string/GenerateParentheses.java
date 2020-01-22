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
        List<String> resultList = generateParenthesis(3);

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
