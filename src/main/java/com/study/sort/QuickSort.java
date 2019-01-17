package com.study.sort;

/**
 * 快速排序算法
 *
 * 最简单的理解: 确保数组里面每一个元素当做基准值后,前后的元素都比它小或者大. 即完成排序.
 *
 *
 * 通过一轮的排序将序列分割成独立的两部分，其中一部分序列的关键字（这里主要用值来表示）均比另一部分关键字小。
 * 继续对长度较短的序列进行同样的分割，最后到达整体有序。在排序过程中，由于已经分开的两部分的元素不需要进行比较，故减少了比较次数，降低了排序时间。
 *
 * 快速排序在进行交换时，只是根据比较基数值判断是否交换，且不是相邻元素来交换，在交换过程中可能改变相同元素的顺序，因此是一种不稳定的排序算法。
 *
 * 时间复杂度介于O(nlogn) 和O(n^2) 之间
 *
 * https://www.cnblogs.com/foreverking/articles/2234225.html
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] a = {12, 20, 5, 16, 15, 1, 30, 5, 23, 9};
        int[] a = {12, 20, 9, 5, 16};
        //int[] a = {12, 1};
        int start = 0;
        int end = a.length - 1;
        sort(a, start, end);
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s ", a[i]);
        }
        System.out.println();
    }

    /**
     * 取一个元素作为基准值, 从后往前找到第一个比基准值小的,将这个小的元素a[end]与前面的元素a[start]元素进行交换
     * <p>
     * 然后从前往后找, 找到第一个比基准值大的,将这个大的元素a[start]与后面的元素a[end]进行交换
     * <p>
     * 一次前后循环过去, 一个小的元素挪到了前面, 一个大的元素挪到了后面
     * <p>
     * 继续循环, 又从后往前找比基准小的元素进行交换,接着又从前往后找比基准大的元素进行交换.
     * <p>
     * 反复循环,直到start和end重叠, 退出循环.
     * <p>
     * 此时,左边的值都比基准值小，右边的值都比基准值大，但是两边的顺序还有可能是不一样的.
     * <p>
     * 所以还需要进行递归调整一下, 递归分别从基准值左右两边进行调整
     */
    private static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        // 选取第一个元素作为基准值
        int key = a[low];

        while (end > start) {
            // 从后往前比较
            // 如果没有比基准值小的，比较下一个，直到有比基准值小的交换位置，交换的为start和end索引上的元素；然后又从前往后比较，
            while (end > start && a[end] >= key){
                end--;
            }

            if (a[end] < key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            // 从前往后比较
            // 如果没有比基准值大的，交换的为start和end索引上的元素，比较下一个，直到有比基准值大的交换位置,之后继续循环,又从后往前比较
            while (end > start && a[start] <= key){
                start++;
            }
            if (a[start] > key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }
        // 当循环结束后，基准值的位置已经确定了。左边的值都比基准值小，右边的值都比基准值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用

        //左边序列. 第一个索引位置到基准值索引-1
        if (start > low) {
            sort(a, low, start - 1);
        }

        //右边序列. 从基准值索引+1到最后一个
        if (end < high) {
            sort(a, end + 1, high);
        }
    }
}
