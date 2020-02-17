package com.study.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        LinkedNode node5 = new LinkedNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // 节点5之后又回到了节点2
        node5.next = node2;

        //Printer.printLinkedList(node1);

        System.out.println(hasCycle(node1));
        //System.out.println(hasCycle2(node1));
        //System.out.println(hasCycle3(node1));
    }

    /**
     * 实现快慢两个指针,一个每次走1个节点,一个每次走2个节点,如果两个指针相遇,那么说明链表有环
     *
     * @param head
     * @return
     */
    private static boolean hasCycle(LinkedNode head) {
        LinkedNode fast = head;
        LinkedNode slow = head;
        while (fast != null && slow != null && fast.next != null) {// 因为快指针需要走两步,如果fast.next为null,那走第二步就报空指针了
            // 注意因为起始位置都一样,都是head,所以需要让两个指针都先跑起来,之后再相遇
            slow = slow.next;
            fast = fast.next.next;

            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用一个hashset来存储链表的每个节点，如果出现已存在的节点，说明链表有环
     * <p>
     * 时间复杂度O(n), 但需要额外创建一个hashset集合
     *
     * hashset读的时间复杂度为O(1), 比很多数据结构都快
     *
     * @param head
     * @return
     */
    private static boolean hasCycle2(LinkedNode head) {
        Set<LinkedNode> set = new HashSet<LinkedNode>();

        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 暴力法，顺着链表一直走下去，如果没有走完说明链表有环(十分影响性能，不推荐这种做法)
     *
     * @param head
     * @return
     */
    private static boolean hasCycle3(LinkedNode head) {
        long start = System.currentTimeMillis();
        while (head != null) {
            head = head.next;

            // 1秒后还有没有走完链表说明有环
            if ((System.currentTimeMillis() - start) > 1000) {
                return true;
            }
        }
        return false;
    }
}
