package com.study.linkedlist;

import com.study.utils.LinkedListUtils;

import java.util.Stack;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Reverse {

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

        LinkedListUtils.printLinkedList(node1);
        System.out.println();
        //Printer.printLinkedList(reverseList(node1));
        //Printer.printLinkedList(reverseListByRecursion(node1));
        LinkedListUtils.printLinkedList(reverseListByRecursion2(node1));
        //Printer.printLinkedList(reverseList3(node1));
        //Printer.printLinkedList(reverseList4(node1));
        //Printer.printLinkedList(reverstListByStack(node1));
    }

    /**
     * 使用循环反转单链表
     * <p>
     * 从前往后遍历整个链表, 将每个节点按顺序取出, 依次放在已取出节点的最前面,最终得到一个反转后的列表
     *
     * @param head
     * @return
     */
    private static LinkedNode reverseList(LinkedNode head) {
        // cur的上一个节点
        LinkedNode prev = null;
        // 将当前节点指针指向head节点
        LinkedNode cur = head;
        while (null != cur) {
            // 将当前节点指针的下一个节点暂存起来
            LinkedNode next = cur.next;
            // 反转指针,将指针从后一个节点转为指向前一个节点
            // 比如2->3 变成 2->1
            cur.next = prev;
            // 将上一个节点指针指向当前节点
            prev = cur;
            // 将当前节点指针指向刚保存的下一个节点
            cur = next;
        }
        return prev;
    }


    private static LinkedNode reverseList4(LinkedNode head) {
        LinkedNode prev = null;

        LinkedNode cur = head;

        while (cur != null) {
            LinkedNode next = cur.next;

            cur.next = prev;

            prev = cur;

            cur = next;
        }

        return prev;
    }

    /**
     * 使用递归的方法反转单链表
     * <p>
     * 最后一次递归结束后,prev为5->null, head为4->5->null
     * 进行第一次head.next.next = head; head.next = null;
     * 经过head.next.next = head; head形成4->5->4闭环, prev形成5->4->5闭环
     * 之后head.next = null; 将head闭环剪断变成4->null, prev变成5->4->null
     * <p>
     * 之后进行第二次head.next.next = head; head.next = null;
     * 这个时候prev为5->4->null, head为3->4->null(4后的next为null是因为之前head闭环被剪断了变成了4->null)
     * 经过head.next.next = head; head形成3->4->3闭环, prev形成5->4->3->4->3闭环
     * 经过head.next = null; 将head闭环剪断变成3->null, prev形成5->4->3->null
     * <p>
     * 同理进行之后的第三次,第四次,第五次head.next.next = head; head.next = null;
     * 最后prev形成5->4->3->2->1->null
     *
     * @param head
     * @return
     */
    private static LinkedNode reverseListByRecursion(LinkedNode head) {
        if (head == null || head.next == null) {
            System.out.print("递归往内终止, head = ");
            LinkedListUtils.printLinkedList(head);
            return head;
        }

        System.out.print("递归往内");
        LinkedListUtils.printLinkedList(head);
        // 首先进入递归,由于head不为空,且head.next不为空,所以直到最后一个head为5的时候,才满足条件,结束递归,返回prev为5->null,head.next为5->null,head为4->5->null
        LinkedNode prev = reverseListByRecursion(head.next);
        System.out.print("递归往外");
        LinkedListUtils.printLinkedList(head);
        // 使得head.next.head.next成为一个闭环
        // 赋值前4->5->null
        // 赋值head后 4->5->4->5->4->.....成为一个闭环
        // 由于4->5->4成为一个闭环, 这样prev也由5->null变成了5->4->5一个闭环
        head.next.next = head; //修改head的同时, prev也跟着变化
        // 将4->5->4闭环剪断 变成4->null
        // 这样prev也变成了 5->4->null
        head.next = null; //修改head的同时, prev也跟着变化
        return prev;
    }

    private static LinkedNode reverseListByRecursion2(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedNode prev = reverseListByRecursion2(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    /**
     * 借用stack后进先出的原理,对链表进行反转
     * 进的顺序为1 2 3 4 5, 出的顺序为 5 4 3 2 1
     *
     * @param head
     * @return
     */
    private static LinkedNode reverseList3(LinkedNode head) {
        Stack<LinkedNode> stack = new Stack<LinkedNode>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        // 创建一个新节点prev,也是一个只有单个节点的链表
        LinkedNode prev = new LinkedNode(-1);
        // 将head指向prev
        head = prev;

        while (!stack.isEmpty()) {
            LinkedNode current = stack.pop();
            // 因为stack中取出的节点可能带有原始链表的后继节点,所以重新创建一个相同val的新节点,移除原有的next属性
            // 给prev链表的添加下一个节点
            head.next = new LinkedNode(current.val);
            //将指针指向head下一个节点
            head = head.next;
        }
        return prev.next;
    }


    /**
     * 利用栈的先进后出原理, 实现链表的反转
     *
     * @param head
     * @return
     */
    private static LinkedNode reverstListByStack(LinkedNode head) {
        // 先将链表每个节点压入栈中, 放入顺序为 1 2 3 4 5
        Stack<LinkedNode> stack = new Stack<LinkedNode>();
        while (head != null) {
            stack.push(new LinkedNode(head.val));// 将节点压栈的时候需要放入没有后继的节点
            head = head.next;
        }

        // 把栈顶部的元素一个个弹出, 弹出顺序为5 4 3 2 1
        LinkedNode newNode = new LinkedNode(0);
        LinkedNode newHead = newNode;
        while (stack.size() > 0) {
            newHead.next = stack.pop();
            newHead = newHead.next;
        }

        return newNode.next;
    }
}
