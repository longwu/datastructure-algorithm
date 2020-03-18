package com.study.tree.ntree;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树的后序遍历
 * <p>
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class NAryTreePostorderTraversal {

    /**
     * 可以使用 递归 或者 迭代+2个stack的方式 来处理
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    private List<Integer> list = new ArrayList();

    public List<Integer> postOrder(NTreeNode root) {
        if (root != null) {
            for (NTreeNode node : root.children) {
                postOrder(node);
            }
            list.add(root.val);
        }
        return list;
    }
}
