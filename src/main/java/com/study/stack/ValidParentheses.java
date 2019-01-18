package com.study.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 */
public class ValidParentheses {
    public static void main(String[] args) {
        //String input = "()";
        //String input = "[]";
        //String input = "(]";
        String input = "{[]}";
        System.out.println(isValid2(input));
    }

    /**
     *
     * 利用栈后进先出的特性
     *
     * 使用了一个hashmap来存储正确的括号,然后将每次往栈中放之前将栈顶端里面的元素和即将放的元素与map中的某个keyvalue想比较,
     *
     * 如果相等,则移除栈内顶端的元素
     *
     * 时间复杂度O(n)
     * @param s
     * @return
     */
    private static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        Map<Character, Character> map = new HashMap<Character, Character>(3);
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        for (char c : chars) {
            //如果栈里没有了元素,往栈里面放
            if (stack.size() == 0) {
                stack.push(c);
                //如果栈里面最上面一个元素能和即将放入的元素组成完整的括号,则将栈最上面的元素移除
            } else if (map.containsKey(stack.peek()) && map.get(stack.peek()) == c) {
                stack.pop();//移除最上面的一个元素
            } else {//往栈里面方元素
                stack.push(c);
            }
        }

        return stack.size() == 0;
    }

    /**
     * 判断将每次往栈中放之前将栈顶端里面的元素和即将放的元素是否组成一个括号,
     *
     * 如果是, 则移除栈内顶端的元素
     *
     * 时间复杂度O(n)
     * @param s
     * @return
     */
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            //如果栈里没有了元素,往栈里面放
            if (stack.size() == 0) {
                stack.push(c);
                //如果栈里面最上面一个元素能和即将放入的元素组成完整的括号,则将栈最上面的元素移除
            } else if (isRightBracket(stack.peek(), c)) {
                stack.pop();//移除最上面的一个元素
            } else {//往栈里面方元素
                stack.push(c);
            }
        }

        return stack.size() == 0;
    }

    private static boolean isRightBracket(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
}
