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

        //selectSort(a);
        selectSort2(a);
        print(a);

        System.out.println();

        //selectSortDesc(a);
        selectSortDesc2(a);
        print(a);
    }

    /**
     * 选择排序, 从每次遍历未排序的元素中找出值最小的元素索引
     * <p>
     * 每一次循环能找出一个当前最小的索引,然后将最小索引上的元素放在当前遍历的索引上
     * <p>
     * 跟冒泡排序类似,只是它不会做元素的两两替换,而是找最大或最小索引,找到后再替换
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
            //将前面未排序的元素与后面最小元素做替换
            if (i != min) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    private static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 每一轮循环找出一个最小值索引
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // 最小索引不再是当前元素的索引, 那就需要将最小元素和当前元素位置替换一下
            if (min != i) {
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    /**
     * 从大到小排序
     */
    private static void selectSortDesc(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int max = i;
            //将后面的元素与前面元素做比较,如果后面的大,则把max索引改为最大的元素索引
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[max]) {
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


    private static void selectSortDesc2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// 这里用arr.length 或者arr.length -1都行, 因为排完前面的, 最后一个肯定是最小的
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (i != maxIndex) {
                int tmp = arr[maxIndex];
                arr[maxIndex] = arr[i];
                arr[i] = tmp;
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
