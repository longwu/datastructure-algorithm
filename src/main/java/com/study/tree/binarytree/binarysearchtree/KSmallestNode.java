package com.study.tree.binarytree.binarysearchtree;

import com.study.tree.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.List;

/**
 * 查找二叉搜索树中第k个小的结点
 */
public class KSmallestNode {

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 1, 4, 6, 8};
        List<TreeNode> treeNodes = TreeUtils.buildTreeAndList(arr);
        TreeNode root = treeNodes.get(0);

        TreeUtils.show(root);

        int k = 6;
        TreeNode kNode = getNodes(root, k);
        // 获取第k个节点
        System.out.println(kNode.val);
    }

    private static TreeNode getNodes(TreeNode root, int k) {
        // 中序遍历获取搜索二叉树
        return helper(root, k);
    }

    // 计数器需要设置成全局, 避免放入递归调用栈
    private static int count = 0;

    private static TreeNode helper(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode left = helper(root.left, k);
        if (left != null) // 不为空返回是为了继续执行下面的count++ 返回第k个目标root节点, 否则root==null的之后 直接就返回null结束递归了
            return left;

        count++; // 每次遍历完一个节点 k-1, 直到k==0 获取第k个节点
        if (count == k) {
            return root; // 返回目标节点, 进行回溯
        }

        TreeNode right = helper(root.right, k);
        if (right != null)
            return right;

        return null; //没有找到, 可能k不在树中
    }
}


