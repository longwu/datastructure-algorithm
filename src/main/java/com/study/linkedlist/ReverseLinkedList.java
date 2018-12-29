package com.study.linkedlist;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        print(node1);

        print(reverseList(node1));
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static void print(ListNode head) {
        while (head != null) {
            if (head.next == null) {
                System.out.printf("%d->NULL" ,head.val);
            } else {
                System.out.printf("%d->", head.val);
            }
            head = head.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
