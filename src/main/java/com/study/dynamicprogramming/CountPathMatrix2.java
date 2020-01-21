package com.study.dynamicprogramming;

/**
 * 求二维矩阵的走法2(石头篇)
 * <p>
 * 1个机器人位于1个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动1步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有1个障碍物。
 * 从左上角到右下角1共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * 算法推导:
 * if(dp[m][n] == 1)
 * dp[m][n] = 0
 * else
 * dp[m][n] = dp[m-1][n] + dp[m][n-1]
 * <p>
 * https://leetcode-cn.com/problems/unique-paths-ii/
 */
public class CountPathMatrix2 {

    public static void main(String[] args) {

        // 3行3列矩阵  1为石头
        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[0] = new int[]{0, 0, 0};
        obstacleGrid[1] = new int[]{0, 1, 0};
        obstacleGrid[2] = new int[]{0, 0, 0};

        System.out.println(uniquePathsWithObstacles(obstacleGrid));


        // 5行4列矩阵 1为石头
        obstacleGrid = new int[5][4];
        obstacleGrid[0] = new int[]{0, 0, 0, 0};
        obstacleGrid[1] = new int[]{0, 1, 0, 0};
        obstacleGrid[2] = new int[]{0, 0, 0, 0};
        obstacleGrid[3] = new int[]{0, 0, 0, 0};
        obstacleGrid[4] = new int[]{0, 0, 1, 0};

        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;

        // 终点堵住了 走法为0
        if (obstacleGrid[0][0] == 1)
            return 0;

        // 对网格进行填充, 第1行和第1列都填为1
        // 对第1行进行填充
        for (int i = 0; i < r; i++) {
            // 如果第1行上有石头, 需要将该行后面的元素全部改为0,因为石头阻挡了道路,走法都为0
            if (obstacleGrid[i][0] == 1) {
                for (; i < r; i++) {
                    obstacleGrid[i][0] = 0;
                }
            } else {
                // 没有石头影响的地方 走法为1
                obstacleGrid[i][0] = 1;
            }
        }

        // 对第1列进行填充, 从第1列的第2行开始, 因为第1列第1行和第1行重复了
        for (int j = 1; j < c; j++) {
            // 如果第1列上有石头, 需要对该列以上的元素全部该为0,因为石头阻挡了道路,走法为0
            if (obstacleGrid[0][j] == 1) {
                for (; j < c; j++) {
                    obstacleGrid[0][j] = 0;
                }
            } else {
                // 没有石头影响的地方 走法为1
                obstacleGrid[0][j] = 1;
            }
        }

        // 计算走法, 从第2行第2列开始
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                // 石头位置上的走法为0
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else// 没石头的位置走法 = 相邻下面 + 相邻右边
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
            }
        }

        return obstacleGrid[r - 1][c - 1];
    }

    /**
     * @param obstacleGrid
     * @return
     */
//    private static int countPath2(int[][] obstacleGrid) {
//
//    }
}
