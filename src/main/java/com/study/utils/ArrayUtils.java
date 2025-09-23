package com.study.utils;

import java.util.List;

public final class ArrayUtils {

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println();
    }

    public static void print(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> currentRows = list.get(i);
            for (int j = 0; j < currentRows.size(); j++) {
                System.out.print(currentRows.get(j) + ",");
            }
        }
        System.out.println();
    }

    /**
     * 打印数组中指定的一段
     */
    public static void printARangeForArray(int[] arr, int low, int high) {
        while (low <= high) {
            System.out.print(arr[low] + ",");
            low++;
        }
        System.out.println();
    }
}
