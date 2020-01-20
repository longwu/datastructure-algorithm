package com.study.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 求二维矩阵的走法2(石头篇)
 * <p>
 * 一个人从二维矩阵的起点位置[-1,-1]走到目标位置[-4,-5],只能往左或者往下,不能往回走, 且有石头的位置不能走,求一共有多少种走法.
 * 思路: 如下经过从推导发现, 一共有35种走法. 其中可以发现一个规律就是每个格子的走法数量 = 右和下两个邻格走法数量之和.
 * 算法推导:
 * if(dp[m][n] == "石头")
 * dp[m][n] = 0
 * else
 * dp[m][n] = dp[m-1][n] + dp[m][n-1]
 */
public class CountPathMatrix2 {

    public static void main(String[] args) {

        Map<Integer, Integer> stones = new HashMap<>();
        // 在非边界区域放了一块石头
        stones.put(2, 3);
        // 在边界区域放了一块石头
        stones.put(1, 0);

        System.out.println(countPath(4, 5, stones));
    }

    /**
     * 使用动态规划 计算从[0][0] 到达[m-1][n-1]的走法有多少,这里需要排除石头所在的位置
     *
     * 边界上若有石头需要重点处理
     *
     * @param m      宽
     * @param n      长
     * @param stones 石头所在的位置
     * @return
     */
    private static int countPath(int m, int n, Map<Integer, Integer> stones) {
        int[][] dp = new int[m][n];

        // 横竖坐标为0的边界上走法都为1
        // 石头位置上的走法为0
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 如果在边界上出现石头,那么边界这一行或者这一纵的前面的几格走法都为0
        for (Map.Entry<Integer, Integer> kv : stones.entrySet()) {
            // 横坐标为0的位置上有石头(竖行), 那石头以上的位置走法都为0,因为唯一往下走的位置给堵住了
            if (kv.getKey() == 0) {
                int sj = kv.getValue(); //获取竖坐标
                for (; sj < n; sj++) {
                    dp[0][sj] = 0; //石头上面的走法全部为0
                }
            }

            // 纵坐标为0的位置上有石头(横行),那石头左边的位置走法都为0,因为唯一往右走的位置给堵住了
            if (kv.getValue() == 0) {
                int si = kv.getKey();// 获取横坐标
                for (; si < m; si++) {
                    dp[si][0] = 0; //石头左边的走法全部为0
                }
            }
        }

        // 计算走法
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 石头位置上的走法为0
                if (stones.containsKey(i) && stones.get(i) == j)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
