# encoding=utf-8

import sys

'''
单链表反转

https://leetcode-cn.com/problems/reverse-linked-list/
'''

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


# java相同代码参考
# private static LinkNode reverse2(LinkNode head){
#     LinkNode prev = null;
#     LinkNode cur = head;
#     while(null != cur){
#         LinkNode next = cur.next; //java需要存储一个中间变量,以防止cur.next在下面一行被改变后,最后一行代码出错
#         cur.next = prev;
#         prev = cur;   //第2,3行代码目的是 prev.next = prev, 之前的节点变成当前节点的下一个节点
#         cur = next;   //第4行代码 目的是 把当前指针指到下一个节点
#     }
#     return prev;
# }

class Solution:

    def reverseList(self, head):
        """
        利用循环方法遍历链表来反转单链表,使用多元赋值
        :param head:
        :return:
        """
        cur, prev = head, None
        while cur:
            # python多元赋值,无需临时变量next
            prev, prev.next, cur = cur, prev, cur.next
        return prev

    def reverseList2(self, head):
        """
        利用循环方法遍历链表来反转单链表,使用临时变量
        :param head:
        :return:
        """
        cur, prev = head, None
        while cur:
            # 下面4行代码 需要临时变量next来保存cur.next值不受改变
            next = cur.next
            cur.next = prev
            prev = cur
            cur = next

        return prev

    def reverseList3(self, head):
        """
        使用递归方法来反转单链表,最后一次递归结束获取链表的尾部节点开始
        :param head:
        :return:
        """
        if head is None or head.next is None:
            return head
        prev = self.reverseList3(head.next)
        head.next.next = head
        head.next = None
        return prev

def print_node(head):
    while head:
        if head.next is None:
            sys.stdout.write(str(head.val))
        else:
            sys.stdout.write(str(head.val) + "->")
        head = head.next


node = ListNode(1)
node2 = ListNode(2)
node3 = ListNode(3)

node.next = node2
node2.next = node3

print_node(node)

print ""

s = Solution()
#reversedNode = s.reverseList(node)
#reversedNode = s.reverseList2(node)

reversedNode = s.reverseList3(node)

print_node(reversedNode)
