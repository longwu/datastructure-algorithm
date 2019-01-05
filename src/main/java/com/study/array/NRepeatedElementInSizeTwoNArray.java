package com.study.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 重复 N 次的元素
 * <p>
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * <p>
 * 返回重复了 N 次的那个元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,1,2,5,3,2]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[5,1,5,2,5,3,5,4]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length 为偶数
 * <p>
 * https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/
 */
public class NRepeatedElementInSizeTwoNArray {
    public static void main(String[] args) {
        //int[] a = {1, 2, 3, 3};
        //int[] a = {2, 1, 2, 5, 3, 2};
        int[] a = {5, 1, 5, 2, 5, 3, 5, 4};
        //int[] a = {9, 5, 3, 3};
        //int[] a = {2, 1, 1, 1, 2, 5, 3, 2};
        //int[] a = {2, 2, 4, 4, 2, 5, 3, 2};
        //int[] a = {2, 4, 4, 4, 2, 5, 4, 2};

        System.out.println(repeatedNTimes(a));
        //System.out.println(repeatedNTimes2(a));
    }

    /**
     * 先将数组中所有的元素以及对应的个数都存到hashmap里面，然后便利hashmap，找到出现个数为数组长度/2的元素
     * <p>
     * 时间复杂度O（n）， 空间复杂度O（n）
     */
    private static int repeatedNTimes(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(A.length);

        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                if (map.get(A[i]) == A.length / 2) {
                    return A[i];
                } else {
                    map.put(A[i], map.get(A[i]) + 1);
                }
            } else {
                map.put(A[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == A.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 将数组中的元素进行从小到大排序,时间复杂度为O(nlogn)
     *
     * 由于出现N次的元素长度为数组的一半,所以排序后肯定会在中间+1或中间-1的位置出现
     *
     * 总体时间复杂度 O(nlogn)
     */
    private static int repeatedNTimes2(int[] A) {
        //将数组里的元素从小到大排序 O(nlogn)
        Arrays.sort(A);

        //因为出现N次的元素出现的次数时数组长度的一半，所以排序后，这个元素在中间+1或中间-1的位置一定会出现
        if (A[A.length / 2 + 1] == A[A.length / 2]) {
            return A[A.length / 2 + 1];
        } else {
            return A[A.length / 2 - 1];
        }
    }
}
