package com.study.linkedlist;

import com.study.utils.LinkedListUtils;

/**
 * 找到链表中间的元素,
 * <p>
 * 对于单链表可以使用使用快慢双指针,快指针走完,慢指针所在的位置即是中间.
 * 对于双链表,可以使用快慢双指针, 如果知道头尾两个节点, 可以使用前后两个指针以相同的速度往中间走.
 *
 * <p>
 * https://blog.csdn.net/lihui930310560/article/details/53319367/
 */
public class FindMiddleOne {

    public static void main(String[] args) {

        //int length = 8;
        int length = 9;

        // 创建一个长度为20的 单向链表
        LinkedNode head = new LinkedNode(1);
        LinkedNode cur = head;

        int i = 2;
        while (i <= length) {
            cur.next = new LinkedNode(i);
            cur = cur.next;
            i++;
        }

        LinkedListUtils.printLinkedList(head);

        // 找出单向链表中间的元素
        System.out.println(findMiddle(head));

        //创建一个双向链表
        DoubleLinkedNode head2 = new DoubleLinkedNode(1);

        DoubleLinkedNode cur2 = head2;
        i = 2;
        while (i <= length) {
            DoubleLinkedNode nextNode = new DoubleLinkedNode(i);
            cur2.next = nextNode;
            nextNode.prev = cur2;

            cur2 = cur2.next;
            i++;
        }

        LinkedListUtils.printLinkedList(head2);
        // 找出双向链表中间的元素
        System.out.println(findMiddle(head2));

        // 通过头尾节点找出中间元素
        System.out.println(findMiddle(head2, cur2));
    }

    /**
     * 找到单向链表的中间节点
     * 可以使用一个快慢指针, 一个走一步,一个走两步
     *
     * @param head
     */
    private static int findMiddle(LinkedNode head) {
        if (head == null)
            return -1;

        // 默认都已经走了1步
        LinkedNode slow = head;
        LinkedNode fast = head;

        while (fast != null && slow != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            // 如果快指针刚好走完或者差半步走完,结束旅程
            if (fast.next == null || fast.next.next == null)
                break;
        }

        // 由于大家都是从第一个节点开始 1 + 2 * n
        // 如果快指针的下一个节点为空,说明链表长度为奇数,则当前慢指针的值为中间值
        if (fast.next == null)
            return slow.val;

        // 如果快指针差一步走完, 链表长度为偶数, 则当前慢指针的值也是中间值
        if (fast.next.next == null)
            return slow.val;

        return -1;
    }

    /**
     * 找到双向链表中间的元素, 使用快慢双指针
     *
     * @param head
     * @return
     */
    private static int findMiddle(DoubleLinkedNode head) {
        if (head == null)
            return -1;

        // 默认都已经走了1步
        DoubleLinkedNode slow = head;
        DoubleLinkedNode fast = head;

        while (fast != null && slow != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            // 如果快指针刚好走完或者差半步走完,结束旅程
            if (fast.next == null || fast.next.next == null)
                break;
        }

        // 由于大家都是从第一个节点开始 1 + 2 * n
        // 如果快指针的下一个节点为空,说明链表长度为奇数,则当前慢指针的值为中间值
        if (fast.next == null)
            return slow.val;

        // 如果快指针差一步走完, 链表长度为偶数, 则当前慢指针的值也是中间值
        if (fast.next.next == null)
            return slow.val;

        return -1;
    }

    /**
     * 使用前后双指针, 一个从头开始走, 一个从尾开始走, 走到相碰的地方就是
     *
     * @param head
     * @return
     */
    private static int findMiddle(DoubleLinkedNode head, DoubleLinkedNode tail) {

        DoubleLinkedNode newHead = head;
        DoubleLinkedNode newTail = tail;

        int middle = -1;

        while (newHead != null && newTail != null) {

            // 链表长度为奇数的情况下,两个指针会走在同一个节点上,则该节点就是中间节点
            // 链表长度为偶数的情况下,头指针所在的节点为中间点
            if (newHead == newTail || newHead.next == newTail)
                middle = newHead.val;

            newHead = newHead.next;// 头节点往后走
            newTail = newTail.prev;// 尾节点往前走
        }

        return middle;
    }
}
