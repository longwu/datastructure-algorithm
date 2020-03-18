package com.study.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 链接：https://leetcode-cn.com/problems/single-number
 */
public class SingleNumber {

    public static void main(String[] args) {
        //int[] arr = {2, 2, 1};
        int[] arr = {4, 1, 2, 1, 2};
        //System.out.println(singleNumberHashSet(arr));
        //System.out.println(singleNumberHashMap(arr));
        //System.out.println(singleNumberBySum(arr));
        System.out.println(singleNumberByBit(arr));
    }

    /**
     * 使用hashset来存每个元素, 当元素出现2次的时候,移除该元素,所以只会保留出现一次的元素.
     * 最终hashset里面只会存1个元素, 即为结果
     * <p>
     * 时间复杂度 O(n), 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    private static int singleNumberHashSet(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            // 如果该元素已经存在, 就移除
            if (set.contains(nums[i]))
                set.remove(nums[i]);
            else // 不存在 就添加
                set.add(nums[i]);
        }

        // 最终set里面只有一个元素即为结果
        for (Integer i : set) {
            return i;
        }

        return -1;
    }

    /**
     * 使用hashmap来存每个数字的个数, 最后遍历hashmap,找出个数为1的那个数字
     * <p>
     * 时间复杂度 O(n), 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    private static int singleNumberHashMap(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            // 如果该元素已经存在, 就移除
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else // 不存在 就添加
                map.put(nums[i], 1);
        }

        // 最终set里面只有一个元素即为结果
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            if (kv.getValue() == 1)
                return kv.getKey();
        }

        return -1;
    }


    /**
     * 通过数学公式  2倍的不重复的元素之和 - 数组元素之和 = 单个元素
     * 2(a+b+c) - (2a + 2b + c)  = c
     * <p>
     * 遍历数组, 计算数组所有元素值
     * 使用set来存储不重复的元素, 存储的同时 计算不重复元素值
     * 使用不重复元素之和的2倍 - 数组元素值之和 = 单个元素
     * <p>
     * 时间复杂度 O(n), 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    private static int singleNumberBySum(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        int twoSum = 0;
        int arrSum = 0;

        for (int n : nums) {
            arrSum += n; // 计算数组元素之和
            if (!set.contains(n)) {
                set.add(n);
                twoSum += n; // 计算不重复元素之和
            }
        }

        return twoSum * 2 - arrSum;
    }

    /**
     * 使用位运算的 异或
     * 相同的两个数进行异或^结果为0, 0和任何数异或为任何数本身
     * <p>
     * 如果我们对 0 和二进制位做 XOR 运算，得到的仍然是这个二进制位
     * a ^ 0 = a
     * 如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
     * a ^ a = 0
     * XOR 满足交换律和结合律
     * a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
     * <p>
     * 链接：https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode/
     * 来源：力扣（LeetCode）
     *
     * @return
     */
    private static int singleNumberByBit(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n; // 相同数异或之后变成0, 0和某个数异或得到该数本身
        }
        return result;
    }
}
