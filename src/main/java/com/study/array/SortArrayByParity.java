package com.study.array;

import java.util.ArrayList;

/**
 * 按奇偶排序数组
 * <p>
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 * <p>
 * 示例：
 * <p>
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 * <p>
 * https://leetcode-cn.com/problems/sort-array-by-parity/
 */
public class SortArrayByParity {

    public static void main(String[] args) {
        //int[] a = {3, 1, 2, 4, 6, 7, 8, 9};
        //int[] a = {3, 1};
        int[] a = {2, 4};

        //int[] result = sortArrayByParity(a);
        //int[] result = sortArrayByParity2(a);
        int[] result = sortArrayByParity3(a);

        for (int i = 0; i < result.length; i++) {
            System.out.printf("%d ", result[i]);
        }
    }

    /**
     * 新建一个集合,将数组中的偶数往集合的最前面插入,而奇数则往末尾添加
     * <p>
     * 最后将集合转换为数组
     * <p>
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    private static int[] sortArrayByParity(int[] A) {
        ArrayList<Integer> list = new ArrayList<Integer>(A.length);
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                list.add(0, A[i]);//如果是偶数,插入到最前面
            } else {
                list.add(A[i]);//如果是奇数添加到末尾
            }
        }
        //将集合转成数组
        int[] result = new int[A.length];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 将原有输入数组里的前面的奇数与后面的偶数对调
     * <p>
     * 时间复杂度O(n), 空间复杂度O(1)
     */
    private static int[] sortArrayByParity2(int[] A) {
        int front = 0, back = A.length - 1;
        while (front < back) {
            //找到前面的奇数下标
            while (A[front] % 2 == 0 && front < A.length -1) {
                front++;
            }
            //找到后面的偶数下标
            while (A[back] % 2 != 0 && 0 < back) {
                back--;
            }

            //如果前面的奇数下标大于后面偶数下标,说明已经循环一遍,退出循环
            if (front >= back) {
                return A;
            }

            //将前面的奇数与后面的偶数替换
            int tmp = A[back];
            A[back] = A[front];
            A[front] = tmp;

            //替换完后,将下标移到一位
            back--;
            front++;
        }

        return A;
    }

    /**
     * 将原有输入数组里的前面的奇数与后面的偶数对调
     * <p>
     * 时间复杂度O(n), 空间复杂度O(1)
     */
    private static int[] sortArrayByParity3(int[] A) {
        int front = 0, back = A.length - 1;
        while (front < back) {
            //找到前面的奇数下标
            while (A[front] % 2 == 0 && front < back) {
                front++;
            }
            //找到后面的偶数下标
            while (A[back] % 2 != 0 && front < back) {
                back--;
            }

            //如果前面的奇数下标大于后面偶数下标,说明已经循环一遍,退出循环
            if (front >= back) {
                return A;
            }

            //将前面的奇数与后面的偶数替换
            int tmp = A[back];
            A[back] = A[front];
            A[front] = tmp;

            //替换完后,将下标移到一位
            back--;
            front++;
        }

        return A;
    }
}
