package org.me.study.selectsort;

import org.me.study.tools.Utils;

/**
 * 选择排序,
 * 每次遍历中从所需要排序的元素中选出最小的,放在已排序好的元素后面
 * <p>
 * https://www.cnblogs.com/shen-hua/p/5424059.html
 *
 * 算法执行比较次数: sum = (n-1) + (n-2) + .... +1 = n(n-1)/2
 *
 * 时间复杂度 O(n2)
 */
public class Client {

    public static void main(String[] args) {

        int[] a = {12, 23, 9, 24, 15, 3, 18};

        selectSort(a);

        selectSortDesc(a);

        Utils.print(a);
    }

    /**
     * 选择排序, 从每次遍历未排序的元素中找出值最小的元素索引
     *
     * @param a
     */
    public static void selectSort(int[] a) {
        int n = a.length;
        for (int k = 0; k < n - 1; k++) {
            //假设最小值索引为min, min初始值为k
            int min = k;
            for (int i = k + 1; i < n; i++) {
                //如果后面元素的值小于第min个元素,则把min修改为后面元素的索引,循环结束后得出最小的min
                if (a[i] < a[min]) {
                    min = i;
                }
            }
            //将最小的索引为m的元素放到前面k位置上
            if (k != min) {
                int temp = a[k];
                a[k] = a[min];
                a[min] = temp;
            }
        }
    }

    /**
     * 选择排序, 降序
     * @param a
     */
    public static void selectSortDesc(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int max = i;
            //将后面的元素与前面元素做比较,如果后面的大,则把max索引改为最大的元素索引
            for (int j = i + 1; j < n; j++) {
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
}
