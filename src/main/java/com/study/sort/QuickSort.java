package com.study.sort;

/**
 * 快速排序算法
 * <p>
 * 最简单的理解: 确保数组里面每一个元素当做基准值后,前后的元素都比它小或者大. 即完成排序.
 * <p>
 * <p>
 * 通过一轮的排序将序列分割成独立的两部分，其中一部分序列的关键字（这里主要用值来表示）均比另一部分关键字小。
 * 继续对长度较短的序列进行同样的分割，最后到达整体有序。在排序过程中，由于已经分开的两部分的元素不需要进行比较，故减少了比较次数，降低了排序时间。
 * <p>
 * 快速排序在进行交换时，只是根据比较基数值判断是否交换，且不是相邻元素来交换，在交换过程中可能改变相同元素的顺序，因此是一种不稳定的排序算法。
 * <p>
 * 时间复杂度介于O(nlogn) 和O(n^2) 之间
 * https://www.cnblogs.com/yueansixing/articles/9125634.html
 * <p>
 * https://www.cnblogs.com/foreverking/articles/2234225.html
 */
public class QuickSort {

    /**
     * 快速排序算法有很多: 挖坑法, 左右指针法, 前后指针法
     *
     * @param args
     */
    public static void main(String[] args) {
        //int[] a = {12, 20, 5, 16, 15, 1, 30, 45};
        //int[] a = {21, 32, 43, 98, 54, 45, 23, 4, 66, 86};
        int[] a = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        // int[] a = {12, 20, 9, 5, 16};
        //int[] a = {12, 1};
        print(a);
        int start = 0;
        int end = a.length - 1;
        //sort(a, start, end);
        sort2(a, start, end);
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
     * 此时, start左边的值都小于或等于基准值，右边的都大于或等于基准值，但是两边的顺序还有可能是不一样的.
     * <p>
     * 所以还需要进行递归调整一下, 递归分别从基准值左右两边进行调整
     */
    private static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        // 选取第一个元素作为基准值
        int key = a[low];
        System.out.println("基准值为: " + key);
        // 循环里 start往后挪, end往前挪
        while (end > start) {
            // 从后往前比较
            // 如果没有比基准值小的，比较下一个，直到有比基准值小的交换位置，将此时start和end上的元素进行交换；然后又从前往后比较，
            while (end > start && a[end] >= key) {
                end--;
            }
            // 把小于基准值的放到基准值左边
            if (a[end] < key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
                System.out.println(String.format("从后往前: 将%d和%d进行对调", a[end], a[start]));
                print(a);
            }
            // 从前往后比较
            // 如果没有比基准值大的，交换的为start和end索引上的元素，比较下一个，直到有比基准值大的交换位置,之后继续循环,又从后往前比较
            while (end > start && a[start] <= key) {
                start++;
            }
            // 把大于基准值的放到基准值右边
            if (a[start] > key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                System.out.println(String.format("从前往后: 将%d和%d进行对调", a[start], a[end]));
                print(a);
            }
        }
        // 当循环结束后，基准值的位置已经确定了。start左边的值都小于或等于基准值，end右边的值都大于或等于基准值，但是两边的顺序还有可能是不一样的.

        // 于是将start和end左右两边划分成两个区间, 不停的进行二分分区, 最后等左右两个区间都完成排序后,那么整个数组就变成有序了
        // 不管是左边还是右边区间, 都取第0个作为基准值
        // 将左边序列进行排序
        if (start > low) {
            sort(a, low, start - 1); // 取左边数组第0个做基准值, 最小索引不变, 然后最大范围不断缩小
            System.out.println("start: " + start);
        }

        // 将右边序列进行排序
        if (end < high) {
            sort(a, end + 1, high); // 取右边数组第0个作基准值,  最大索引不变, 最小范围不断加大
            System.out.println("end: " + end);
        }
    }

    private static void sort2(int[] arr, int low, int high) {
        int start = low;
        int end = high;

        int key = arr[low];

        while (start < end) {
            // end从后往前和基准值做比较, 小于基准值就和start进行交换
            while (end > start && arr[end] >= key) {
                end--;
            }

            //如果后面存在元素小于基准值, 将将start和end位置上的元素互换
            if (arr[end] < key) {
                int tmp = arr[end];
                arr[end] = arr[start];
                arr[start] = tmp;
            }

            while (end > start && arr[start] <= key) {
                start++;
            }

            //如果前面有元素大于基准值, 就将start和end位置上的元素互换
            if (arr[start] > key) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
        }

        if (start > low) {
            sort2(arr, low, start - 1);
        }

        if (end < high) {
            sort2(arr, end + 1, high);
        }
    }

    private static void print(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
        System.out.println();
    }
}
