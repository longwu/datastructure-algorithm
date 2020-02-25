package com.study.sort;


/**
 * 归并排序, 节省内存空间 直接在原有的数组内部直接排序, 需要额外借助一个数组存放临时排序的元素
 *
 * 时间复杂度 O(NlogN)
 *
 * 搜先通过不停的左右二分，将数组分成了类似二叉树的结构，
 * 其递归的方式类似 二叉树的 后续遍历 左 右 根， 合并处理在根阶段
 * 使用数组5, 4, 7, 9, 3, 8, 2, 1进行举例
 * 根节点为完整数组， 一级子节点为 5479 和 3821  二级节点为 54，79 ，38，21 三级节点也就是叶子节点为 5，4，7，9， 3，8，2，1
 * 然后通过最底层左边5，4开始进行merge，得到4，5， 再右边7，9得到7，9， 再往上回溯 左边54和右边79合并得到5479，
 * 然后是根的右边最底层3和8合并得到38，21合并得到12， 往上回溯38和12合并得到1238，
 * 最后是 左5479节点和右1238合并得到根节点12345789
 * <p>
 * https://www.jianshu.com/p/33cffa1ce613
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 4, 7, 9, 3, 8, 2, 1};

//        mergeSort(arr);
//        print(arr);

        mergeSort_2(arr);
        print(arr);
    }

    private static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, tmp);
    }

    /**
     * 搜先通过不停的左右二分，将数组分成了类似二叉树的结构，
     * 其递归的方式类似 二叉树的 后续遍历 左 右 根， 合并处理在根阶段
     * 根节点为完整数组， 一级子节点为 5479 和 3821  二级节点为 54，79 ，38，21 三级节点也就是叶子节点为 5，4，7，9， 3，8，2，1
     * 然后通过最底层左边5，4开始进行merge，得到4，5， 再右边7，9得到7，9， 再往上回溯 左边54和右边79合并得到5479，
     * 然后是根的右边最底层3和8合并得到38，21合并得到12， 往上回溯38和12合并得到1238，
     * 最后是 左5479节点和右1238合并得到根节点12345789
     *
     * @param arr
     * @param left
     * @param right
     * @param tmp
     */
    private static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);

            // 将数组不断的分成左右两部分, 直到分成无数个单个元素数组后，进行merge排序

            // 左边分到不能再分 就进行回溯, 回溯的过程中再进行右边分
            sort(arr, left, mid, tmp); // 左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, tmp); // 右边归并排序, 使得右子序列有序

            String s1 = "";
            for (int i = left; i <= mid; i++)
                s1 += arr[i] + " ";
            String s2 = "";
            for (int j = mid + 1; j <= right; j++)
                s2 += arr[j] + " ";

            System.out.println(String.format("分成了数组%s和数组%s", s1, s2));
            // 每次左右两部分排序完后 再进行合并
            merge(arr, left, mid, right, tmp); //将两个有序子数组合并操作
            String s = "";
            for (int a = left; a <= right; a++)
                s += arr[a];
            System.out.println("合并后得到: " + s);
        }
    }

    /**
     * 将arr中的 left到mid， mid+1到right 两部分进行合并
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param tmp
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left; // 左边数组开始指针
        int j = mid + 1;// 右边数组开始指针
        int t = 0;  // 临时数组开始指针

        // 将左边和右边数组里的元素 从小到大排放入临时数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }

        // 上面循环执行完后, 左右数组里可能有一个数组还有元素没放入临时数组, 需要再次处理一下
        // 处理左边
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }

        // 处理右边
        while (j <= right) {
            tmp[t++] = arr[j++];
        }

        // 最后将临时数组中合并好的元素重新放回原来的数组
        t = 0;//每次合并排序的元素放入tmp中都是从0开始的,所以取出也从0开始
        while (left <= right) {
            arr[left++] = tmp[t++];//将tmp中的元素依次放回数组的left到right位置
        }
    }

    private static void print(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    private static void mergeSort_2(int[] arr) {
        int[] tmp = new int[arr.length];
        sort_2(arr, 0, arr.length - 1, tmp);
    }

    private static void sort_2(int[] arr, int left, int right, int[] tmp) {
        // 左右两个数组中的元素不能相同
        if (left >= right)
            return;

        int mid = ((right - left) >> 1) + left;
        // 分治递归, 将数组不断分成单个元素的左右数组
        sort_2(arr, left, mid, tmp);
        sort_2(arr, mid + 1, right, tmp);

        // 将左右数组进行排序合并, 合并后回溯到上层大数组再进行排序合并,最终完成整个数组排序
        merge_2(arr, left, mid, right, tmp);
    }

    // 将左右两块数组进行合并
    // 将arr中的left 到 mid, mid+1 到 right 两部分数组进行合并排序人放入tmp中
    // 最后再从tmp中取出放回arr中
    private static void merge_2(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        // 左边数组范围 left 到mid, 右边数组范围 mid+1到right
        // 将元素从小到大放入tmp中
        while (i <= mid && j <= right) {
            if (arr[i] >= arr[j]) {
                tmp[t++] = arr[j++];
            } else {
                tmp[t++] = arr[i++];
            }
        }

        // 将左右数组中没有取出的元素放入tmp中
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }

        while (j <= right) {
            tmp[t++] = arr[j++];
        }

        // 将tmp中的元素放回arr中
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }
}
