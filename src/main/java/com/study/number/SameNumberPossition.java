package com.study.number;

/**
 * 求数组中连续数第一次出现的位置
 */
public class SameNumberPossition {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(position(arr));
    }

    /**
     * 需要判断上一个索引位置和当前位置的元素是否相等,如果相等说明该元素连续出现了,返回该索引位置
     * @param arr
     * @return
     */
    private static int position(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }
}
