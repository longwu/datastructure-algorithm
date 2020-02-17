package com.study.binarytree;

import com.study.utils.TreeUtils;

import java.util.*;

/**
 * 层级遍历二叉树，广度优先遍历bfs
 * 1
 * 2    3
 * 4 5  6 7
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/comments/
 * <p>
 * 解题思路，
 * 1.借用一个queue先进先出的结构来遍历二叉树 remove和add方法
 * 2.借用一个stack后进先出的接口来遍历二叉树 pop和push方法
 * <p>
 * 用宽度优先搜索遍历来划分层次：[[1], [2, 3], [4, 5 , 6 ,7]]。
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-ci-bian-li-by-leetcode/
 */
public class LevelTraversal {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(arr);

        //List<List<Integer>> allNodes = levelOrder(root);
        List<List<Integer>> allNodes = levelOrder2(root);

        for (List<Integer> nodesInALevel : allNodes) {
            System.out.println();
            for (Integer node : nodesInALevel) {
                System.out.print(node + " ");
            }
        }
    }

    /**
     * 返回二叉树的所有节点的值
     * <p>
     * 使用迭代的方式
     * <p>
     * 思路: 使用一个队列来装载二叉树的每一层节点, 通过先进先出的原理使得取出来的节点可以根据装入的节点顺序一样(根 左 右)
     *
     * @param root
     * @return
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        // 创建一个2维的节点集合， 第1维度为层集合,第2维度为每层的节点集合
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        //如果根节点为空，直接返回空列表
        if (root == null) return levels;

        // 创建一个当前层的队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 往节点结合中添加当前层级的空节点集合
            List<Integer> currentLevelNodes =  new ArrayList<>();
            // 获取当前层次的节点个数
            int level_length = queue.size();
            // 遍历当前层的节点个数
            for (int i = 0; i < level_length; i++) {
                TreeNode node = queue.remove();//取出并移除队列的第一个节点 根据先进先出原则, 取出的顺序为 根 左 右(根据添加时候的顺序一致)

                // 往节点集合中添加当前层级的节点
                currentLevelNodes.add(node.val);

                // 往当前层队列中添加当前层的子节点,用于下一次迭代处理
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            levels.add(currentLevelNodes);
        }
        return levels;
    }

    /**
     * 使用 queue
     *
     * @param root
     * @return
     */
    private static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<List<Integer>>();

        if (root == null)
            return nodes;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        // 遍历队列中的节点,当前层里的所有节点
        while (queue.size() > 0) {
            List<Integer> currentLevelNodes =  new ArrayList<>();

            // 当前层节点数
            int nodeCount = queue.size();

            // 遍历当前层所有的节点, 往节点结合中添加当前层所有节点值, 往队列中添加当前所有节点的子节点
            for (int i = 0; i < nodeCount; i++) {
                // 添加当前层节点值
                TreeNode node = queue.remove();
                currentLevelNodes.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            nodes.add(currentLevelNodes);
        }
        return nodes;
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

    private static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<List<Integer>>();

        if (root == null)
            return nodes;

        int level = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        // 遍历队列中的节点,当前层里的所有节点
        while (queue.size() > 0) {
            nodes.add(new ArrayList<Integer>());

            // 当前层节点数
            int nodeCount = queue.size();

            // 遍历当前层所有的节点, 往节点结合中添加当前层所有节点值, 往队列中添加当前所有节点的子节点
            for (int i = 0; i < nodeCount; i++) {
                // 添加当前层节点值
                TreeNode node = queue.remove();
                nodes.get(level).add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            // 接着循环下一层
            level++;
        }
        return nodes;
    }
}
