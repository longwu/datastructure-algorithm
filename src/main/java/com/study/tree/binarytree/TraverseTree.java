package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 遍历二叉树 二叉树有三种遍历模式 前序遍历 中序遍历 后续遍历
 * <p>
 * 其中每种遍历 又有多种遍历方法 比如递归, 迭代
 */
public class TraverseTree {
    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(arr);

        //preOrderReverse(root); // 0 1 3 7 4 2 5 6
        //preOrderReverse2(root); // 0 1 3 7 4 2 5 6
        //inOrderReverse(root); // 7 3 1 4 0 5 2 6
        inOrderReverse2(root);
        //afterOrderReverse(root); //7 3 4 1 5 6 2 0
    }

    /**
     * 前序遍历 根在前面
     * <p>
     * 根 - 左 - 右
     * <p>
     * 先输出根节点
     */
    private static void preOrderReverse(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.printf("当前节点----------------%d\r\n", node.val);

        System.out.printf("当前节点%d的左节点为%s\r\n", node.val, node.left == null ? "空" : node.left.val);
        preOrderReverse(node.left);

        System.out.printf("当前节点%d的右节点为%s\r\n", node.val, node.right == null ? "空" : node.right.val);
        preOrderReverse(node.right);
    }


    /**
     * 使用迭代法进行前序遍历
     *
     * @param node
     */
    private static void preOrderReverse2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (node == null) {
            return;
        }

        stack.push(node);

        while (!stack.isEmpty()) {
            // 先处理根节点
            TreeNode tempNode = stack.pop();
            System.out.println(tempNode.val);

            //根据栈的后进先出原理, 将left节点放在right后面入栈
            if (tempNode.right != null) {
                stack.push(tempNode.right);
            }

            if (tempNode.left != null) {
                stack.push(tempNode.left);
            }
        }
    }

    /**
     * 中序遍历 根在中间
     * <p>
     * 左 - 根 - 右
     * <p>
     * 根节点在左节点之后输出
     */
    private static void inOrderReverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.printf("当前节点%d的左节点为%s\r\n", node.val, node.left == null ? "空" : node.left.val);
        inOrderReverse(node.left);

        System.out.printf("当前节点----------------%d\r\n", node.val);

        System.out.printf("当前节点%d的右节点为%s\r\n", node.val, node.right == null ? "空" : node.right.val);
        inOrderReverse(node.right);
    }

    /**
     * 使用迭代法进行中序遍历
     *
     * @param node
     */
    private static void inOrderReverse2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (node == null) {
            return;
        }

        while (node != null || !stack.isEmpty()) {

            // 先将当前节点以及所有左节点入栈
            while (node != null) {
                stack.push(node);
                node = node.left; //左
            }

            if (!stack.isEmpty()) {
                node = stack.pop(); //根
                System.out.println(node.val);
                node = node.right; //右
            }
        }
    }

    /**
     * 后续遍历  根在末尾
     * <p>
     * 左 - 右 - 根
     * <p>
     * 根节点在左右节点输出之后再输出
     */
    private static void afterOrderReverse(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.printf("当前节点%d的左节点为%s\r\n", node.val, node.left == null ? "空" : node.left.val);
        afterOrderReverse(node.left);

        System.out.printf("当前节点%d的右节点为%s\r\n", node.val, node.right == null ? "空" : node.right.val);
        afterOrderReverse(node.right);

        System.out.printf("当前节点----------------%d\r\n", node.val);
    }
}
