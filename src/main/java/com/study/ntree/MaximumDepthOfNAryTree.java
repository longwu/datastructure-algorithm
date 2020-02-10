package com.study.ntree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * N叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 * 我们应返回其最大深度，3。
 *
 *    3
 * /  \  \
 * 9  20 11
 * /   \
 * 15   7
 *
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 */
public class MaximumDepthOfNAryTree {

    public static void main(String[] args) {

    }

    public static int maxDepth(NTreeNode root) {
        // 节点为null是返回0
        if(root == null)
            return 0;

        // 没有子节点,返回1(有根节点)
        if(root.children.isEmpty())
            return 1;

        List<Integer> depthList = new ArrayList<>();
        // 获取所有子树的深度
        for(NTreeNode node : root.children){
            depthList.add(maxDepth(node));
        }

        // 返回所有子树的最大深度+1
        return Collections.max(depthList) + 1;
    }
}
