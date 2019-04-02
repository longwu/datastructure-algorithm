package com.study.number;

import java.util.PriorityQueue;

/**
 * 数据流中的第K大元素
 * <p>
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/comments/
 */
public class KthLargest {
    private int k;

    /**
     * PriorityQueue特点是每次插入元素都会将所有元素进行排序,并将最小的元素放在堆顶部
     */
    private PriorityQueue<Integer> queue = null;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<Integer>(k);
        this.k = k;
        for (int n : nums) {
            add(n);
        }
    }

    /**
     * 往队列中添加新的元素
     *
     * @param element
     * @return
     */
    public int add(int element) {
        //如果队列中的元素没有满, 则往里添加
        if (queue.size() < k) {
            queue.offer(element);
        } else {
            // 如果队列中的元素满了, 则判断队列最小的元素(顶端的元素)是否比新元素小
            // 如果最小的元素比该元素小, 那将顶端最小的元素移除, 将新元素插入
            if (queue.peek() < element) {
                queue.poll(); //移除顶端最小元素
                queue.offer(element);//插入新元素
            }
        }
        return queue.peek(); //返回最小的元素
    }

    /**
     * PriorityQueue 优先队列会将里面的元素进行从小到大排序, 每次插入一个新元素后,都会将最小的元素放到最上面
     * <p>
     * 优先队列的作用是能保证每次取出的元素都是队列中权值最小的
     */
    public static void main(String[] args) {
        int k = 3;
        int[] arr = {8, 5, 4, 2};
        KthLargest kthLargest = new KthLargest(k, arr);
        // 每次加入新元素都返回第k大的元素
        System.out.println(kthLargest.add(8)); // 第k大的元素为5
        System.out.println(kthLargest.add(6)); // 第k大的元素为6
        System.out.println(kthLargest.add(4)); // 第k大的元素为6
        System.out.println(kthLargest.add(10)); // 第k大的元素为8
        System.out.println(kthLargest.add(9)); // 第k大的元素为8
    }
}


