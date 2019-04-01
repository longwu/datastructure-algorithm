package com.study.binarytree.binarysearchtree;

/**
 * 二叉搜索树节点
 */
public class SearchTreeNode {
    public SearchTreeNode left;
    public SearchTreeNode right;
    public SearchTreeNode parent;
    public int val;

    public SearchTreeNode(int val) {
        this.val = val;
    }
}
