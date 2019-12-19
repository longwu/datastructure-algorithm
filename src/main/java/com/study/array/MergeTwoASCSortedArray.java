package com.study.array;

/**
 * 合并两个升序数组
 * <p>
 * 可以使用如下方法：
 * 1.新数组存两个数组参数的方法，两个数组不变
 */
public class MergeTwoASCSortedArray {
    public static void main(String[] args) {
        int[] num1 = {1, 2, 5, 7, 9, 10};
        int[] num2 = {1, 4, 6, 8};

//        int[] newNum = merge(num1, num2);
//        int[] newNum = merge2(num1, num2);
        int[] newNum = merge3(num1, num2);

        for (int i = 0; i < newNum.length; i++) {
            System.out.print(newNum[i] + " ");
        }
    }

    /**
     * 新建一个数组来存取两个数组的内容
     * 多次迭代完成
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int[] merge(int[] num1, int[] num2) {
        int a = 0, b = 0, c = 0;

        int[] newNum = new int[num1.length + num2.length];

        // 在数组num1和num2都不超出索引的情况下遍历，一旦num1或者num2 其中一个数组遍历完就退出循环，否则将出现索引越界的情况。
        // 假设 num2遍历完了， b的长度等于num2，这个时候需要退出循环，否则里面执行num2【b】的时候会报索引越界
        while (a < num1.length && b < num2.length) {
            // 将小的元素放到前面
            if (num1[a] < num2[b])
                newNum[c++] = num1[a++]; //先放元素 再各自索引+1
            else
                newNum[c++] = num2[b++];
        }

        // 元素补齐
        // 执行到这里 num1或num2可能会有元素没有加到newNum中，需要再补充。
        // 如果num1中还有元素未添加的 继续添加
        while (a < num1.length) {
            newNum[c++] = num1[a++];
        }

        // 如果num2中还有元素未添加的继续添加
        while (b < num2.length) {
            newNum[c++] = num2[b++];
        }
        return newNum;
    }

    /**
     * 新建一个数组来存取两个数组的内容
     * 多次迭代完成
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int[] merge2(int[] num1, int[] num2) {
        int i = 0, j = 0, k = 0;

        int[] newNum = new int[num1.length + num2.length];

        // 一旦有一个数组的元素全部取出，就退出循环，避免数组越界的情况发生
        while (i < num1.length && j < num2.length) {
            // 两个数组中的元素做比较, 将小的元素放前面
            if (num1[i] < num2[j])
                newNum[k++] = num1[i++];
            else
                newNum[k++] = num2[j++];
        }

        // 将剩下的某个未取出元素的数组 再取出元素
        // 通常只剩下一个数组，因为上面的循环执行完 肯定是另一个数组已取完
        while (i < num1.length) {
            newNum[k++] = num1[i++];
        }

        while (j < num2.length) {
            newNum[k++] = num2[j++];
        }

        return newNum;
    }

    /**
     * 新建一个数组来存取两个数组的内容
     * 1次迭代完成
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int[] merge3(int[] num1, int[] num2) {
        int i = 0, j = 0, k = 0;

        int[] newNum = new int[num1.length + num2.length];

        // 一次循环完成，需要处理数组越界的问题
        while (i < num1.length || j < num2.length) {

            // 如果数组num1越界，说明已取完，那就取另一个数组num2, 需要确保num2没越界
            if (i >= num1.length && j < num2.length) {
                newNum[k++] = num2[j++];
                continue;
            }

            // 如果数组num2越界，说明已取完，那就取另一个数组num1，需要确保num1没越界
            if (j >= num2.length && i < num1.length) {
                newNum[k++] = num1[i++];
                continue;
            }

            // 两个数组中的元素做比较, 将小的元素放前面
            if (num1[i] < num2[j])
                newNum[k++] = num1[i++];
            else
                newNum[k++] = num2[j++];

        }

        return newNum;
    }
}
