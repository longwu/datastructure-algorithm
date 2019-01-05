package com.study.array;

import java.util.Arrays;

/**
 * 按奇偶排序数组 II
 * <p>
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * <p>
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityTwo {
    public static void main(String[] args) {
        int[] a = {4, 7, 5, 2};
        //int[] a = {3, 4};

        //System.out.println(Arrays.toString(sortArrayByParityII(a)));
        //System.out.println(Arrays.toString(sortArrayByParityII2(a)));
        System.out.println(Arrays.toString(sortArrayByParityII3(a)));
    }

    /**
     * 创建一个与数组A长度相同的新数组b, 遍历数组A,将数组A里的所有偶数放在b的偶数位置,将所有奇数分别放在b的奇数位置.
     * <p>
     * 时间复杂度O(n), 但需要额外开辟一个数组的空间
     */
    private static int[] sortArrayByParityII(int[] A) {
        int x = 0; //偶数索引
        int y = 1; //技术索引
        int[] b = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                b[x] = A[i];
                x += 2;
            } else {
                b[y] = A[i];
                y += 2;
            }
        }
        return b;
    }

    /**
     * 将数组A偶数位置上的奇数与奇数位置上偶数进行互换
     * <p>
     * 时间复杂度O(n)
     */
    private static int[] sortArrayByParityII2(int[] A) {
        //奇数索引
        int j = 1;
        for (int i = 0; i < A.length - 1; i = i + 2) {
            // 如果A的偶数位置上不是偶数
            if ((A[i] & 1) != 0) {
                // 那么就从A的奇数位置上找偶数
                while ((A[j] & 1) != 0) {
                    j = j + 2;
                }
                // 一旦找到就将偶数位置上的奇数和奇数位置上的偶数进行交换
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        return A;
    }

    /**
     * 将数组A奇数位置上的偶数与偶数位置上奇数进行互换
     * <p>
     * 时间复杂度O(n)
     */
    private static int[] sortArrayByParityII3(int[] A) {
        // 偶数索引
        int i = 0;
        for (int j = 1; j < A.length; j += 2) {
            // 如果奇数位置上不是奇数
            if ((A[j] & 1) == 0) {
                // 从偶数位置上找奇数
                while ((A[i] & 1) == 0) {
                    // 如果该偶数位置上不是奇数,则找下一个偶数位置,直到找到奇数
                    i += 2;
                }
                //将奇数位置上的偶数与偶数位置上的奇数互换
                int tmp = A[j];
                A[j] = A[i];
                A[i] = tmp;
            }
        }
        return A;
    }
}
