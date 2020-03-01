package com.study.tree.ntree;

import java.util.List;

/**
 * N叉树, 具有多颗子节点的N叉树
 */
public class NTreeNode {

    public int val;
    public List<NTreeNode> children;

    public NTreeNode(int val){
        this.val = val;
    }

    public NTreeNode(int val, List<NTreeNode> children){
        this.val = val;
        this.children = children;
    }
}
