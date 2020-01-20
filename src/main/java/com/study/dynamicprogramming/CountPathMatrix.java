package com.study.dynamicprogramming;

/**
 * 求二维矩阵的走法
 *
 * 一个人从二维矩阵的起点位置[-1,-1]走到目标位置[-4,-5],只能往左或者往下,不能往回走,求一共有多少种走法.
 * 思路: 如下经过从推导发现, 一共有35种走法. 其中可以发现一个规律就是每个格子的走法数量 = 右和下两个邻格走法数量之和.
 * 算法推导: dp[-3,-4] = dp[-4,-4] + dp[-3,-5]
 * dp[-2,-4] = dp[-3,-4] + dp[-2,-5]
 * ...
 * dp[-1,-1] = dp[-2,-1] + dp[-1,-2]
 * dp[m,n] = dp[m-1,n] + dp[m,n-1]
 */
public class CountPathMatrix {

    public static void main(String[] args) {

        System.out.println(countPath(4,5));
        System.out.println(countPath(8,9));
    }

    /**
     * 有一个宽为width,长为height的矩阵
     * 从起点[0,0] 走到终点[width-1,height-1] 一共有多少种走法
     * <p>
     * 解法 动态规划, 通过二维数组进行递推
     *
     * 时间复杂度为O(n), 空间复杂度为O(n)
     *
     * @return
     */
    private static int countPath(int width, int height) {
        // 创建一个宽为width,长为height
        int[][] matrix = new int[width][height];

        // 先将横坐标为0 和 纵坐标为0的所有表格都填上, 走法都是一种.
        // 横坐标为0的只能从左往右横着走. 纵坐标为0的只能从上往下竖着走
        for (int i = 0; i < width; i++) {
            matrix[i][0] = 1;
        }
        for (int j = 0; j < height; j++) {
            matrix[0][j] = 1;
        }

        // 从横纵坐标不为0的格子开始推倒
        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        //返回走后一个格子的走法数量, 由于数组是从0开始,所以实际最大索引为长度-1
        return matrix[width - 1][height - 1];
    }
}
