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

        System.out.println(search(arr, 1));
    }

    /**
     * 返回指定元素的位置
     *
     * @param arr
     * @param key
     * @return
     */
    private static int search(int[] arr, int key) {
        //第1个元素索引
        int low = 0;

        //最后一个元素索引
        int high = arr.length - 1;
        while (low <= high) {
            // 先从中间开始查找
            //int mid = (low + high) / 2; // low + high 可能会大于 Integer.MAX_VALUE
            int mid = ((high - low) >> 1) + low;
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
}
