package com.study.utils;

import com.study.binarytree.TreeNode;
import com.study.binarytree.binarysearchtree.SearchTreeNode;

import java.util.ArrayList;
import java.util.List;

public final class TreeUtils {

    /**
     * 构建二叉树,
     *
     * @param nodes
     * @return 返回二叉树的所有节点, root为第0个
     */
    public static List<TreeNode> buildTree(int[] nodes){
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (int data : nodes) {
            treeNodes.add(new TreeNode(data));
        }
        //创建一个二叉树
        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
        for (int i = 0; i < nodes.length / 2; i++) {
            treeNodes.get(i).left = treeNodes.get(i * 2 + 1);
            if (i * 2 + 2 < treeNodes.size()) {
                treeNodes.get(i).right = treeNodes.get(i * 2 + 2);
            }
        }
        return treeNodes;
    }

    /**
     * 构建二叉搜索树
     *
     * @param nodes
     * @return 返回二叉搜索树的所有节点, root为第0个
     */
    public static List<SearchTreeNode> buildSearchTree(int[] nodes){
        List<SearchTreeNode> treeNodes = new ArrayList<SearchTreeNode>();
        for (int data : nodes) {
            treeNodes.add(new SearchTreeNode(data));
        }
        //创建一个二叉树
        SearchTreeNode root = treeNodes.get(0);
        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
        for (int i = 0; i < nodes.length / 2; i++) {
            treeNodes.get(i).left = treeNodes.get(i * 2 + 1);
            if (i * 2 + 2 < treeNodes.size()) {
                treeNodes.get(i).right = treeNodes.get(i * 2 + 2);
            }
        }
        return treeNodes;
    }
}
