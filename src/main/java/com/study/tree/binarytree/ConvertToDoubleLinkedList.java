package com.study.tree.binarytree;


import com.alibaba.fastjson.JSON;
import com.study.linkedlist.DoubleLinkedNode;
import com.study.utils.DoubleLinkedListUtils;
import com.study.utils.LinkedListUtils;
import com.study.utils.TreeUtils;

import java.util.*;

/**
 * 将二叉搜索树转成双向链表
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表
 * https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=295&tqId=23253&sourceUrl=%2Fexam%2Foj%3FquestionJobId%3D10%26subTabName%3Donline_coding_page
 * 注意:
 * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
 * 2.返回链表中的第一个节点的指针
 * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
 * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出
 */
public class ConvertToDoubleLinkedList {

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 1, 4, 6, 8};
        List<TreeNode> treeNodes = TreeUtils.buildTreeAndList(arr);
        TreeNode root = treeNodes.get(0);
        List<Integer> list = new ArrayList<>();
        // 递归遍历二叉树
//        traverseByRecursive(root, list);
//        DoubleLinkedNode node = DoubleLinkedListUtils.convertTo(list);
        // 循环遍历二叉树
        List<Integer> list2 = inOrderTraverse(root);
        DoubleLinkedNode node2 = DoubleLinkedListUtils.convertTo(list2);
        DoubleLinkedListUtils.printLinkedList(node2);
    }



    private static void traverseByRecursive(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        traverseByRecursive(node.left, list);
        // 中序遍历的节点加入集合中
        list.add(node.val);
        traverseByRecursive(node.right, list);
    }

    /**
     * 中序遍历 左根右
     *
     * @param node
     */
    private static List<Integer> inOrderTraverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        // 循环条件,当前节点不为空, 或者 栈不为空
        while (root!=null || !stack.isEmpty()) {

            // 当前节点不为空, 不停遍历左子树, 左根右
            while (root != null) {
                // 将左边节点一一放入栈中,直到左边最底层叶子节点
                stack.push(root);
                root = root.left;
            }

            // 栈不为空,将栈里的节点弹出一个,并且切到右子树,供后续继续往左子树遍历
            if(!stack.isEmpty()){
                // 将左子树最底部的叶子节点弹出, 并且将当前节点切到右子树,便于后续继续往左边遍历
                TreeNode node = stack.pop();
                result.add(node.val);
                root = node.right;
            }
        }
        return result;
    }
}
