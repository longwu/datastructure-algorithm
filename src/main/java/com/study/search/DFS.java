package com.study.search;

import com.study.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先搜索
 * <p>
 * 其过程简要来说是对每一个可能的分支路径深入到不能再深入为止，而且每个节点只能访问一次.
 * <p>
 * 常见的解法有: 递归(递归是天然的深度优先遍历法则)
 */
public class DFS {

    /**
     * 深度优先遍历
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(arr);
        List<TreeNode> nodes = search(root);
        for (TreeNode node : nodes) {
            System.out.print(node.val + " ");
        }
    }

    /**
     * 通过递归的方式实现深度优先遍历
     *
     * @return
     */
    private static List<TreeNode> search(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        if (root == null)
            return nodeList;
        dfs(root, nodeList);
        return nodeList;
    }

    private static void dfs(TreeNode node, List<TreeNode> nodeList) {
        // 递归结束条件为节点为空
        if (node == null) {
            return;
        }

        // 将节点加到集合中, 因为有些节点会反复访问,所以这里需要去重
        if (!nodeList.contains(node)) {
            nodeList.add(node);
            //System.out.println("经过节点:" + node.val);
        } else {
            //System.out.println("经过重复节点:" + node.val);
        }


        // 先从左边从外往内递归 直到最深处, 当node为null的时候结束递归开始从内往外
        if (node.left != null) {
            // 1 3 7 5
            System.out.println("递归左边: " + node.left.val);
            dfs(node.left, nodeList);
        }

        // 再从右边一直往内部
        // 左边从外往内直到node==null后终止往内, 开始由内往外, 再往外的过程中不断再由外往内递归右子树
        if (node.right != null) {
            // 4 2 6
            System.out.println("递归右边: " + node.right.val);
            dfs(node.right, nodeList);
        }
    }
}
