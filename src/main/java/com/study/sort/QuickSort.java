package com.study.sort;

import com.study.utils.ArrayUtils;

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
        ArrayUtils.print(a);
        int start = 0;
        int end = a.length - 1;
        //sort(a, start, end);
        sort2(a, start, end);
        ArrayUtils.print(a);
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
        // start,end 为基准值前后两个区间
        int start = low;// 最小索引,
        int end = high;// 最大索引
        // 选取第一个元素作为基准值
        // start和low都是基准值的索引位置,且start会变,所以基准值的位置会跟着start进行动态调整
        int key = a[low]; // 因为基准值也是数组中的元素,所以在前后替换的时候也会被调整位置
        System.out.println("基准值为: " + key);
        // 循环里 start索引往后挪, end索引往前挪
        while (end > start) {
            System.out.println("从后往前找<<");
            // 从后往前找
            // 找出后面"比基准值小"的元素, 并挪到基准值前面去
            // 如果后面的元素"大于基准值", 那就继续往前找,直到找到小于基准值的,则将前后元素进行替换
            while (end > start && a[end] >= key) {
                end--; //修改最大索引,不停往前找
            }
            // 把后面的元素和基准值元素进行替换
            if (a[end] < key) {
                System.out.println(String.format("找到后面比基准值%s小的元素%s, 准备和前面的元素进行替换", key, a[end]));
                // 将后面比基准值小的元素放到基准值前面
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
                System.out.println(String.format("将%d和%d进行对替换", a[end], a[start]));
                ArrayUtils.printARangeForArray(a, low, high);
            }

            System.out.println("从前往后找>>");
            // 从前往后找
            // 找出前面 "比基准值大"的元素, 挪到基准值后面去
            // 如果前面的元素 "比后面小" 且 "比基准值小", 那就继续往后找, 直到找到 "比后面大" 或者 "大于基准值的", 则将前后元素进行替换
            while (end > start && a[start] <= key) {
                start++; // 修改最小索引,不停的往后找
            }
            // 把大于基准值或大于基准值后边的元素 放到后面 (前后替换)
            if (a[start] > key) {
                System.out.println(String.format("找到前面比基准值%s大的元素%s, 准备和后面的元素进行替换", key, a[start]));
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                System.out.println(String.format("将%d和%d进行对调", a[start], a[end]));
                ArrayUtils.printARangeForArray(a, low, high);
            }
        }
        System.out.print("第一轮循环结束, 数组为:");
        ArrayUtils.printARangeForArray(a, low, high);
        // 当循环结束后，基准值的位置已经确定了。start左边的值都小于或等于基准值，end右边的值都大于或等于基准值，但是两边的顺序还有可能是不一样的.

        // 于是将start和end左右两边划分成两个区间, 不停的进行二分分区, 最后等左右两个区间都完成排序后,那么整个数组就变成有序了
        // 不管是左边还是右边区间, 都取第0个作为基准值
        // 将左边序列进行排序
        // 将基准值左边的区域进行再次排序
        if (start > low) {
            System.out.print("基准值左边区间开始排序, 数组为");
            ArrayUtils.printARangeForArray(a, low, start -1);
            sort(a, low, start - 1); // 取左边数组第0个做基准值, 最小索引不变, 然后最大范围不断缩小
            System.out.println("start: " + start);
        }

        // 将基准值右边的区域进行再次排序
        if (end < high) {
            System.out.print("基准值右边区间开始排序, 数组为");
            ArrayUtils.printARangeForArray(a, end + 1, high);
            sort(a, end + 1, high); // 取右边数组第0个作基准值,  最大索引不变, 最小范围不断加大
            System.out.println("end: " + end);
        }
    }

    private static void sort2(int[] arr, int low, int high) {
        // 设置索引
        int start = low;
        int end = high;
        // 初始的时候选第一个元素为基准值
        int key = arr[low]; // 基准值值不变,但是基准值的位置会随着start的变化而变化
        System.out.println("当前基准值为:" + key);
        // 开始一轮循环,目的是确保基准值前面的元素都比它小,后面的元素都比它大
        while(start < end){
            // 从后往前找比基准值小的,挪到前面去
            while(start < end && key <= arr[end]){ // 后面元素和基准值相等的情况下无需挪动位置
                end --; // 如果大于基准值,则继续往前找
            }
            // 比基准值小, 跟基准值进行位置替换,因为基准值的位置为start,所以基准值会跟着start的变动而变动
            if(key > arr[end]){
                // 将基准值和后面的元素进行交换,确保小的元素在基准值前面
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }

            // 从前往后找比基准值小的,挪到后面去
            while(start < end && key >= arr[start]){
                start ++; // 如果小于基准值,则继续往后找
            }
            if(key < arr[start]){
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        System.out.println("一轮循环结束");
        ArrayUtils.printARangeForArray(arr, low, high);

        // 虽然基准值左边元素都比基准值小,右边都比基准值大,但是他们的顺序不一定是有序的;
        // 所以需要将左右两边进行进一步拆分,使得每个位置的基准值左右两边都是满足的,这样整个数组就是完全有序的

        // 如果左边没有排序(拆分)完, 基准值左边的元素不是第0个
        if(start > low){ // 左边还没拆分完
            // 左边继续拆分排序
            sort2(arr, low, start -1); // 上面排序完start和基准值位置重合了,不用再排序基准值本身,所以需要-1
        }
        // 如果右边没有拆分完,基准值右边元素不是最后一个
        if(end < high){
            sort2(arr, end+1, high); // 上面排序完end和基准值位置重合了,不用再排序基准值本身,所以需要+1
        }
    }
}
