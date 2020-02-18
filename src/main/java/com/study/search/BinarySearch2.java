package com.study.search;

/**
 * 二分查找4种变形题:
 *
 * 找到第一个相同的元素(存在重复的元素)
 * 找到最后一个相同的元素(存在重复的元素)
 * 找到第一个大于等于的元素(存在重复的元素)
 * 找到最后一个小于等于的元素(存在重复的元素)
 *
 */
public class BinarySearch2 {

    /**
     * ((right - left) >> 1) + left 等价于 (left + right) / 2
     *
     * @param args
     */
    public static void main(String[] args) {
        //int[] arr = {-1, 0, 1, 2, 4, 5};
        //int[] arr = {0};
        int[] arr = {};
        System.out.println(find(arr, 0));
        System.out.println(find(arr, -1));
        System.out.println(find(arr, 1));
        System.out.println(find(arr, 2));
        System.out.println(find(arr, 4));
        System.out.println(find(arr, 5));
        System.out.println("---------------查找第一个相同元素");
        arr = new int[]{-2, -1, 0, 2, 2, 3, 3, 5};
        //arr = new int[]{};
        System.out.println(findFirst(arr, -2));
        System.out.println(findFirst(arr, 0));
        System.out.println(findFirst(arr, 2));
        System.out.println(findFirst(arr, 3));
        System.out.println(findFirst(arr, 5));
        System.out.println("---------------查找最后一个相同元素");
        System.out.println(findLast(arr, -2));
        System.out.println(findLast(arr, 0));
        System.out.println(findLast(arr, 2));
        System.out.println(findLast(arr, 3));
        System.out.println(findLast(arr, 5));
        System.out.println("---------------查找第一个大于等于元素");
        arr = new int[]{-2, 0, 2, 2, 3, 3, 5};
        System.out.println(findFirstLargerEquals4(arr, -2)); //0
        System.out.println(findFirstLargerEquals4(arr, -1)); //1
        System.out.println(findFirstLargerEquals4(arr, 1)); //2
        System.out.println(findFirstLargerEquals4(arr, 5)); //6
        System.out.println(findFirstLargerEquals4(arr, 2)); //2
        System.out.println(findFirstLargerEquals4(arr, 7)); //-1 没找到
        System.out.println(findFirstLargerEquals4(arr, 0)); //1

        arr = new int[]{-2};
        System.out.println(findFirstLargerEquals4(arr, -3)); //0

        arr = new int[]{};
        System.out.println(findFirstLargerEquals4(arr, -3)); //-1

        System.out.println("---------------查找最后一个小于等于元素");
        arr = new int[]{-2, 0, 2, 2, 3, 3, 5};
        System.out.println(findLastSmallerEquals2(arr, -2)); //0
        System.out.println(findLastSmallerEquals2(arr, -1)); //0
        System.out.println(findLastSmallerEquals2(arr, 1)); //1
        System.out.println(findLastSmallerEquals2(arr, 5)); //6
        System.out.println(findLastSmallerEquals2(arr, 2)); //3
        System.out.println(findLastSmallerEquals2(arr, 7)); //6
        System.out.println(findLastSmallerEquals2(arr, 0)); //1

//        System.out.println(findLastLargerEquals(arr, 3));
//        System.out.println(findLastLargerEquals(arr, 4));
    }


    /**
     * 在一个有序数组中(没有重复的元素),找到目标元素所在的位置
     *
     * @param arr
     * @param target
     * @return
     */
    private static int find(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            //int mid = (right - left) >> 1 + left;// 由于+left的优先级高, 会先于前面的 >> 1执行, 这样查找某些元素的时候会出现死循环
            int mid = ((right - left) >> 1) + left; //  左边需要加括号
            //int mid2 = (left + right) / 2;
            //System.out.println(String.format("mid: %d, mid2: %d", mid, mid2));
            if (arr[mid] == target) {
                return mid;// 找到目标元素直接返回
                // 当前元素大于目标元素
            } else if (arr[mid] > target) {
                right = mid - 1; // 往前找
            } else {
                left = mid + 1; // 否则往后找
            }
        }
        // 找不到或者空数组返回-1
        return -1;
    }

    /**
     * 找到第一个相同的元素(存在重复的元素)
     *
     * 如果找到, 再往前找, 找到的条件是前一个小于目标元素, 那么当前这个就是目标元素. (往前找需要考虑边界值为0,已经找到第一个了还等于目标,那直接返回)
     *
     * @param array
     * @param target
     * @return
     */
    public static int findFirst(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (array[mid] > target) {
                right = mid - 1; //往前查找
            } else if (array[mid] < target) {
                left = mid + 1;  // 往后查找
            } else {
                // 如果找到相同的元素, 并且前面一个不是该元素, 说明找到了结果
                // 两种情况, 该元素已经是数组第一个元素了(前面没有了元素) 或者 前面的元素不是目标元素
                if (mid == 0 || array[mid - 1] != target) {
                    return mid;
                } else {
                    // 否则 继续往左查找
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    //3.变式：查找最后一个值等于给定值的元素（ 存在重复的数据）
    public int binarySearch5(int[] array, int target) {
        int left = 0, mid = 0, right = array.length - 1;

        while (left <= right) {
            mid = (right - left) >> 1 + left;

            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else { //相等的前提下
                if (mid == array.length - 1 || (array[mid + 1] != target)) {
                    return mid;
                } else {
                    left = mid + 1; //往后找
                }
            }
        }

        return -1;
    }

    /**
     * 找到最后一个相同的元素,
     *
     * 找到的基础上往后找, 如果后一个大于目标元素,则当前为最后一个相同元素. 边界值为当前元素为最后一个
     *
     * @return
     */
    private static int findLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (arr[mid] > target) {
                right = mid - 1; //往前找
            } else if (arr[mid] < target) {
                left = mid + 1; //往后找
            } else {
                // 如果下一个元素不是目标元素, 说明当前这个为目标最后一个元素
                // 两种情况, 该元素已经是数组最后一个(不用再找) 或者 下一个不是目标元素(该元素不是最后一个)
                if (mid == arr.length - 1 || arr[mid + 1] != target)
                    return mid;
                else
                    left = mid + 1; //继续往右找
            }
        }
        return -1;
    }

//    /**
//     * 查找第一个大于等于给定值的元素（ 存在重复的数据）
//     *
//     * @param array
//     * @param target
//     * @return
//     */
//    public static int binarySearch4(int[] array, int target) {
//        int left = 0, right = array.length - 1;
//
//        while (left <= right) {
//            int mid = ((right - left) >> 1) + left;
//
//            if (array[mid] >= target) {
//                if (mid == array.length - 1 || (array[mid + 1] != target)) {
//                    return mid;
//                } else {
//                    right = mid - 1;
//                }
//            } else {
//                left = mid + 1; //往后找
//            }
//        }
//
//        return -1;
//    }

    /**
     * 查找第一个大于等于给定值的元素（存在重复的数据）
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findFirstLargerEquals(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 小于目标, 往后找
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                // 大于或者等于目标, 需要判断是否已经找到, 如果没找到再往前找
                // 如果已经处于第0个元素 或者 前一个元素小于目标, 说明已经找到
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    // 否则 继续往前找, 将越来越接近第一个大于等于的元素
                    right = mid - 1;
                }
            }
        }

        // 找不到 返回-1
        return -1;
    }

    /**
     * 找到 第一个大于等于目标元素的位置
     * <p>
     * 思路, 先找大于等于的元素, 找到以后再里面判断是否是第一个大于等于的(因为大于等于的可能很多), 如果不是,再往前找第一个大于等于的
     * <p>
     * 第一个大于等于的标志为 上一个元素小于目标 (或者 当前位置为第0个, 后面的元素都大于等于目标 边界值)
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findFirstLargerEquals3(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 如果小于,完全不满足条件,往后找
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                // 大于或者等于的情况, 因为大于等于的目标元素非常多,所以需要判断是否为第一个
                // 如果当前找到的位置是第0个元素, 说明后面的元素都比目标元素大, 那第0个就是结果 (或者数组就一个元素)
                // 或者前一个元素小于目标, 那当前元素肯定是第一个大于等于的元素
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    // 如果没找到目标, 就往前找, 最终一定能找到第一个大于等于的
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int findFirstLargerEquals4(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 大于等于 往前找
            if (arr[mid] >= target) {
                // 大于或者等于的情况, 因为大于等于的目标元素非常多,所以需要判断是否为第一个
                // 需要考虑一些边界值, 如果已经找到了第一个元素 还比目标大, 那就直接返回这个元素
                // 或者前一个元素小于目标, 那当前元素肯定是第一个大于等于的元素
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    // 如果没找到目标, 就往前找, 最终一定能找到第一个大于等于的
                    right = mid - 1;
                }
            } else {
                // 如果小于,完全不满足条件,往后找
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素（ 存在重复的数据）
     * <p>
     * 思路: 如果大于就往前找, 如果小于等于 就往后找(因为小于等于目标元素的特别多), 直到找到最后一个小于等于目标元素的
     * <p>
     * https://blog.csdn.net/hyforthy/article/details/22614247
     *
     * @param array
     * @param target
     * @return
     */
    private static int findLastSmallerEquals(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 如果大于就往前找
            if (array[mid] > target) {
                right = mid - 1;
            } else {
                // 如果小于等于 就往后找, 直到找到目标元素
                // 下一个元素大于目标, 那当前元素为最后一个小于等于目标的
                // 需要考虑一些边界值, 如果已经找到了最后一个元素 还比目标小, 那就直接返回这个元素
                if (mid == array.length - 1 || array[mid + 1] > target)
                    return mid;
                else // 否则继续往后找
                    left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找最后一个小于等于目标元素的索引(目标元素之前的最后一个)
     * <p>
     * 思路: 先排除大于的, 如果大于目标元素就往前找. 如果小于等于目标元素,
     * 就往后找, 找到的条件是 下一个大于目标元素. (这里需要考虑边界问题,就是最后一个元素还是小于目标元素(整个数组都比目标元素小),那就直接返回最后一个)
     *
     * @param array
     * @param target
     * @return
     */
    private static int findLastSmallerEquals2(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            // 当前元素大于目标元素 往前找
            if (array[mid] > target) {
                right = mid - 1;
            } else {
                // 当前元素小于等于目标元素, 往后找
                if (mid == array.length - 1 || array[mid + 1] > target)
                    return mid;
                else
                    left = mid + 1;
            }
        }
        // 没找到
        return -1;
    }

    //5.变式：查找最后一个小于等于给定值的元素（ 存在重复的数据）
//    public static int binarySearch6(int[] array, int target) {
//        int left = 0, mid = 0, right = array.length - 1;
//
//        while (left <= right) {
//            mid = (right - left) >> 1 + left;
//
//            if (array[mid] <= target) {
//                if (mid == array.length - 1 || (array[mid + 1] != target)) {
//                    return mid;
//                } else {
//                    left = mid + 1;
//                }
//            } else {//当前值比 参考值 大， 往前找
//                right = mid - 1;
//            }
//        }
//
//        return -1;
//    }
}
