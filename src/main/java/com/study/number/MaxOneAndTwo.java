package com.study.number;

/**
 * 找出一个数组里面第一大和第二大的数
 */
public class MaxOneAndTwo {
    public static void main(String[] args) {
        int[] a = {12, 23, 9, 24, 15, 3, 18};

        //int[] result = findMaxOneAndTwo(a);
        int[] result = findMax1AndMax2(a);

        System.out.printf("max1:%d, max2:%d", result[0], result[1]);
    }

    /**
     * 找出数组中最大和第二大的数, O1的时间复杂度
     *
     * @param arr
     * @return
     */
    private static int[] findMaxOneAndTwo(int[] arr) {
        // 定义最大数和第二大数
        int max1 = arr[0];
        int max2 = arr[0];
        // 遍历数组, 将每个元素与最大数进行比较,如果比最大数大,先将最大数赋给第二大,然后再其赋给最大数.
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] > max1) {
                max2 = max1;
                max1 = arr[i + 1];
            }
        }
        // 循环完成后 得到最大值和第二大值
        return new int[]{max1, max2};
    }


    private static int[] findMax1AndMax2(int[] arr){
        // 假设第一个元素为最大和第二大
        int max1 = arr[0];
        int max2 = max1;
        for(int i =0; i < arr.length -1; i++){
            // 从第二个元素开始判断
            if(arr[i+1] > max1){
                // 如果后一个元素比最大的还大,那就将最大的改成第二大,后一个元素改为最大
                max2 =  max1;
                max1 = arr[i+1];
            }
        }
        return new int[]{max1,max2};
    }
}
