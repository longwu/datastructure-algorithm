package com.study.number;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * https://leetcode-cn.com/problems/two-sum/
 */
public class TwoSum {

    private final static int SIZE = 100 * 1000;

    public static void main(String[] args) {

        int target = 9;
        int[] nums = {2, 7, 11, 15};

//        int[] nums = new int[SIZE];
//        for (int i = 0; i < SIZE; i++) {
//            if (i == SIZE - 1) {
//                nums[i] = 7;
//            } else if (i == SIZE - 2) {
//                nums[i] = 2;
//            } else {
//                nums[i] = 1;
//            }
//        }

        long start = System.currentTimeMillis();

        //int[] result = twoSum(nums, target);//1600ms
        //int[] result = twoSum2(nums, target); //20ms
        int[] result = twoSum3(nums, target); //15ms

        long end = System.currentTimeMillis();

        if (result.length == 2) {
            System.out.printf("%d,%d\r\n", nums[result[0]], nums[result[1]]);
        } else {
            System.out.println("no solution found");
        }

        System.out.printf("time cost: %dms", end - start);

    }

    /**
     * 将数组存到hashmap中, 利用hashmap查询速度的O(1), 将整个算法的时间复杂度提高到O(n)
     * <p>
     * 在将数组插入hashmap的同时,进行查找目标元素
     * <p>
     * 因此只进行了一次O(n)的操作
     * <p>
     * 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(SIZE);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; //将目标元素减去当前元素求得差值元素
            // 查看hashmap里面是否存在这个差值元素,
            //if (map.containsKey(complement) && map.get(complement) != i) { //并且这个差值元素不能是nums[i]本身, 实际来说map是不会存在nums[i]的, 因为往map里面添加nums[i]是在最后面
            // map是否包含7这个值, map.get(complement)就是7的索引
            if (map.containsKey(complement)){  //所以只判断是否包含该元素和上面代码是一样的
                return new int[]{i, map.get(complement)};
            }
            // 每次都将差值和索引存入, 以后如果数组存在差值这个元素, 那就直接取出value索引就找到了
            map.put(nums[i], i);// map 可以为值, value为索引
        }
        return new int[0];
    }

    /**
     * 将数组存到hashmap中, 利用hashmap查询速度的O(1), 将整个算法的时间复杂度提高到O(n)
     * <p>
     * 进行了两次O(n)的操作
     * <p>
     * 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(SIZE);

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; //将目标元素减去当前元素求得差值元素
            if (map.containsKey(complement) && map.get(complement) != i) {//由于nums[i]已经存在map里面,所以这里需要判断一下差值元素不能是由于nums[i]本身
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }

    /**
     * 穷举法, 使用两层循环 将每个元素之间两两相加找出目标元素
     * <p>
     * 时间复杂度O(n2)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[0];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
