package com.study.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。
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
 * 输出: [1,2,3]
 * <p>
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> treeNodes = TreeUtils.buildTree(arr);
        TreeNode root = treeNodes.get(0);

        //List<Integer> list = preorderTraversal(root);
        List<Integer> list = preorderTraversal2(root);
        for (Integer num : list) {
            System.out.println(num);
        }
    }


    /**
     * 通过递归的方式遍历整个二叉树
     * <p>
     * 前序遍历: 根 - 左 - 右
     *
     * @param root
     * @param list
     */
    private static void traversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            traversal(root.left, list);
            traversal(root.right, list);
        }
    }


    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root, list);
        return list;
    }

    /**
     * 使用迭代的方式 遍历二叉树
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return list;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            // 根据栈后进先出的原理, 把left节点放在right后面放入栈中
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }
}
