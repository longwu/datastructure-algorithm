# encoding=utf-8

import sys

'''
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.
说明:

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

https://leetcode-cn.com/problems/swap-nodes-in-pairs/
'''


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def swapPairs3(self, head):

        hd = ListNode(0)  # 创建一个next为None的节点
        hd.next = head
        head = hd
        prev = hd
        hd = hd.next

        while hd and hd.next:
            next = hd.next
            prev.next = next
            hd.next = next.next
            next.next = hd

            prev = hd
            hd = hd.next

        return head.next

    def swapPairs(self, head):
        result = ListNode(-1)
        prev = result
        #result.next = head
        prev.next = head
        while prev.next and prev.next.next:
            a = prev.next  # 1
            b = prev.next.next  # 2

            prev.next = b  # 2
            a.next = b.next  # 3
            b.next = a  # 1

            # 上面3行代码也可以写成如下一行
            # prev.next, a.next, b.next = b,b.next,a

            # prev.next = 2->1->3->4->5->6
            # 因为prev = self,所以self.next=prev.next 因此self.next = 2->1->3->4->5->6
            #prev = a  # 将prev指向a, 指向1, 于是下一轮循环prev.next变成了3, prev.next.next变成了4
            prev = prev.next.next
        return result.next

    def swapPairs2(self, head):
        if head is None or head.next is None:
            return head

        prev = self.swapPairs2(head.next.next)
        tmp = head.next
        head.next = prev
        tmp.next = head

        return tmp

def print_node(head):
    '''
    打印出链表
    :param head: 链表的头结点
    :return:
    '''
    while head:
        if head.next is None:
            sys.stdout.write(str(head.val))
        else:
            sys.stdout.write(str(head.val) + "->")
        head = head.next


node = ListNode(1)
node2 = ListNode(2)
node3 = ListNode(3)
node4 = ListNode(4)
node5 = ListNode(5)
node6 = ListNode(6)

node.next = node2
node2.next = node3
node3.next = node4
node4.next = node5
node5.next = node6

print_node(node)

print ("")

s = Solution()

# result = s.swapPairs(node)
result = s.swapPairs2(node)

print_node(result)
