package com.study.linkedlist;

import com.google.common.collect.Lists;
import com.study.utils.LinkedListUtils;

import java.util.List;

/**
 * 删除有序链表中重复的元素-2
 * <p>
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 给出的链表为1-2-3-3-4-4-5 返回的1-2-5
 * 给出的链表为1-1-1-2-3,返回2-3
 */
public class DeleteDuplicates2 {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 1, 1, 2, 2, 3,5,6);
        LinkedNode node = LinkedListUtils.buildList(list);

        LinkedListUtils.printLinkedList(node);

        LinkedNode result = deleteDuplicates(node);

        LinkedListUtils.printLinkedList(result);
    }

    public static LinkedNode deleteDuplicates(LinkedNode head) {

        if (head == null) {
            return null;
        }
        // 增加一个前缀,确保第一个和第二个不同
        LinkedNode result = new LinkedNode(-1);
        result.next = head;
        LinkedNode temp = result; // 这样修改temp不会改掉res和head
        while (temp != null && temp.next != null && temp.next.next != null) {
            // 如果下一个节点和下下个都相同, 说明下一个和下下个都相同,甚至还有下三个也想通
            if (temp.next.val == temp.next.next.val) {
                int sameValue = temp.next.val;
                while (temp.next != null && temp.next.val == sameValue) {
                    temp.next = temp.next.next;  // 不停的切换下一个,直到下一个与相同值不同为止
                }
            } else {
                // 不同,则将当前节点删除(出现过重复的元素需要删除),切到下一个节点,继续遍历
                temp = temp.next;
            }
        }
        return result.next;
    }
}
