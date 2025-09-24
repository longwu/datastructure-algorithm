package com.study.utils;

import com.study.linkedlist.DoubleLinkedNode;
import com.study.linkedlist.LinkedNode;

import java.util.List;

public final class LinkedListUtils {

    public static void printLinkedList(LinkedNode head) {
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

    public static LinkedNode buildList(List<Integer> list){
        LinkedNode result = new LinkedNode(-1);
        LinkedNode temp = result;
        for(Integer l :list){
            temp.next = new LinkedNode(l);
            temp = temp.next;
        }
        return result.next;
    }

}
