package com.study.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个链表是否有环,并找出环的起始节点
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * <p>
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LinkedListCycleTwo {
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
        // 节点5之后又回到了节点3
        node5.next = node3;
        LinkedNode cycyleBegins = detectCycle(node1);
        //ListNode cycyleBegins = detectCycle2(node1);
        //ListNode cycyleBegins = detectCycle3(node1);
        System.out.println(cycyleBegins.val);
    }


    /**
     * 使用公式推导出为什么 从快慢节点相遇位置出发的节点 会与从头节点出发的节点相遇?
     * <p>
     * 快的与慢的相遇的时候, 快的笔慢的要多走r2-r1圈
     * a        b
     * start ------->-------->meeting
     * |         |
     * <----------
     * c
     * assume fast and slow meets at k steps
     * k=a+b+r1(b+c) slow runs r1 cycles
     * 2k=a+b+r2(b+c) fast runs r2 cycles, fast steps is 2 times of slow
     * 2k=a+b+r2(b+c)=2a+2b+2r1(b+c)
     * (b+c)(r2-2r1)=a+b => (b+c)n=a+b
     * a=(n-1)b+nc=(n-1)(b+c)+c
     * a = (n-1)(b+c)+c
     * a = n-1 cycles + c  which means when slow moves (n-1) cycles and c, start moves a
     * <p>
     * 最后, 从起始点出发的节点走 a 步 会与 从相遇点出发的节点走(n-1)圈 + c 步相遇, 且相遇位置刚好在环的起始节点
     *
     * @param head
     * @return
     */
    private static LinkedNode detectCycle(LinkedNode head) {
        if (head == null || head.next == null) return null;

        LinkedNode slow = head;
        LinkedNode fast = head;
        LinkedNode start = head;
        boolean isCycle = false;

        // 使用快慢节点,找出是否为有还链表
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                isCycle = true;
                break;
            }
        }

        if (!isCycle) {
            return null;
        }

        // start从头节点开始走, 然后fast从相遇的节点开始走
        // 大家每次都走一步, 最终相遇的位置为链表的环的起始节点
        while (start != fast) {
            start = start.next;
            fast = fast.next;
        }

        return start;
    }


    /**
     * 与detectCycle一样, 代码再次优化
     * @param head
     * @return
     */
    private static LinkedNode detectCycle2(LinkedNode head) {
        LinkedNode fast = head;
        LinkedNode slow = head;

        LinkedNode start = head;
        while (fast != null && slow != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                // 是一个有环链表
                // 从头结点重新开始一个节点去与整个慢节点进行碰面,当碰到的时候则为链表的环起始位置.
                // 这是一个公式推导出来的算法
                while(start != slow){
                    start = start.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 遍历链表, 将每个节点存入一个hashset,
     * <p>
     * 存储前判断是否已存在该节点,存在则返回该节点.
     */
    private static LinkedNode detectCycle3(LinkedNode head) {
        Set<LinkedNode> set = new HashSet<LinkedNode>();

        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
