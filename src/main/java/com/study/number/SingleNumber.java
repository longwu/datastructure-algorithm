package com.study.number;

/**
 * 只出现一次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * https://leetcode-cn.com/problems/single-number/
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1, 4, 3};

        System.out.println(3 ^ 3); //相同的数异或为0
        System.out.println(0 ^ 3); //0和一个数异或得到数本身

        System.out.println(singleNumber((nums)));
    }

    /**
     * 根据二进制异或的原理, 每两个相同元素异或之后会等于0, 为此异或所有元素之后单个元素的值就出来了
     * <p>
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * <p>
     * 任何数于0异或为任何数 0 ^ n => n
     * <p>
     * 相同的数异或为0: n ^ n => 0
     * <p>
     * var a = [2,3,2,4,4]
     * <p>
     * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i]; //
        }
        return result;
    }
}
