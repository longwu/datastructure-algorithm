package com.study.search;

/**
 * 旋转数组的二分查找问题
 * <p>
 * 截取一个有序数组的后面一段放到前面, 求整个数组中的最小元素
 * <p>
 * 比如有序数组为 [2, 3, 6, 7, 8, 10, 12, 15], 将后面的10,12,15截取后放到数组的最前面,变成[10, 12, 15, 2, 3, 6, 7, 8]
 * <p>
 * 问题一:  请写一个算法, 找出最小值
 * <p>
 * 问题二:  给定一个数，查找其在序列中是否存在（返回其位置），请问如何实现？
 * <p>
 * 问题三: 给定任意一个序列，找出其中的一个谷/峰，谷的定义为两边的数均大于某个数。
 * <p>
 * https://segmentfault.com/q/1010000000181714
 * <p>
 * https://blog.csdn.net/weixin_34018169/article/details/89698260?utm_source=distribute.pc_relevant.none-task
 */
public class FindSmall {

    public static void main(String[] args) {
        int[] arr = {10, 12, 15, 2, 3, 6, 7, 8};
        System.out.println("------找最小值");
        System.out.println(findMin(arr)); // 2
        System.out.println(findMin2(arr)); // 2
        System.out.println("------找目标值");
        System.out.println(findTarget(arr, 2)); //3
        System.out.println(findTarget(arr, 10));//0
        System.out.println(findTarget(arr, 12));//1
        System.out.println(findTarget(arr, 15));//2
        System.out.println(findTarget(arr, 8));//7
        System.out.println(findTarget2(arr, 2)); //3
        System.out.println(findTarget2(arr, 10));//0
        System.out.println(findTarget2(arr, 12));//1
        System.out.println(findTarget2(arr, 15));//2
        System.out.println(findTarget2(arr, 8));//7

        arr = new int[]{10, 12, 15, 2};
        System.out.println("------找最小值");
        System.out.println(findMin(arr)); // 2
        System.out.println(findMin2(arr)); // 2
        System.out.println("------找目标值");
        System.out.println(findTarget(arr, 2));//3
        System.out.println(findTarget(arr, 10));//0
        System.out.println(findTarget(arr, 12));//1
        System.out.println(findTarget(arr, 15));//2
        arr = new int[]{10, 2};
        System.out.println("------找最小值");
        System.out.println(findMin(arr)); // 2
        System.out.println(findMin2(arr)); // 2
        System.out.println("------找目标值");
        System.out.println(findTarget(arr, 2));//1
        System.out.println(findTarget(arr, 10));//0
        System.out.println(findTarget(arr, 12));//-1
        arr = new int[]{2};
        System.out.println("------找最小值");
        System.out.println(findMin(arr)); // 2 只有一个元素的时候,返回本身
        System.out.println(findMin2(arr)); // 2
        System.out.println("------找目标值");
        System.out.println(findTarget(arr, 2));//0
        System.out.println(findTarget(arr, 10));//-1

        arr = new int[]{3, 4, 5, 1, 1, 2};
        //arr =  new int[]{1, 0, 1, 1, 1};
        System.out.println("------找最小值");
        System.out.println(findMin(arr)); // 2 只有一个元素的时候,返回本身
        System.out.println(findMin2(arr)); // 2
        System.out.println("------找目标值");
        System.out.println(findTarget(arr, 2));//5
        System.out.println(findTarget(arr, 3));//0
        System.out.println(findTarget(arr, 1));//4
    }

    /**
     * 问题一:  请写一个算法, 找出最小值
     * <p>
     * 思路: 跟第0个元素作比较, 如果大于或等于第0个元素, 就往后找. 如果小于第0个元素, 就往前找.
     * <p>
     * 找到的条件是 当前元素小于前一个元素, 那么当前元素就是最小元素.
     * <p>
     * 注意: 需要考虑数组只有一个元素的情况
     * <p>
     * 时间复杂度O(logN)
     *
     * @param arr
     * @return
     */
    private static int findMin(int[] arr) {
        int left = 0, right = arr.length - 1;

        if (arr.length == 1)
            return arr[0];

        // 找到目标为前面一个大于后面
        while (left <= right) {
            // 如果数组长度为1或者2的时候, mid长度为0
            int mid = ((right - left) >> 1) + left;
            // 当前元素大于等于第一个, 往后找
            if (arr[mid] >= arr[0]) {
                left = mid + 1;
            } else
                // 当前元素小于第一个,往前找
                // 找到条件为上一个大于当前这个
                if (mid == 0 || arr[mid] < arr[mid - 1]) //通常mid == 0 || mid < mid -1 是常用的手法. 这样能避免mid-1越界, 也能处理数组长度为1的情况
                    return arr[mid];
                else// 否则继续往前走
                    right = mid - 1;
        }
        return -1;
    }

    /**
     * 问题一:  请写一个算法, 找出最小值
     * 解法二
     * https://blog.csdn.net/weixin_34018169/article/details/89698260?utm_source=distribute.pc_relevant.none-task
     *
     * @param nums
     * @return
     */
    public static int findMin2(int nums[]) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("input error!");
        }
        // 如果只有一个元素，直接返回
        if (nums.length == 1)
            return nums[0];
        int result = nums[0];
        int low = 0, high = nums.length - 1;
        int mid;
        // 确保 low 下标对应的值在左边的递增子数组，high 对应的值在右边递增子数组
        while (nums[low] >= nums[high]) {
            // 确保循环结束条件
            if (high - low == 1) {
                return nums[high];
            }
            // 取中间位置
            mid = (low + high) / 2;
            // 代表中间元素在左边递增子数组
            if (nums[mid] >= nums[low]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return result;
    }

    /**
     * 给定一个数，查找其在序列中是否存在（返回其位置），请问如何实现？
     *
     * 实现方案一: 按 目标元素所处的位置(前半段或后半段), 当前元素和目标元素大小比较, 当前元素的位置(前半段或后半段) 的顺序来处理
     * <p>
     * 思路: 需要考虑目标元素和当前元素所处的位置, 与第0个元素做比较.
     *       如果目标元素在前半段,
     *                          当前元素小于目标元素
     *                                             当前元素在前半段 怎么做
     *                                             当前元素在后半段 怎么做
     *
     *                           当前元素大于目标元素
     *                                             当前元素[肯定]在前半段 怎么做
     *
     *       目标元素在后半段
                                当前元素小于目标元素
     *                                             当前元素[肯定]在后半段 怎么做
     *
     *                           当前元素大于目标元素
     *                                             当前元素在前半段 怎么做
     *                                             当前元素在后半段 怎么做
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findTarget(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 目标元素在前半段
            if (target >= arr[0]) {
                // 如果当前元素小于目标元素
                if (arr[mid] < target) {
                    // 需要判断一下当前元素所处的区域是前半段还是后半段
                    // 如果当前元素在前半段
                    if (arr[mid] >= arr[0]) {
                        //往后找
                        left = mid + 1;
                    } else {
                        //如果当前元素在后半段
                        //往前找
                        right = mid - 1;
                    }
                    //如果当前元素大于目标元素
                    //当前元素也在前半段
                } else if (arr[mid] > target) {
                    //直接往前找即可
                    right = mid - 1;
                } else {
                    // 在前半段找到目标
                    return mid;
                }
                //如果目标元素在后半段
            } else  {
                // 当前元素小于目标元素
                if (arr[mid] < target) {
                    //往后找即可
                    left = mid + 1;
                    // 当前元素大于目标元素
                } else if (arr[mid] > target) {
                    // 需要判断一下当前元素处于前后哪个半段
                    // 如果在前半段
                    if (arr[mid] >= arr[0]) {
                        // 往后找
                        left = mid + 1;
                    } else {
                        //在后半段,往前找即可
                        right = mid - 1;
                    }
                } else {
                    // 在后半段找到目标
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * 实现方案二: 按 目标元素所处的位置(前半段或后半段), 当前元素的位置(前半段或后半段), 当前元素和目标元素大小比较 的顺序来处理
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findTarget2(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 目标元素在前半段
            if (target >= arr[0]) {
                // 如果当前元素也处于前半段
                if (arr[mid] >= arr[0]) {
                    // 如果当前元素大于目标元素, 往前找
                    if (arr[mid] > target) {
                        right = mid - 1;
                        // 小于则往后找
                    } else if (arr[mid] < target) {
                        left = mid + 1;
                    } else {
                        // 或者等于
                        return mid;
                    }
                    // 如果当前元素处于后半段
                } else if (arr[mid] < arr[0]) {
                    // 往前找即可
                    right = mid - 1;
                }
            } else {
                // 目标元素在后半段
                // 如果当前元素也处于后半段
                if (arr[mid] < arr[0]) {
                    // 如果当前元素大于目标元素 往前找
                    if (arr[mid] > target) {
                        right = mid - 1;
                        // 小于目标元素 往后找
                    } else if (arr[mid] < target) {
                        left = mid + 1;
                    } else {
                        // 否则等于 返回坐标
                        return mid;
                    }
                    //如果当前元素在前半段 直接往后找
                } else if (arr[mid] >= arr[0]) {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
