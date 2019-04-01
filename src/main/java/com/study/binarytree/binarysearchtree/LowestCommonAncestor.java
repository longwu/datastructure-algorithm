package com.study.binarytree.binarysearchtree;

import com.study.utils.TreeUtils;

import java.util.List;

/**
 * 二叉搜索树的最近公共祖先
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * <p>
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {

        // 由于二叉搜索树也是二叉树, 所以既可以使用二叉搜索树的方法也可以使用二叉树的方法求最近公共祖先
        int[] arr = {5, 3, 7, 1, 4, 6, 8};
        List<SearchTreeNode> treeNodes = TreeUtils.buildSearchTree(arr);

        SearchTreeNode root = treeNodes.get(0);

        SearchTreeNode p = treeNodes.get(5);
        SearchTreeNode q = treeNodes.get(6);

        //SearchTreeNode lca = getlca(root, p, q);
        SearchTreeNode lca = getlca2(root, p, q);
        if (lca != null)
            System.out.println(lca.val);


    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static SearchTreeNode getlca(SearchTreeNode root, SearchTreeNode p, SearchTreeNode q) {

        if (root == null)
            return null;
        if (p == root || q == root)
            return root;

        // 递归根左子树, 查找p或q节点, 一旦找到立刻退出, 进行根右子树递归
        // 因为根左子树找到1个节点, 那么只要根右子树存在另一个节点, 那最近公共祖先就是根节点, 否则就是左子树找到的节点本身(另一个节点肯定在这个节点下面)
        SearchTreeNode left = getlca(root.left, p, q);

        // 递归右子树, 查看右边是否也存在目标节点
        SearchTreeNode right = getlca(root.right, p, q);

        // 如果左右都存在, 直接返回当前节点
        if (left != null && right != null)
            return root;

        // 如果左右都没找到, 说明最近公共不存在
        if (left == null && right == null)
            return null;

        //如果左边不存在,而右边存在返回右边, 如果右边不存在,左边存在,返回左边
        return left == null ? right : left;
    }

    /**
     * 使用二叉搜索树  左子树所有节点 < 父节点 < 右子树所有节点 的原理
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static SearchTreeNode getlca2(SearchTreeNode root, SearchTreeNode p, SearchTreeNode q) {
        // 如果 p和q的值 都小于 root, 直接从树的左边去找
        if (p.val < root.val && root.val > q.val)
            return getlca2(root.left, p, q);
        // 如果 p和q的值都大于 root, 直接从树的右边去找
        if (p.val > root.val && q.val > root.val)
            return getlca2(root.right, p, q);
        // 如果  p.val <  root.val < q.val 或 p.val > root.val > q.val 则直接返回root节点
        return root;
    }
}
