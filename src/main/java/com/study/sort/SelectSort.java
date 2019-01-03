package com.study.sort;

/**
 * 选择排序
 * <p>
 * 每次遍历中从所需要排序的元素中选出最小的,放在已排序好的元素最后,直到全部排序结束
 *
 * <p>
 * https://www.cnblogs.com/shen-hua/p/5424059.html
 * <p>
 * 算法执行比较次数: sum = (n-1) + (n-2) + .... +1 = n(n-1)/2
 * <p>
 * 时间复杂度O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] a = {12, 23, 9, 24, 15, 3, 18};

        selectSort(a);
        print(a);

        System.out.println();

        selectSortDesc(a);
        print(a);
    }

    /**
     * 选择排序, 从每次遍历未排序的元素中找出值最小的元素索引
     *
     */
    private static void selectSort(int[] a) {
        //由于最后一个元素无需再遍历了,只要前面的所有元素都是按顺序从小到大排列,那么最后一个就是最大值, 所以只要判断i < a.length-1即可
        for (int i = 0; i < a.length - 1; i++) {
            //假设最小值索引为min, min初始值为k
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                //如果后面元素的值小于第min个元素,则把min修改为后面元素的索引,循环结束后得出最小的min
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            //将最小的索引为m的元素放到前面k位置上
            if (i != min) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    /**
     * 从大到小排序
     *
     */
    private static void selectSortDesc(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int max = i;
            //将后面的元素与前面元素做比较,如果后面的大,则把max索引改为最大的元素索引
            for (int j = i + 1; j < a.length; j++) {
                if (a[max] < a[j]) {
                    max = j;
                }
            }
            //将最前面的未排序的元素与后面最大的元素做替换
            if (i != max) {
                int tmp = a[max];
                a[max] = a[i];
                a[i] = tmp;
            }
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }
        }
    }
}
