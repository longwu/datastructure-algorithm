package com.study.search;

import com.study.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.*;

/**
 * BFS 广度优先搜索
 * 按层级一层一层进行搜索
 *
 * 常见的解法有: 循环 + 队列
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class BFS {


    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> treeNodes = TreeUtils.buildTree(arr);
        TreeNode root = treeNodes.get(0);

        List<List<TreeNode>> visitedNodes = searchByLoop(root);
        // List<List<TreeNode>> visitedNodes = searchByRecursion(root);
        for (List<TreeNode> currentLevelNodes : visitedNodes) {
            for(TreeNode node : currentLevelNodes){
                System.out.print(node.val + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用遍历的方式进行广度优先搜索, 需要借助一个Queue的结构,先进先出
     *
     * 时间复杂度为O(n). 空间复杂度为O(n^2),用到了两层集合
     *
     * @param root
     * @return
     */
    private static List<List<TreeNode>> searchByLoop(TreeNode root) {
        // 用于存储访问过的节点
        List<List<TreeNode>> visited = new ArrayList<>();

        if (root == null)
            return visited;

        // 记录节点层级
        int level = 0;

        // 利用队列先进先出的特性,实现层级的从左往右遍历
        Queue<TreeNode> nodes = new LinkedList<>();  //链表也是有序的,并且实现了队列的功能,先进先出
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            // 添加层
            visited.add(new ArrayList<>());
            // 获取当前层级的节点数
            int size = nodes.size();

            // 遍历当前层级所有节点, 将他们的子节点从左往右加到队列中去
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll(); //取出最先加入的节点, 并将其子节点从左往右加入到队列中, 用于下一轮遍历
                visited.get(level).add(node); //给当前层添加节点
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
            level++;
        }
        return visited;
    }

    /**
     * 使用递归的方式实现广度优先, 这里在递归的过程中用到了层级控制,如果集合当前层没有节点,就新建; 如果有节点, 就加到当前层里面
     *
     * @param root
     * @return
     */
    private static List<List<TreeNode>> searchByRecursion(TreeNode root) {
        List<List<TreeNode>> nodeList = new ArrayList<>();
        if (root == null)
            return nodeList;
        // 递归获取所有子节点
        search(root, 0, nodeList);
        return nodeList;
    }

    private static void search(TreeNode root, int level, List<List<TreeNode>> nodeList) {
        if (root == null) {
            return;
        }
        // 集合的当前层没有节点, 就新建
        if (level >= nodeList.size()) {
            List<TreeNode> subList = new ArrayList<>();
            subList.add(root);
            nodeList.add(subList);
        } else {
            // 集合的当前层有节点, 就直接添加
            nodeList.get(level).add(root);
        }
        search(root.left, level + 1, nodeList);
        search(root.right, level + 1, nodeList);
    }
}
