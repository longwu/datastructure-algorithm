package com.study.binarytree;

import com.study.utils.TreeUtils;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 */
public class MinimumDepth {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> treeNodes = TreeUtils.buildTree(arr);
        TreeNode root = treeNodes.get(0);

        //System.out.println(minDepthByRecursion(root));
        System.out.println(minDepthByLoop(root));
    }

    /**
     * 使用分治的方法 从左右节点进行递归
     * 时间复杂度为O(n)
     *
     * @param root
     * @return
     */
    private static int minDepthByRecursion(TreeNode root) {
        if (root == null)
            return 0;
        // 使用分治的方法 从左右节点进行递归
        int left = minDepthByRecursion(root.left);
        int right = minDepthByRecursion(root.right);

        // 如果左边层级为0, 那么返回右边层级+1
        if (left == 0)
            return right + 1;
            // 如果右边层级为0, 返回左边层级+1
        else if (right == 0)
            return left + 1;
        else //如果左右层级都不为0, 返回左右层数最小值+1
            return Math.min(left, right) + 1;
    }


    /**
     * 使用层级遍历的方式: 循环+队列 一层一层遍历并记录层数, 当遇到第一个左右子节点为空的节点, 那这个节点就是最小深度的叶子节点, 返回的层数为最小层数(最小深度).
     *
     * 时间复杂度为O(n), 空间复杂度为O(n)
     *
     * @param root
     * @return
     */
    private static int minDepthByLoop(TreeNode root) {
        int level = 0;
        if (root == null)
            return level;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 如果节点的左右子树都为空, 那么这个节点为叶子节点, 也就是最小深度
                if (node.left == null && node.right == null) {
                    return level;
                }
                // 将左右节点按顺序放入队列中,用于下一层遍历
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }
}
