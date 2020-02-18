package com.study.search;

/**
 * 找出有序数组中绝对值最小的元素
 */
public class MinAbs {

    public static void main(String[] args) {
        // 一个升序整形数组, 找到绝对值最小的输出

        //int[] arr = {-3,-2,-1,5};
        //int[] arr = {-3, -2, -1, 0, 3, 5};
        //int[] arr = {-1, 1, 3, 5};

        //int[] arr = {1, 3, 5};
        //int[] arr = {-9, -6, - 2, 0};
        //int[] arr = {-6};
        int[] arr = {};
        System.out.println(min(arr));
    }

    /**
     * 利用二分查找进行,使得时间复杂度为O(logN), 低于遍历的O(N)
     *
     * @param arr
     * @return
     */
    private static int min(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            //int middle = (left + right) / 2; // 这段代码可能越界
            int middle = left + (right - left) >>1; //防止越界
            // 大于0往左找最近的负数
            if (arr[middle] > 0) {
                // 判断下一个数是否为负数
                if (middle - 1 >= 0 && arr[middle - 1] < 0) {
                    // 返回正负交届的绝对值最小的那个数
                    if (Math.abs(arr[middle]) > Math.abs(arr[middle - 1]))
                        return arr[middle - 1];
                    else
                        return arr[middle];
                } else
                    right = middle - 1; //从左边找, 右边界需要减一,因为有边界这个元素不满足
                // 小于0往右找最近的正数
            } else if (arr[middle] < 0) {
                // 判断下一个数是否为正数 (注意边界)
                if (middle + 1 < arr.length &&  arr[middle + 1] > 0)
                    // 返回正负交届的绝对值最小的那个数
                    if (Math.abs(arr[middle]) > Math.abs(arr[middle + 1]))
                        return arr[middle + 1];
                    else
                        return arr[middle];
                else
                    left = middle + 1; // 从左边找 左边界需要+1,因为边界这个元素不满足
            } else {
                // 如果为0,直接返回 (0是所有数中绝对值最小的)
                return arr[middle];
            }
        }
        return -1;
    }
}
