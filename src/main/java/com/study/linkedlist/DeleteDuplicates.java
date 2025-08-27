package com.study.linkedlist;

import com.google.common.collect.Lists;
import com.study.utils.LinkedListUtils;

import java.util.List;

/**
 * 删除有序链表中重复的元素-I
 *
 * 因为是有序的, 所以出现重复的元素都是连在一起的, 不会隔几个再出现相同元素
 * https://www.nowcoder.com/practice/c087914fae584da886a0091e877f2c79?tpId=295&tqId=664&sourceUrl=%2Fexam%2Foj%3FquestionJobId%3D10%26subTabName%3Donline_coding_page
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,1,1,2,2,3);
        LinkedNode node = LinkedListUtils.buildList(list);

        LinkedListUtils.printLinkedList(node);

        LinkedNode result = deleteDuplicates(node);

        LinkedListUtils.printLinkedList(result);
    }

    public static LinkedNode deleteDuplicates(LinkedNode head) {

        if(head == null){
            return null;
        }
        LinkedNode temp = head;

        while(temp !=null && temp.next!=null){
            if(temp.val == temp.next.val){
                // 如果当前和下一个节点相同,则将下一个节点改成下下个,下回合再看是否相同,相同再继续改
                temp.next = temp.next.next;
            }else {
                // 不同,则切到下一个节点,继续遍历
                temp = temp.next;
            }
        }
        return head;
    }
}
