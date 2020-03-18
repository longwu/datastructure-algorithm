package com.study.linkedlist;

import com.study.utils.LinkedListUtils;

/**
 * 删除排序链表中的重复元素
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class DeleteRepeatedNode {

    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(2);
        LinkedNode node4 = new LinkedNode(3);
        LinkedNode node5 = new LinkedNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        LinkedListUtils.printLinkedList(node1);

        //LinkedNode result = deleteDuplicates(node1);
        LinkedNode result = deleteDuplicates2(node1);
        LinkedListUtils.printLinkedList(result);
    }

    /**
     * 遍历链表, 如果当前节点和下一个节点值相同, 就把下一个节点的指针指向下下一个,
     * 去重后再下轮检查一下当前节点是否和下一个节点相同,如果相同就继续指向下下一个去重, 当前节点相同的节点直到去重结束.
     * 然后指向当前节点指向下一个节点继续遍历.
     *
     * 时间复杂度O(n), 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static LinkedNode deleteDuplicates(LinkedNode head) {
        LinkedNode cur = head;
        // 遍历条件是当前节点和下一个节点不为空
        while(cur!=null && cur.next !=null){
            // 如果当前节点和下一个节点值相同, 就把下一个节点的指针指向下下一个
            // 2->2->3
            if (cur.val == cur.next.val){
                // 不断将指针指向下下个 进行去重, 直到去重结束
                // 2->2->3 变成了 2->3 //然后下一轮循环 判断 当前2不等于next3, 于是就执行cur = cur.next
                cur.next = cur.next.next; // 将下一个指针指向下下一个, 这样下一轮遍历 当前值和下一个值可能就不再一样了,但也可能一样,那就再尝试下一个,直到不一样
            }else{
                cur = cur.next; // 如果当前和下一个节点不相同, 那就直接切换到下一个节点继续遍历
            }
        }

        return head;
    }

    public static LinkedNode deleteDuplicates2(LinkedNode head) {
        LinkedNode cur = head;
        while(cur!=null && cur.next!=null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next; // 不断移除与当前节点相同的重复节点
            }else{
                cur = cur.next;// 往当前节点的下一个节点遍历
            }
        }
        return head;
    }
}


