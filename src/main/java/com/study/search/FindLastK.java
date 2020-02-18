package com.study.search;

/**
 * 找到数组中最后一个目标元素
 */
public class FindLastK {

    public static void main(String[] args) {
        //int[] arr = {1, 2, 2, 2, 2, 2, 3, 3, 4, 5};
        int[] arr = {1, 2, 3, 3, 4, 5};
        //int[] arr = {2};
        System.out.println(find(arr, 2));
        //System.out.println(find2(arr, 2));
    }

    /**
//    private static int findLastSameElement(int[] arr, int target) {
//
//        if (arr.length == 1) {
//            if (arr[0] == target) {
//                return 0;
//            } else {
//                return -1;
//            }
//        }
//
//        int left = 0;
//        int right = arr.length - 1;
//
//
//        while (left <= right) {
//            int middle = (left + right) / 2;
//            // 当前位置元素大于目标元素
//            if (arr[middle] > target) {
//                // 如果上一个元素为目标元素
//                if (arr[middle - 1] > 0 && arr[middle - 1] == target) {
//                    return middle - 1; // 返回上一个元素索引
//                } else {
//                    // 从左边找
//                    right = middle - 1;
//                }
//                // 当前位置元素小于目标元素
//            } else if (arr[middle] < target) {
//                // 从右边找
//                left = middle + 1;
//            } else {
//                // 目标元素
//                // 后面存在目标元素, 继续往后找
//                if (arr[middle + 1] < arr.length && arr[middle + 1] == target) {
//                    left = middle;
//                    // 如果后面元素大于目标元素, 那当前元素就是结果
//                } else if (arr[middle + 1] < arr.length && arr[middle + 1] > target) {
//                    return middle;
//                }
//            }
//        }
//        return -1;
//    }
     */

    private static int find(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            // 目标元素小于当前元素
            if (arr[mid] > target) {
                right = mid - 1; //把范围放到左边找
                // 目标元素大于当前元素
            } else if (arr[mid] < target) {
                left = mid + 1; //把范围放到右边找
            } else {
                // 找到值相等的元素, 需要判断下一个是否为非值相同的元素
                // 如果下一个值与目标元素不同,则说明当前元素为目标元素
                // 这里需要特殊考虑一下数组只有一个元素的情况
                if (mid == arr.length - 1 || (mid + 1 <= arr.length -1 && arr[mid + 1] != target)) { // 索引+1或者-1需要考虑数组越界的情况
                    return mid;
                } else {//否则继续往右找,直到找到下一个元素值与目标元素不同
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static int find2(int[] array, int target) {
        int left = 0, mid = 0, right = array.length - 1;

        while (left <= right) {
            mid = (right - left) >> 1 + left;

            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else { //相等的前提下
                if (mid == array.length - 1 || (array[mid + 1] != target)) {
                    return mid;
                } else {
                    left = mid + 1; //往后找
                }
            }
        }
        return -1;
    }


    private static int findLastK2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = (left + right + 1) >> 1; //保证取到中间靠后的位置
            //int temp = (left + right + 1) / 2; //保证取到中间靠后的位置
            // 当前位置元素大于目标元素
            if (arr[temp] > target) {
                // 往左边找, 位置-1
                right = temp - 1;
            } else {
                // 如果当前位置元素小于或等于目标元素, 往右边找
                left = temp;
            }
        }
        return right;
    }
}
