package com.study.array;

/**
 * 有一个 n 个正整数的数组 a[0, n-1]作为输入，同时生成一个
 * 大小与 a 相同的数组array，然后依次处理 a 中每个元素：如果当前的 a[i]是奇数则直接添加
 * 到 array 中最后一个元素后面；如果是偶数，则从 array 中最后一个元素开始，向前依次删除所有的奇数。
 */
public class ArrayOperation {

    public static void main(String[] args) {

        int[] arr = {2, 3, 5, 8};
        int[] newArr = work(arr);

        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] != 0)
                System.out.printf("%d ", newArr[i]);
        }
    }


    private static int[] work(int[] a) {
        int p = 0, n = a.length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) //如果是偶数
                while (p > 0 && array[p - 1] % 2 != 0)
                    array[p--] = 0; //删除前面的奇数
            array[p++] = a[i];
        }
        return array;
    }
}
