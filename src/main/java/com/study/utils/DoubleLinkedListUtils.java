package com.study.utils;

import com.study.linkedlist.DoubleLinkedNode;

import java.util.List;

public final class DoubleLinkedListUtils {

    /**
     * 将集合转成双向链表
     * @param list
     * @return
     */
    public static DoubleLinkedNode convertTo(List<Integer> list) {
        // 将集合转成双向链表
        DoubleLinkedNode preNode = new DoubleLinkedNode(list.get(0));
        DoubleLinkedNode currentNode = preNode;
        for (int i = 1; i < list.size(); i++) {
            DoubleLinkedNode nextNode = new DoubleLinkedNode(list.get(i));
            // 设置前继和后驱节点
            currentNode.next = nextNode;
            nextNode.prev = currentNode;
            // 循环到下一个节点
            currentNode = nextNode;
        }
        return preNode;
    }

    public static void printLinkedList(DoubleLinkedNode head) {

        DoubleLinkedNode originalHead = head;
        while (head != null) {
            if (head.next == null) {
                System.out.printf("%d->NULL", head.val);
            } else {
                System.out.printf("%d->", head.val);
            }
            head = head.next;
        }

        System.out.println();

        while (originalHead != null) {
            if (originalHead.prev == null) {
                System.out.printf("NULL<-%d", originalHead.val);
            } else {
                System.out.printf("<-%d", originalHead.val);
            }
            originalHead = originalHead.next;
        }

        System.out.println();
    }
}
