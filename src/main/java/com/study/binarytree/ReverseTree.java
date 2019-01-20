package com.study.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 遍历二叉树 二叉树有三种遍历模式 前序遍历 中序遍历 后续遍历
 * <p>
 * 其中每种遍历 又有多种遍历方法 比如递归, 迭代
 */
public class ReverseTree {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> datas = new ArrayList<TreeNode>();
        for (int data : arr) {
            datas.add(new TreeNode(data));
        }
        //创建一个二叉树
        //----------------方法一-------------------
        // 将一个作为根节点

        TreeNode root = datas.get(0);
        root.left = datas.get(1);
        root.right = datas.get(2);
        root.left.left = datas.get(3);
        root.left.right = datas.get(4);
        root.right.left = datas.get(5);
        root.right.right = datas.get(6);
        root.left.left.left = datas.get(7);
        //----------------方法一-------------------

        //----------------方法二-------------------
        //tree.root = datas.get(0);
        // 创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
//        for (int i = 0; i < arr.length / 2; i++) {
//            datas.get(i).left = datas.get(i * 2 + 1);
//            if (i * 2 + 2 < datas.size()) {
//                datas.get(i).right = datas.get(i * 2 + 2);
//            }
//        }
        //----------------方法二-------------------


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
     * @param node
     */
    private static void inOrderReverse2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (node == null) {
            return;
        }

        if (node.left != null) {
            stack.push(node);
            stack.push(node.left);
        }

        while (!stack.isEmpty()) {
            // 先处理左节点
            TreeNode tempNode = stack.pop();
            System.out.println(tempNode.val);

            //根据栈的后进先出原理, 将left节点放在right后面入栈
            if (tempNode.right != null) {
                stack.push(tempNode.right);
            }

            if (tempNode.left != null) {
                stack.push(tempNode.left);
            }

            stack.push(node);
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
