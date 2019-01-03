package com.study.sort;

/**
 * 冒泡排序
 * <p>
 * 冒泡排序(从小到大排列),将数组中的每一个元素都分别于后面的所有元素进行比较,将小的往前排
 * <p>
 * 冒泡排序有两层循环,第一层循环是遍历数组中的每一个元素,
 * <p>
 * 第二层循环是将数组中第一层循环的这个元素与后面所有的元素进行大小替换,这层循环n-1次结束后,最小的元素被排到了第一位
 * <p>
 * 之后第一层循环到下一个元素,第二层循环再次循环n-2次,这次第二小的元素被找到,排到了第二的位置,
 * <p>
 * 此后,第3,4,5小的元素被找出并被排到对应的位置,直到最后一个元素(最大的)被找出排好,算法结束
 * <p>
 * 时间复杂度 O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {12, 23, 9, 24, 15, 3, 18};

        bubbleSort(a);

        print(a);

        bubbleSortDesc(a);

        System.out.println();
        print(a);
    }

    /**
     * 从小到大排列,外循环每执行到下一个元素,内循环都能找到一个未排序的最小元素并排好顺序
     * <p>
     * 时间复杂度O(n^2)
     */
    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        //遍历数组中每一个元素, 最后一个元素可以不遍历,前面n-1个元素排序好,最后一个自然是最大
        for (int f = 0; f < len - 1; f++) {
            //把后面的所有元素与该元素做比较,
            for (int b = f + 1; b < len; b++) {
                //如果后面的小那就把后面的元素和该元素做替换,最终该元素所在的位置为最小元素
                if (arr[f] > arr[b]) {
                    int tmp = arr[f];
                    arr[f] = arr[b];
                    arr[b] = tmp;
                }
            }
        }
    }

    /**
     * 从大到小排列, 外循环每执行到下一个元素,内循环都能找到一个未排序的最大元素并排好顺序
     * <p>
     * 时间复杂度O(n^2)
     */
    private static void bubbleSortDesc(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] < arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
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