package com.study.array;

/**
 * 求数组中连续数第一次出现的位置
 */
public class SameNumberPossition {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(position(arr));
    }

    private static int position(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }
}
