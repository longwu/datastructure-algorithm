package com.study.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和小于target
 * <p>
 * 题目描述：给定一个n个整数的数组和一个目标整数target，找到下标为i、j、k的数组元素0 <= i < j < k < n，
 * 满足条件nums[i] + nums[j] + nums[k] < target。输出满足上述条件的组合的总数。
 */
public class ThreeSumSmaller {

    public static void main(String[] args) {
        int[] nums = {5, 1, 7, 2, 8, 9, 3, 4};

        //List<Integer> arr = threeSumSmaller(nums, 9);
        List<Integer> arr = threeSumSmaller(nums, 12);

        for (int i = 0; i < arr.size(); i += 3) {
            System.out.println(String.format("%d %d %d", arr.get(i), arr.get(i + 1), arr.get(i + 2)));
        }
    }

    private static List<Integer> threeSumSmaller(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 数组排序后方可使用双指针
        Arrays.sort(nums);

        // 枚举第一个元素, 并对另外的两个元素设置左右两个指针
        for (int i = 0; i < nums.length - 2; i++) {//由于left 和 right元素在第一个元素右边,所以第一个元素只需要遍历数组长度-3个即可
            int left = i + 1; //左边指针位置
            int right = nums.length - 1; //右边指针位置

            while (left < right) {
                // 左边保持位置不变, 如果right + left 满足条件, 那么从right指针从此处一直移动到left+1的元素都满足条件, 因为数组元素是从小到大排列的,最大的right满足,那更小的right都应该满足
                if (nums[left] + nums[right] < target - nums[i]) {
                    // 固定left和i, 调整right, 获取所有满足条件的right
                    for (int j = left + 1; j <= right; j++) {
                        result.add(nums[i]);
                        result.add(nums[left]);
                        result.add(nums[j]);
                    }
                    left++; // 获取完 固定i和left后的right元素后, 将left往后移
                } else {
                    right--;
                    // 如果right不满足条件,比结果target 那就往前挪动一位
                }
            }
        }
        return result;
    }
}
