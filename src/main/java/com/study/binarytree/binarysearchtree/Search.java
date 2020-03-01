package com.study.binarytree.binarysearchtree;


import com.study.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.List;

/**
 * 查找二叉搜索树的某个节点
 */
public class Search {

    public static void main(String[] args) {
        // 由于二叉搜索树也是二叉树, 所以既可以使用二叉搜索树的方法也可以使用二叉树的方法求最近公共祖先
        int[] arr = {5, 3, 7, 1, 4, 6, 8};
        List<TreeNode> treeNodes = TreeUtils.buildTreeAndList(arr);
        TreeNode root = treeNodes.get(0);

        TreeUtils.show(root);

        //int target = 6;
        int target = 2;
        //boolean result = searchByRecursion(root,target);
        //TreeNode result = searchByRecursion2(root, target);
        TreeNode result = searchByLoop(root, target);
        System.out.println(result != null ? result.val : "未找到");
    }

    /**
     * 后续遍历查找结果, 需要在递归的时候将参数传进去
     *
     * @param root   根节点
     * @param target 目标值
     * @return
     */
    private static boolean searchByRecursion(TreeNode root, int target) {
        if (root == null)
            return false;

        if (root.val > target) {
            return searchByRecursion(root.left, target);
        }

        if (root.val < target) {
            return searchByRecursion(root.right, target);
        }

        // 后续遍历 查找结果
        return true;
    }

    /**
     * 先序遍历查找目标节点
     *
     * @param root
     * @param target
     * @return
     */
    private static TreeNode searchByRecursion2(TreeNode root, int target) {
        // 如果为空, 说明递归到了最底层 叶子节点也没找到
        if (root == null) {
            System.out.println("没有找到目标节点");
            return null;
        }

        // 如果找到了目标
        if (root.val == target) {
            // 一旦找到, 返回结果
            System.out.println("找到了节点" + root.val);
            System.out.println("开始回溯");
            return root;
        }

        // 如果目标值小于根节点, 往左子树找
        if (root.val > target) {
            // 找到目标节点后, 如果曾经从左边找过, 会从左边回溯,并返回结果
            System.out.println("从左子树节点找" + root.left.val);
            TreeNode left = searchByRecursion2(root.left, target);
            System.out.println("回溯到左子树节点" + root.left.val);
            return left;
        } else {
            // 如果目标值大于根节点, 往右子树找
            System.out.println("从右子树节点找" + root.right.val);
            TreeNode right = searchByRecursion2(root.right, target);
            System.out.println("回溯到右子树节点" + root.right.val);
            return right;
        }
    }

    /**
     * 使用循环的方式,查找目标节点
     *
     * @param root
     * @param target
     * @return
     */
    private static TreeNode searchByLoop(TreeNode root, int target) {
        while (root != null) {
            // 找到目标节点 直接返回
            if (root.val == target)
                return root;

            // 目标节点小于根节点
            if (root.val > target) {
                // 往左找
                root = root.left;
            } else {
                // 大于目标节点往右找
                root = root.right;
            }
        }
        // 循环结束的时候,还没找到 返回空
        return null;
    }
}
