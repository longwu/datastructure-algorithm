package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 将二叉树的先序遍历的节点 组装成只有右边节点的二叉树
 * 1
 * 2      3
 * 4  5   6
 * <p>
 * 1
 * 2
 * 4
 * 5
 * 3
 * 6
 */
public class PreorderTraversal2 {

    /**
     * 两种解法:
     * 方法一: 将二叉树前序遍历 取出里面每个节点放入集合, 然后遍历集合,取出里面的节点
     * 方法而: 直接在原来的二叉树上做处理
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);

        List<TreeNode> list = new ArrayList<>();
        traversal(root, list);
        TreeNode newRoot = buildNewTree(list);
        TreeUtils.show(newRoot);
    }

    private static void traversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            TreeNode node = new TreeNode(root.val);
            list.add(node);
            traversal(root.left, list);
            traversal(root.right, list);
        }
    }

    private static TreeNode buildNewTree(List<TreeNode> list) {
        TreeNode root = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            root.right = list.get(i);
            root = root.right;
        }
        return list.get(0);
    }
}
