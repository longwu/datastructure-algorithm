package com.study.utils;

import com.study.linkedlist.ListNode;

public final class Printer {

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            if (head.next == null) {
                System.out.printf("%d->NULL", head.val);
            } else {
                System.out.printf("%d->", head.val);
            }
            head = head.next;
        }
        System.out.println();
    }

}
