package com.study.tree.binarytree;

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
        //Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        Integer[] arr = {5, 1, 4, null, null, 3, null, null, null, null, null, 8};
        //Integer[] arr = {5, 1};
        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);
        //System.out.println(minDepthByRecursion(root));
        //System.out.println(minDepthByRecursion2(root));
        //System.out.println(minDepthByRecursion5(root));
        System.out.println(minDepthByLevelReverse(root));
    }

    /**
     * 使用分治的方法 从左右节点进行递归,
     * 注意: 当root节点左右孩子都为空时,返回1
     * <p>
     * 如果是单边为空或者都为空的节点, 返回另一边+1 这样能够记录所有分支的高度, 最后通过min(跟的左分支,根的右分支) 得到深度最小的
     * <p>
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

        // 如果是单边为空或者都为空的节点, 返回另一边+1 这样能够记录所有分支的高度, 最后通过min(跟的左分支,根的右分支) 得到深度最小的
        // 如果左边子节点为空的时候, 那么返回右边不为空的孩子深度+1
        if (left == 0)
            return right + 1;
        // 如果右边子节点为空的时候, 那么返回左边不为空的孩子深度+1
        if (right == 0)
            return left + 1;

        //如果左右子节点都不为空, 返回左右深度最小的那个+1
        return Math.min(left, right) + 1;
    }


    private static int minDepthByRecursion2(TreeNode root) {
        if (root == null)
            return 0;

        int left = minDepthByRecursion2(root.left);
        int right = minDepthByRecursion2(root.right);

        // 记录所有分支的深度, 最后在根节点上取左右最短的一条
        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;

        return Math.min(left, right) + 1;
    }

    private static int minDepthByRecursion3(TreeNode root) {
        if (root == null)
            return 0;

        int left = minDepthByRecursion3(root.left);
        int right = minDepthByRecursion3(root.right);

        // 到达叶子节点的时候, 进行回溯的时候计算层级高度
        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;

        return Math.min(left, right) + 1;
    }

    /**
     * 使用层级遍历的方式: 循环+队列 一层一层遍历并记录层数, 当遇到第一个左右子节点为空的节点, 那这个节点就是最小深度的叶子节点, 返回的层数为最小层数(最小深度).
     * <p>
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


    /**
     * 深度优先遍历, 最小深度
     *
     * @param root
     * @return
     */
    private static int minDepthByRecursion5(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepthByRecursion5(root.left);

        int right = minDepthByRecursion5(root.right);
        // 每次都取最小深度, 比如左边为3, 右边为0, 取右边0
        return Math.min(left, right) + 1;
    }

    /**
     * 使用层级遍历,第一个左右子节点为空的叶子节点, 则为最小深度
     *
     * @param root
     * @return
     */
    private static int minDepthByLevelReverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int minLevel = 0;

        while (!queue.isEmpty()) {
            minLevel++;
            int rowCount = queue.size();

            // 遍历当前层里的每一个节点
            for (int i = 0; i < rowCount; i++) {
                TreeNode node = queue.remove();

                if (node.left == null && node.right == null) {
                    // 找到第一个叶子节点 则为最小层高度
                    return minLevel;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return minLevel;
    }
}
