package com.study.array;

import java.util.Arrays;

/**
 * 在不开辟新数组的情况下 合并两个升序数组, 使用归并排序
 * <p>
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class MergeTwoASCSortedArray2 {

    public static void main(String[] args) {
        int[] num1 = {2, 3, 4, 0, 0, 0, 0};
        int[] num2 = {1, 2, 5, 6};

        MergeTwoASCSortedArray2 m = new MergeTwoASCSortedArray2();
        //m.merge(num1, 3, num2, 4);
        //m.merge2(num1, 3, num2, 4);
        //m.merge3(num1, 3, num2, 4);
        m.merge4(num1,3,num2,4);
    }

    /**
     * 最简单的办法就是先合并,再排序. 时间复杂度较差为 O((n+m)log(n+m))
     * 因为没有用到两个数组本身已经排好顺序这一点
     *
     * @param nums1 目标数组
     * @param m     目标数组开始放置新元素的位置
     * @param nums2 源数组
     * @param n     目标数组需要放置新元素的长度
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 源数组 源数组开始的位置  目标数组  目标数组开始放置新元素的位置  目标数组需要放置新元素的长度
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);

        print(nums1);
    }

    /**
     * 使用一个新数组 来存放合并结果
     * 时间复杂度 : O(n + m)O(n+m)
     * 空间复杂度 : O(m)O(m)
     *
     * @param nums1 目标数组
     * @param m     目标数组开始放置新元素的位置
     * @param nums2 源数组
     * @param n     目标数组需要放置新元素的长度
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);

        print(nums1);
    }

    /**
     * 不开辟新的数组, 直接在nums1上面做调整, 从nums1的尾部开始插入元素
     *
     * @param nums1 目标数组
     * @param m     目标数组开始放置新元素的位置
     * @param nums2 源数组
     * @param n     目标数组需要放置新元素的长度
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // nums1数组有效元素的最大索引
        int p2 = n - 1; // nums2数组有效元素的最大索引

        int p = m + n - 1;
        // 将两个数组的有效元素从后往前做比较, 大的元素放在nums1的后面
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] < nums2[p2]) {
                nums1[p--] = nums2[p2--];
            } else {
                nums1[p--] = nums1[p1--];
            }
        }

        // 上面循环完, 可能因为mums2的元素更多,导致还有元素没有拷贝到nums1中
        // 将nums2剩余的最小的几个元素拷贝到mums1的最前面
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);

        print(nums1);
    }


    /**
     * @param nums1 目标数组
     * @param m     目标数组从哪个位置开始放置源数组元素
     * @param nums2 源数组
     * @param n     需要将源数组元素拷贝到目标数组的个数
     */
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // p1为nums1有效元素的最大索引, 而m比nums1最大索引还要大1
        int p2 = n - 1;// p2为nums2的最大索引

        int p = m + n - 1; // 新数组的最大索引

        // 将nums1和nums2的有效元素从后往前做比较, 并将大的元素从后往前放到nums1中
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }

        // 如果nums2的长度比nums1的有效长度还长, 那么循环结束后, nums2前面还有一些元素未拷贝到nums1中
        // 将nums2剩余的最小元素拷贝到nums1的前面
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);

        print(nums1);
    }

    private static void print(int[] nums){
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
