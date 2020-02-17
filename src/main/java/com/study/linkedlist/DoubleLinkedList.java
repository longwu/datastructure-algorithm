package com.study.linkedlist;

import com.study.utils.LinkedListUtils;

/**
 * 双向升序链表
 * <p>
 * 每个节点都有直接前驱后直接后继, 除了第一个节点没有直接前驱和最后一个节点没有直接后继
 * 结构如下
 * <p>
 * 1 -> 2 -> 4 -> 5 -> 6 -> null
 * null <- 1 <- 2 <- 4 <- 5 <- 6
 */
public class DoubleLinkedList {

    public static void main(String[] args) {
        //往 1 -> 2 -> 4 -> 5 -> 6 中插入  一个0或3
        //     <-   <-   <-   <-
        DoubleLinkedNode node1 = new DoubleLinkedNode(1);
        DoubleLinkedNode node2 = new DoubleLinkedNode(2);
        DoubleLinkedNode node3 = new DoubleLinkedNode(3);
        DoubleLinkedNode node4 = new DoubleLinkedNode(4);
        DoubleLinkedNode node5 = new DoubleLinkedNode(5);
        DoubleLinkedNode node6 = new DoubleLinkedNode(6);

        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;
        node5.next = node6;
        node6.prev = node5;

//        printLinkedList(node1);
//
//        Node head1 = insertNode(node1, 0);
//
//        printLinkedList(head1);
//        System.out.println();
//
//        Node head2 = insertNode(head1, 8);
//
//        printLinkedList(head2);
//        System.out.println();
//        Node head3 = insertNode(head2, 4);
//
//        printLinkedList(head3);

        System.out.println("---------------------");
        DoubleLinkedNode head4 = deleteNode(node1, 3);
        LinkedListUtils.printLinkedList(head4);

//        System.out.println();
//        head4 = deleteNode(head4, 0);
//        printLinkedList(head4);
//
//        System.out.println();
//        head4 = deleteNode(head4, 5);
//        printLinkedList(head4);
//
//        System.out.println();
//        head4 = deleteNode(head4, 8);
//        printLinkedList(head4);
    }


    /**
     * 插入一个新value并返回插入后的头节点
     * <p>
     * 新插入的节点可能是第一个,也可能是中间或者最后一个
     *
     * @param head
     * @param value
     * @return
     */
    private static DoubleLinkedNode insertNode(DoubleLinkedNode head, int value) {
        DoubleLinkedNode newNode = new DoubleLinkedNode(value);
        DoubleLinkedNode originalHead = head;
        while (head != null) {
            // 如果插入的节点比链表里的每个节点都小
            if (newNode.val < head.val) {
                // 头节点
                // 如果head是头节点， 将新节点插在头节点的前面
                if (head.prev == null) {
                    newNode.next = head;
                    head.prev = newNode;
                    return newNode;
                } else {
                    // 中间节点
                    //如果当前head不是头节点，插入新节点的时候需要修改上一个节点的next,当前节点的pre和next, 以及下一个节点的pre, 4个指针
                    //修改当前节点的上一个节点, 将上一个节点存一个临时变量
                    DoubleLinkedNode prevNode = head.prev;
                    //将上一个节点的临时变量的后继指针指向新节点, 将新节点的前驱指针指向上一个节点的临时变量
                    prevNode.next = newNode;
                    newNode.prev = prevNode;
                    // 将当前节点的前驱指向新节点, 新节点的后继指向当前节点
                    head.prev = newNode;
                    newNode.next = head;
                    return originalHead;
                }
            }
            // 末尾节点
            //如果新插入的节点比链表最后一个还要大
            if (head.next == null) {
                //修改当前节点的后继和新节点的前驱
                head.next = newNode;
                newNode.prev = head;
                return originalHead;
            }
            head = head.next;
        }
        return originalHead;
    }


    /**
     * 删除一个值对应的节点,并返回新的头节点
     * <p>
     * 该值对应的节点可能不存在, 也可能是头,中,尾部位的节点
     *
     * @param head
     * @param value
     */
    private static DoubleLinkedNode deleteNode(DoubleLinkedNode head, int value) {
        System.out.println("要删除的节点是" + value);
        DoubleLinkedNode deleteNode = new DoubleLinkedNode(value);
        DoubleLinkedNode originalHead = head;
        while (head != null) {
            // 如果要删除的节点存在
            if (head.val == deleteNode.val) {
                // 如果删除的节点是头节点, 将头节点的next和下一个节点prev改为null
                if (head.prev == null) {
                    DoubleLinkedNode secondNode = head.next;
                    secondNode.prev = null;
                    head.next = null;  //这行代码也可以不写  因为我们返回的链表secondNode里面已经没有了head,所以head.next也可以不处理
                    System.out.println("删除的节点为头节点");
                    return secondNode; //返回新的头结点,第二个节点
                } else {
                    //删除的节点是中间节点和尾节点
                    //如果删除的节点是尾节点
                    if (head.next == null) {
                        // 将尾节点的prev和上一个节点的next改为null
                        DoubleLinkedNode prev = head.prev;
                        prev.next = null;
                        head.prev = null; //这行代码也可以不写
                        System.out.println("删除的节点为尾节点");

                    } else { //如果删除的节点为中间的一个节点
                        //将要删除的节点的上一个节点next改为要删除节点的下一个节点, 将要删除节点的下一个节点的prev改为它的上一个节点

                        //将当前节点的上一个和下一个节点拿出来存临时变量
                        DoubleLinkedNode prev = head.prev;
                        DoubleLinkedNode next = head.next;
                        //将当前节点的前驱和后继改为null
                        //head.prev = head.next = null; //这行代码也可以不写
                        //将上一个节点的next指向下一个节点
                        prev.next = next;
                        //将下一个节点的prev指向上一个节点
                        next.prev = prev;

                        System.out.println("删除的节点为中间节点");
                    }
                    return originalHead; //返回原来的头结点
                }
            }
            head = head.next;
        }
        System.out.println("要删除的节点不存在");
        // 如果删除的节点不存在
        return originalHead;
    }


}