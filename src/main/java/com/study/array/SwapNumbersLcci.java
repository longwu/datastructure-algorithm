package com.study.array;

/**
 * 交换数字
 * <p>
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 * <p>
 * 示例：
 * <p>
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * 提示：
 * <p>
 * numbers.length == 2
 * <p>
 * https://leetcode-cn.com/problems/swap-numbers-lcci/
 */
public class SwapNumbersLcci {

    public static void main(String[] args) {
        //int[] arr = {1, 2};
        int[] arr = {5, 3};
        //int[] result = swapNumbers(arr);
        int[] result = swapNumbers2(arr);
        System.out.println(result[0] + " " + result[1]);
    }


    /**
     * 求和相减法
     * <p>
     * 由于数组中只有两个元素, 可以使用两数之和 减去其中一个数 得到另一个数来处理
     *
     * @param numbers
     * @return
     */
    private static int[] swapNumbers(int[] numbers) {

        if (numbers == null || numbers.length != 2) {
            return new int[]{};
        }

        numbers[0] = numbers[0] + numbers[1]; // 3 = 1 + 2
        numbers[1] = numbers[0] - numbers[1]; // 1 = 3 - 2  这个时候2变成了1
        numbers[0] = numbers[0] - numbers[1]; // 2 = 3 - 1  这个时候1变成了2

        return numbers;
    }

    /**
     * 使用位算法
     *
     * @param numbers
     * @return
     */
    private static int[] swapNumbers2(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1]; // 3 = 1 ^ 2
        numbers[1] = numbers[0] ^ numbers[1]; // 1 = 3 ^ 2
        numbers[0] = numbers[0] ^ numbers[1]; // 2 = 3 ^ 1

        return numbers;
    }
}
