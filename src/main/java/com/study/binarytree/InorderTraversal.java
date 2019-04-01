package com.study.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        List<TreeNode> treeNodes  = TreeUtils.buildTree(arr);
        TreeNode root = treeNodes.get(0);

        //List<Integer> list = inorderTraversal(root);
        //List<Integer> list = inorderTraversal2(root);
        List<Integer> list = inorderTraversal3(root);
        for (Integer num : list) {
            System.out.println(num);
        }
    }

    /**
     * 递归遍历 左 - 根 - 右
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root, list);
        return list;
    }

    private static void traversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            //递归左(右)子树
            traversal(root.left, list);
            list.add(root.val);
            //递归右(左)子树
            traversal(root.right, list);
        }
    }


    /**
     * 递归遍历2  左 - 根 - 右
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root != null) {
            // 递归左(右)子树
            if (root.left != null) {
                list.addAll(inorderTraversal2(root.left));
            }
            // 把某个根节点的值 加入到list中
            list.add(root.val);
            // 递归右(左)子树
            if (root.right != null) {
                list.addAll(inorderTraversal2(root.right));
            }
        }
        return list;
    }

    /**
     * 使用迭代法 进行中序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (root != null || !stack.isEmpty()) {
            // 左
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                // 根 (当左子节点为空的时候,该节点变为根)
                root = stack.pop();
                list.add(root.val);
                // 右
                root = root.right;
            }
        }
        return list;
    }
}
