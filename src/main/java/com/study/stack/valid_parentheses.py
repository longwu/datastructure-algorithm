# encoding=utf-8
"""
有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true

https://leetcode-cn.com/problems/valid-parentheses/
"""


def isValid(s):
    list = []
    map = {')': '(', ']': '[', '}': '{'}
    for c in s:
        if c not in map: # 如果当前符号不在map的key中
            list.append(c) # 那么将该括号存入list中
        elif not list or map[c] != list.pop(): # 如果list不为空, 并从list中取出最后放入的元素与字典里的元素看能不能组成括号
            return False  # 如果不能那直接返回false, 否则就继续循环
    return not list # 判断最后list剩余元素的个数, 为0 说明全部组成了括号, 否则不能组成括号


s = "[]"
# s = "([)]"
#s = "{[]}"
print isValid(s)
