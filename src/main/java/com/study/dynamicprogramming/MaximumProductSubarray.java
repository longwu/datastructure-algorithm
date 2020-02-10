package com.study.dynamicprogramming;

/**
 * 乘积最大子序列
 * <p>
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaximumProductSubarray {

    public static void main(String[] args) {
        //int[] nums = {2, 3, -2, 4};
        //int[] nums = {-2, 0, -1};
        //int[] nums = {0, 2};
        int[] nums = {2, -5, -2, -3};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct2(nums));
    }

    /**
     * 使用穷举法(暴力法), 获取所有元素的值 以及 所有连续元素的乘积, 求其中的最大值
     * <p>
     * 时间复杂度O(m*n) 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    private static int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;

        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            // 最大乘积可以是单个元素
            int result = nums[i];
//            if (result > max)
//                max = result;
            max = Math.max(result, max);

            // 最大乘积可以是多个元素乘积
            // 每个元素为了确保不重复,只能乘以自己后面的元素
            // 乘以自己前面的元素结果跟前面的元素乘以自己是一样的,所以重复了
            int j = i + 1;
            while (j < nums.length) {
                result *= nums[j];
//                if (result > max)
//                    max = result;
                max = Math.max(result, max);
                j++;
            }
        }
        return max;
    }

    /**
     * 标签：动态规划
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)
     * <p>
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
     *
     * @param nums
     * @return
     */
    private static int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}
