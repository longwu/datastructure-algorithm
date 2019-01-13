package com.study.linkedlist;

import com.study.utils.Printer;

/**
 * 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 说明:
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Printer.printLinkedList(node1);

        //Printer.printLinkedList(swapPairs(node1));
        Printer.printLinkedList(swapPairs2(node1));
    }

    /**
     * 通过循环遍历链表, 每次取未交换的前2个节点进行交换,交换完后将指针指向后2个节点,一遍下一轮循环处理
     *
     * 这里需要3个指针，其中两个是pair对中相邻的两个节点指针a和b，另一个是pair对之前的一个节点指针
     *
     * @param head
     * @return
     */
    private static ListNode swapPairs(ListNode head) {
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode prev = result;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next; //1
            ListNode b = prev.next.next; //2

            prev.next = b; //2
            a.next = b.next; //3
            b.next = a; //1

            // prev.next = a.next = 3,  prev.next.next = 4
            // prev = a; // 也可以 (a = prev.next.next)
            prev = prev.next.next;
        }

        return result.next;
    }

    /**
     * 使用递归
     *
     * @param head
     * @return
     */
    private static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // prev 为最后一个节点
        // prev = null, head = 5, head = 3
        ListNode prev = swapPairs2(head.next.next);
        // tmp 为倒数第二个节点
        // 下面3行代码将每一对的两个节点进行互换
        // 将tmp指向当前pair对的第2个节点
        ListNode tmp = head.next; //tmp2 = 6,tmp2 = 4->5->null, tmp2 = 2->3->6->5->null
        // 将当前pair对第2个节点指向已经反转好的节点
        head.next = prev; // 5.next = null, 3.next = 6->5->null, 1.next = 4->3->6->5->null
        // 将pair对的第3个节点的指向第1个节点， 这样第2个节点后面为第1个节点，形成了反转
        tmp.next = head; // 6.next = 5, 4.next = 3->6->5->null , 2.next = 1->4->3->6->5->null
        // tmp = 6->5->null, tmp = 4->3->6->5->null, tmp = 2->1->4->3->6->5->null
        //print(tmp);
        return tmp;
    }
}
