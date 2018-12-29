package com.study.sort;

import com.study.tools.Utils;

/**
 * 冒泡排序,从小到大排列
 *
 * 时间复杂度 O(n2)
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {12, 23, 9, 24, 15, 3, 18};

        bubbleSort(a);

        Utils.print(a);

        bubbleSortDesc(a);

        System.out.println();
        Utils.print(a);
    }

    /**
     * 冒泡排序,从小到大排列
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        //遍历数组中每一个元素, 最后一个元素可以不遍历,前面n-1个元素排序好,最后一个自然是最大
        for (int i = 0; i < len - 1; i++) {
            //把后面的所有元素与该元素做比较,
            for (int j = i + 1; j < len; j++) {
                //如果后面的小那就把后面的元素和该元素做替换,最终该元素所在的位置为最小元素
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序,从大到小排列
     *
     * @param arr
     */
    private static void bubbleSortDesc(int[] arr) {
          int len = arr.length;
          for (int i = 0; i < len -1 ; i++){
              for (int j = i+1; j < len; j++){
                  if(arr[i] < arr[j]){
                      int tmp = arr[j];
                      arr[j] = arr[i];
                      arr[i] = tmp;
                  }
              }
          }
    }
}