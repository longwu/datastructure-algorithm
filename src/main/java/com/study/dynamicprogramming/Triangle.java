package com.study.dynamicprogramming;

import java.util.*;

/**
 * 三角形最小路径和
 * <p>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * <p>
 * 链接：https://leetcode-cn.com/problems/triangle
 */
public class Triangle {

    /**
     * 这个题解法跟贪心不一样的是, 它要求全局最优值, 而不是当前最优值
     * <p>
     * 每个点[i][j] 只能访问下一行相邻的两个点  也就是[i+1][j] 和 [i+1][j+1]
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        //System.out.println(minimumTotal(triangle));
        System.out.println(minimumTotal2(triangle));
    }

    /**
     * 动态规划法
     * <p>
     * 从后往前推, 修改每个点的值为其最小路径和, 一层层往上,最终顶点的最短路径 = 顶点值 + 第二层最短路径的那个点的值
     * <p>
     * 时间复杂度为O(m*n)
     * <p>
     * https://leetcode-cn.com/problems/triangle/solution/120-san-jiao-xing-zui-xiao-lu-jing-he-by-alexer-66/
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {

        if (triangle.size() == 0)
            return 0;

        // 从后面往前推, i为倒数第二行, 下面的i+1则为倒数第一行
        // 每一层推完后得出当前层每个节点的最小路径和, 最终将顶点值+第二层最小路径和的那个点 = 最短路径和
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 当前节点的最短路径path = 当前节点下一层两个节点中最短那个节点的路径值 + 当前节点的路径值
                int path = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j);
                triangle.get(i).set(j, path);//修改当前节点路径值为最短路径值
            }
        }

        // 最终结果
        return triangle.get(0).get(0);
    }


    public static int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }

        // 遍历从倒数第2行开始 直到 最顶层元素
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int minPath = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                int pathSum = triangle.get(i).get(j) + minPath;
                triangle.get(i).set(j, pathSum);
            }
        }

        return triangle.get(0).get(0);
    }
}