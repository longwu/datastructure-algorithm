package com.study.number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素, 也叫众数
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element
 */
public class MajorityElement {

    /**
         解法: 1. 暴力解法, 循环两次, 第一次循环每个元素, 第二次循环统计当前元素在整个数组中出现的个数,并判断是否大于数组长度的1/2,如果大于就返回该元素.   时间复杂度为 O(n2)
         2. 使用hashmap存储每个元素的数量, 由于hashmap读写的时间复杂度为O(1), 主要时间花费在一层循环上. 时间复杂度为O(n)
         3. 对数组进行排序, 排完序后, 如果题目说一定存在某个元素个数大于长度的一半,那么这个元素肯定会在数组的中间位置. 时间复杂度为O(nlogn)
         4. 利用递归 + 分治的方式,将数组分成左右两个数组,分别从两个数组中获取众数, 如果两边众数相等,则直接返回该众数. 如果众数不相等, 则计算两个众数的个数, 取多的那个.  时间复杂度 O(nlogn)
         5. Boyer-Moore 投票算法
             原理: 利用众数比非众数多的优势. 众数肯定比非众数多, 所以众数和非众数抵消后,剩下的必然是众数
             只需要对原数组进行两趟扫描，并且简单易实现。第一趟扫描我们得到一个候选节点candidate，第二趟扫描我们判断candidate出现的次数是否大于⌊ n/2 ⌋。
             第一趟扫描中，我们需要记录2个值： candidate，初值可以为任何数. count，初值为0
             之后，对于数组中每一个元素，首先判断count是否为0，若为0，则把candidate设置为当前元素。之后判断candidate是否与当前元素相等，若相等则count+=1，否则count-=1。
             时间复杂度为O(n)，空间复杂度为O(1).
     * @param args
     */
    public static void main(String[] args) {
        //int[] nums = {3, 2, 3};
        //int[] nums = {1};
        //int[] nums = {8, 8, 7, 7, 7};
        int[] nums = {8, 1, 2, 7, 7};
        //System.out.println(majorityElementByDoubleLoop(nums));
        //System.out.println(majorityElementByHashMap(nums));
        //System.out.println(majorityElementBySort(nums));
        //System.out.println(majorityElementByRecursion(nums));
        System.out.println(majorityElementByBoyerMoore(nums)); //投票算法
    }

    /**
     * 暴力解法, 循环两次, 第一次循环每个元素, 第二次循环统计当前元素在整个数组中出现的个数,并判断是否大于数组长度的1/2,如果大于就返回该元素.
     * <p>
     * 时间复杂度为 O(n2)
     *
     * @param nums
     * @return
     */
    private static int majorityElementByDoubleLoop(int[] nums) {
        int count = 1;

        // 边界处理
        if (nums.length == 1) {
            return nums[0];
        }

        // 循环每一个元素
        for (int i = 0; i < nums.length; i++) {
            // 将当前元素 与其他元素做对比, 如果相同次数就+1, 一旦达到数组长度1/2就返回该元素
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    count++;
                    if (count > nums.length / 2)
                        return nums[i];
                }
            }
            // 循环到下一个元素后,计数器需要还原
            count = 1;
        }
        return -1;
    }

    /**
     * 使用hashmap存储每个元素的数量, 由于hashmap读写的时间复杂度为O(1), 主要时间花费在一层循环上,
     * <p>
     * 时间复杂度为O(n)
     *
     * @param nums
     * @return
     */
    private static int majorityElementByHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);

            // 放完元素后 计算一下该元素个数是否大于数组长度的一半
            Integer count = map.get(nums[i]);
            if (count != null && count > nums.length / 2) {
                return nums[i];
            }
        }

        return -1;
    }

    /**
     * 对数组进行排序, 排完序后, 如果题目说一定存在某个元素个数大于长度的一半,那么这个元素肯定会在数组的中间位置.
     *
     * 时间复杂度为O(nlogn)
     *
     * @param nums
     * @return
     */
    private static int majorityElementBySort(int[] nums) {

        // 对数组进行排序, 时间复杂度为O(nlogn)
        Arrays.sort(nums);

        // 如果题目说肯定有元素超过数组长度的一半
        return nums[nums.length / 2]; //那排序后 这个元素肯定会处在1/2的位置
    }


    /**
     * 对数组进行排序, 排完序后, 记录每个元素的个数.
     * 对于排序后的元素, 可以直接进行一次遍历,然后对每个元素进行count[num]++,以及判断元素个数
     * <p>
     * 由于排序,所以最快的时间复杂度也要O(nlogn)
     *
     * @param nums
     * @return
     */
    private static int majorityElementBySort2(int[] nums) {

        // 边界处理
        if (nums.length == 1)
            return nums[0];

        // 对数组进行排序, 时间复杂度为O(nlogn)
        Arrays.sort(nums);

        int count = 1;
        int lastNum = nums[0];

        // 循环部分时间复杂度为O(n)
        // 从第2个元素开始循环
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素和上一个元素相等, 那么出现次数+1
            if (nums[i] == lastNum)
                count++;
            else //否则次数恢复为1
                count = 1;

            // 修改上一个元素
            lastNum = nums[i];

            // 如果该元素出现次数大于数组的一半,直接返回
            if (count > nums.length / 2)
                return nums[i];
        }

        return -1;
    }


    private static int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 利用递归 + 分治的方式,将数组分成左右两个数组,分别从两个数组中获取众数, 如果两边众数相等,则直接返回该众数.
     *
     * 如果众数不相等, 则计算两个众数的个数, 取多的那个.
     *
     * 时间复杂度 O(nlogn)
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private static int majorityElementRecursion(int[] nums, int low, int high) {
        // 只有一个元素的情况
        if (low == high) {
            // 递归终止条件
            System.out.println(String.format("从外往内递归终止: high = %d, low = %d, result = %d", high, low, nums[low]));
            return nums[low];
        }

        // 将数组分成左右两边两部分， 分别进行递归
        int mid = (high - low) / 2 + low;
        System.out.println(String.format("从外往内递归: high = %d, low = %d, mid = %d", high, low, mid));
        int left = majorityElementRecursion(nums, low, mid);
        int right = majorityElementRecursion(nums, mid + 1, high);

        System.out.println(String.format("从内往外递归: left = %d, right = %d",left, right));

        // 如果左右两边的众数一样，那该众数即为数组的众数
        if (left == right) {
            return left;
        }

        // 如果左右众数不一样， 那么分别计算两边众数的个数 ，多的为整个数组的众数
        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);

        return leftCount > rightCount ? left : right;
    }

    public static int majorityElementByRecursion(int[] nums) {
        return majorityElementRecursion(nums, 0, nums.length - 1);
    }

    /**
     * Boyer-Moore 投票算法
     *
     * 原理: 众数肯定比非众数多, 所以众数和非众数抵消后,剩下的必然是众数
     *
     * 该算法时间复杂度为O(n)，空间复杂度为O(1)，只需要对原数组进行两趟扫描，并且简单易实现。第一趟扫描我们得到一个候选节点candidate，第二趟扫描我们判断candidate出现的次数是否大于⌊ n/2 ⌋。
     *
     * 第一趟扫描中，我们需要记录2个值：
     *
     * candidate，初值可以为任何数
     * count，初值为0
     * 之后，对于数组中每一个元素，首先判断count是否为0，若为0，则把candidate设置为当前元素。之后判断candidate是否与当前元素相等，若相等则count+=1，否则count-=1。
     * 原文链接：https://blog.csdn.net/kimixuchen/article/details/52787307
     *
     * @param nums
     * @return
     */
    public static int majorityElementByBoyerMoore(int[] nums) {
        int count = 0;
        // 众数
        Integer candidate = null;

        for (int num : nums) {
            // 如果count变回了0, 相当于众数和非众数互相抵消, 则后面的候选数(众数)需要重新选取
            if (count == 0) {
                candidate = num; //只有count为0的时候 才重新选取候选数(众数)
            }
            // 如果新的数字为众数, 则count+1, 否则count-1
            count += (num == candidate) ? -1 : 1;
        }

        return candidate;
    }
}
