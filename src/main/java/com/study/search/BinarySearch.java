package com.study.search;

/**
 * 二分查找, 也叫折半查找
 * <p>
 * 适用于已经排好顺序的数组, 如果是没有排好序的数组不建议先排序再二分,
 * 因为排序最快的速度也要O(nlogn),然后还要进行二分查找O(logn),而直接查找也只需要O(n)
 * <p>
 * 时间复杂度O(logn), 远远好于顺序查找的O(n)。
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //System.out.println(search(arr, 1));
        System.out.println(search2(arr, 3));
    }

    /**
     * 返回指定元素的位置
     *
     * @param arr
     * @param key
     * @return
     */
    private static int search(int[] arr, int key) {
        int low, high, mid;
        //第1个元素索引
        low = 0;
        //最后一个元素索引
        high = arr.length - 1;
        while (low <= high) {
            // 先从中间开始查找
            mid = (low + high) / 2;
            // 如果小于中间的,就把范围改成最左边到中间-1
            if (key < arr[mid]) {
                high = mid - 1;
                // 如果大于中间的,就把范围改成中间+1到最右边
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                //最后找到了返回元素位置
                return mid;
            }
        }
        //没找到返回-1
        return -1;
    }


    /**
     * 折半查找,每次查找都基于中间值来对比 来缩小查找范围, 时间复杂度为O1
     *
     * @param arr
     * @param element
     * @return
     */
    private static int search2(int[] arr, int element) {
        int low = 0, high = arr.length - 1;

        // 循环条件是最低位索引小于等于最高位索引
        while (low <= high) {
            int mid = (low + high) / 2;
            // 如果元素大于中间值, 则下次从右边查找
            if (arr[mid] < element) {
                // 将最小索引改为中间+!
                low = mid + 1;
            } else if (arr[mid] > element) {
                // 将最大索引改为中间-1
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
