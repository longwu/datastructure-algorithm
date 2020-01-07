package com.study.linkedlist;

import com.study.utils.Printer;

/**
 * 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/hua-jie-suan-fa-21-he-bing-liang-ge-you-xu-lian-bi/
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(6);

        System.out.println("l1链表");
        Printer.printLinkedList(l1);
        System.out.println("l2链表");
        Printer.printLinkedList(l2);

        // ListNode result = mergeTwoLists(l1, l2);
        // ListNode result =  mergeTwoListsByRecursion(l1,l2);
        ListNode result = mergeTwoListsByRecursion2(l1, l2);
        Printer.printLinkedList(result);
    }

    /**
     * 创建一个新链表
     * 迭代的方式将两个链表中的小值加入到一个新链表中, 迭代完后需要把没有处理完的列表加到新链表尾部. 时间复杂度O(m+n)
     * <p>
     * 时间复杂度 O(m+n)  m和n各位两个链表的长度
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(-1);
        ListNode head = prev;

        // 先将l1和l2中小的元素依次放入新链表中
        while (l1 != null && l2 != null) {
            // 将小的加入到链表前面
            if (l1.val > l2.val) {
                head.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                head.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            Printer.printLinkedList(prev.next);
            head = head.next;
        }

        // 由于两个链表长度和节点值原因, 可能存在某个链表没有遍历完成

        // 如果l1没有遍历完成
//        while (l1 != null) {
//            head.next = new ListNode(l1.val);
//            head = head.next;
//            l1 = l1.next;
//        }
//
//        // 如果l2没有遍历完成
//        while (l2 != null) {
//            head.next = new ListNode(l2.val);
//            head = head.next;
//            l2 = l2.next;
//        }

        // 针对上面注释的代码进行优化
        // 因为最终只可能有一条链表没有遍历完成, 而且链表本身也是有序的,所以直接加到新链表的next即可
        head.next = l1 == null ? l2 : l1;
        Printer.printLinkedList(prev.next);
        return prev.next;
    }

    /**
     * 使用递归的方式, 将链表从大到小(node.next)进行组装, 时间复杂度为O(m+n)
     *
     * 递归往内直到终止条件前,都是按值小的先递归,这样一旦递归条件终止, 之后开始递归计算, 就把返回的剩下的大节点附加到小节点的.next后面,
     *
     * 依次往外计算, 小的.next = 大的, 最后得到结果 从小到大的完整链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListsByRecursion(ListNode l1, ListNode l2) {
        // 如果l1递归结束,说明l2剩下的节点比l1大, 于是返回l2, 让前面的l1.next接上
        if (l1 == null) {
            System.out.print("l1递归往内终止完成, l2 = ");
            Printer.printLinkedList(l2);
            return l2;
        }
        // 如果l2递归结束, 说明l1剩下的节点比l2大, 于是返回l1, 让前面的l2.next接上
        else if (l2 == null) {
            System.out.print("l2递归往内终止完成, l1 = ");
            Printer.printLinkedList(l1);
            return l1;
            // 递归的顺序是从小的节点开始往内递归, 从小往大,所以到了最内层结束都的节点都是最大的. 之后从内层外外计算,是从大往小计算.
        } else if (l1.val < l2.val) {
            // 如果l1的当前节点比l2小, 那么l1.next与排序好的表头相接, 为什么是接表头? 因为递归是从里往外开始计算的,
            // 里是最后一个节点(最大的节点), 而l1是前面的小节点, 顺序是从小到大
            System.out.println("l1递归往内找终止条件");
            Printer.printLinkedList(l1);
            // 对l1进行递归遍历
            // 真正计算是从递归最里层往外
            l1.next = mergeTwoListsByRecursion(l1.next, l2);// l1.next 后面接的是递归里层的比自己大的节点
            System.out.println("l1递归往外,开始计算:");
            Printer.printLinkedList(l1);
            return l1;// 返回l1, 供外层递归的 l1或者l2中的
        } else {
            // 如果l2的节点小于l1, 那么使用l2的next与排序好的表头相接
            System.out.println("l2递归往内找终止条件");
            Printer.printLinkedList(l2);
            // 对l2开始递归, 真正计算是从递归最里层往外, 从小往大, l2.next 接的节点都是比它大的
            l2.next = mergeTwoListsByRecursion(l1, l2.next);
            System.out.println("l2递归往外,开始计算:");
            Printer.printLinkedList(l2);
            return l2;
        }
    }


    public static ListNode mergeTwoListsByRecursion2(ListNode l1, ListNode l2) {
        // 哪个链表先递归到终止条件为null, 说明另一个链表的剩下节点比该链表大, 于是返回对方剩余链表, 加在自己的.next后面
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // 先递归小的, 然后将后面大的放在小的.next上面
        if (l1.val < l2.val) {
            // l1的当前节点小就先递归l1
            l1.next = mergeTwoListsByRecursion2(l1.next, l2);
            return l1;
        } else {
            // l2的当前节点小那就递归l2
            l2.next = mergeTwoListsByRecursion2(l1, l2.next);
            return l2;
        }
    }
}
