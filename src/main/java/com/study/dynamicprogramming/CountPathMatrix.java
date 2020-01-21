package com.study.dynamicprogramming;

import java.util.Arrays;

/**
 * 求二维矩阵的走法(不同路径)
 * <p>
 * 一个人从二维矩阵的起点位置[-1,-1]走到目标位置[-4,-5],只能往左或者往下,不能往回走,求一共有多少种走法.
 * 思路: 如下经过从推导发现, 一共有35种走法. 其中可以发现一个规律就是每个格子的走法数量 = 右和下两个邻格走法数量之和.
 * 算法推导: dp[-3,-4] = dp[-4,-4] + dp[-3,-5]
 * dp[-2,-4] = dp[-3,-4] + dp[-2,-5]
 * ...
 * dp[-1,-1] = dp[-2,-1] + dp[-1,-2]
 * dp[m,n] = dp[m-1,n] + dp[m,n-1]
 * <p>
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class CountPathMatrix {

    public static void main(String[] args) {

        //System.out.println(uniquePaths(4, 5));
        //System.out.println(uniquePaths2(4, 5));
        // System.out.println(uniquePaths2_2(4, 5));
        System.out.println(uniquePaths3(4, 5));
//        System.out.println(uniquePaths(8, 9));
//        System.out.println(uniquePaths2(8, 9));
    }

    /**
     * 有一个宽为m,长为n的矩阵
     * 从起点[0,0] 走到终点[m-1,n-1] 一共有多少种走法
     * <p>
     * <p>
     * 解法 动态规划, 通过二维数组进行递推
     * <p>
     * 时间复杂度为O(m*n), 空间复杂度为O(m*n)
     *
     * @return
     */
    private static int uniquePaths(int m, int n) {
        // 创建一个宽为m,长为n
        int[][] matrix = new int[m][n];

        // 先将横坐标为0 和 纵坐标为0的所有表格都填上, 走法都是一种.
        // 横坐标为0的只能从左往右横着走. 纵坐标为0的只能从上往下竖着走
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 1;
        }

        // 从横纵坐标不为0的格子开始推倒
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        //返回走后一个格子的走法数量, 由于数组是从0开始,所以实际最大索引为长度-1
        return matrix[m - 1][n - 1];
    }

    /**
     * 由于计算一个坐标元素的值只需要用到它的下方和右边元素的值即可. 所以我们可以使用两个一维数组来存当前这一列和相邻的右边这一列即可
     * <p>
     * 针对uniquePaths()方法的优化, 使用2个一维数组来节省空间, 空间复杂度变为O(2n), 时间复杂度为O(m*n)
     * <p>
     * https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
     *
     * @param m
     * @param n
     * @return
     */
    private static int uniquePaths2(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1); //将1存在数组的每个元素位置上
        Arrays.fill(cur, 1);
        // 从右往左遍历每列的元素
        for (int i = 1; i < m; i++) {
            // 从下往上遍历每行的元素
            for (int j = 1; j < n; j++) {
                // 当前列的元素等于 相邻下方元素 + 右边元素
                cur[j] = cur[j - 1] + pre[j];
                System.out.println(String.format("j=%d", j));
                System.out.println(String.format("cur[j]=%d, cur[j-1]=%d, pre[j]=%d", cur[j], cur[j - 1], pre[j]));
            }
            // 一轮循环后,将当前这一列数组赋给右边这一列,便于下一轮循环使用
            pre = cur;
        }
        return pre[n - 1];
    }

    private static int uniquePaths2_2(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];

        //将第1列和第2列用1进行填充
        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 某一个格子的走法等于下方和右方走法之和
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur;
        }
        // 返回最后一列最后一行的元素 走法数
        return pre[n - 1];
    }

    /**
     * 针对两列数组进行优化,将两列数组合并为一列数组,不断的使用 新值 = 上一列新值 + 旧值(右侧) 可以进一步优化为 新值 += 旧值(右侧)
     *
     * 由于只用了一个数组, 空间复杂度变成了O(n), 时间复杂度没变,依旧为O(m*n)
     *
     * @param m
     * @param n
     * @return
     */
    private static int uniquePaths3(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                // 充分利用每一列开始的时候下方格子都为1 + 上一列右边的值 = 当前值 = 1 + cur[j-1] = a
                // 然后下一列 = 当前值 + 上一列右边的值   a + cur[j] = b
                // 接着再下一列 = 当前值 + 上一列右边的值  b + cur[j+1] = c
                //cur[j] = cur[j] + cur[j-1];
                cur[j] += cur[j-1];
                System.out.println("cur[j] = " + cur[j]);
            }
        }
        return cur[n-1];
    }
}
