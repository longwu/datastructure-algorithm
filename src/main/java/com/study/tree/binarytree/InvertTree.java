package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树翻转
 * <p>
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 */
public class InvertTree {

    public static void main(String[] args) {

        Integer[] nodes = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = TreeUtils.buildTree(nodes);
        TreeUtils.show(root);
        TreeNode newRoot = invertTree(root);
        //TreeNode newRoot = invertTree2(root);
        TreeUtils.show(newRoot);
    }


    /**
     * 后序遍历, 深度优先dfs 自下而上左右节点互换
     * <p>
     * 时间复杂度O(n)
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 从最底层叶子节点开始左右互换
        root.left = right;
        root.right = left;

        // 返回新的root节点,其左右子节点已经互换
        return root;
    }

    /**
     * 使用层级遍历的方式
     *
     * 时间复杂度O(n)
     * @param root
     * @return
     */
    public static TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList();

        queue.offer(root);

        // 一层一层遍历, 并将左右子节点互换
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 当前层级的所有节点取出, 并将其左右子节点进行互换
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    TreeNode tmp = node.left;
                    node.left = node.right;
                    node.right = tmp;

                    // 左右节点互换后, 再将左右节点依次放入队列中
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
