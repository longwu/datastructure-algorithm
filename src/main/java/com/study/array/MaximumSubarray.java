package com.study.array;

/**
 * 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1};
        //int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //int result = maxSubArray(nums);
        int result = maxSubArray_greedy(nums);
        //int result = maxSubArray2(nums);
        System.out.println(result);
    }

    /**
     * 动态规划
     * <p>
     * 首先对数组进行遍历, 当前最大连续子序列和为currentSum,结果为max;
     * 如果 currentSum>0, 说明currentSum + 下一个数 是有增益效果的.
     * 如果 currentSum<0, 说明currentSum + 下一个数 没有增益效果. 需要舍弃, 使用下一个数进行替换
     * 比较 currentSum和max值, 取其中最大值作为max
     *
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        int max = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // currentSum为正数时候, 有增益效果, 加下一个元素的和 比使用下一个元素更大
            if (currentSum > 0) {
                currentSum += nums[i];
            } else {
                // 为负数时候, 没有增益效果, +下一个元素 小于直接使用下一个元素,所以使用下一个元素替换 重新开始计算currentSum
                currentSum = nums[i];
            }
            // 取 当前和 最大值 中最大的一个
            max = Math.max(max, currentSum);
        }
        return max;
    }

    /**
     * 使用贪心算法
     *
     * 遍历每个元素,将当前所有数之和 与 当前元素做比较, 取大的 作为当前所有数之和
     * 同时保持更新最大值, 将当前所有数之和 跟 最大值做比较, 取最大的 更新为 最大值
     *
     * 因为只需要一次循环遍历,时间复杂度O(n), 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    private static int maxSubArray_greedy(int[] nums) {
        int max = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 贪心 当前所有数之和 把每个数都加上
            currentSum += nums[i];
            // 如果当前数 比当前所有数之和还大, 那就使用当前数 作为当前所有数之和
            // 如果当前数 比当前所有数之和还小, 那就继续使用当前所有数之和
            // 比如 [-2 1]   -2 + 1 < 1, 那就直接从1开始计算 舍弃之前的-2
            currentSum = Math.max(nums[i], currentSum);
            // 取 当前所有数之和 与 最大值 中最大的一个
            max = Math.max(max, currentSum);
        }
        return max;
    }

    /**
     * 暴力解法, 计算出每个元素的连续最大和, 从中取最大的那个
     * 时间复杂度 O(n*m)
     *
     * @param nums
     * @return
     */
    private static int maxSubArray2(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max = nums[0];

        // 遍历每个元素, 得出每个元素能够得到的最大值和, 从中取最大的
        for (int i = 0; i < nums.length; i++) {
            int curSum = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                // 正数有增益效果, 对之和有帮助
                if (curSum > 0)
                    curSum += nums[j];
                else// 负数没有增益效果, 需要舍去
                    curSum = nums[j];

                max = Math.max(curSum, max);
            }
        }
        return max;
    }
}
