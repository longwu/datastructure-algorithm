package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的最近公共祖先
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * https://time.geekbang.org/course/detail/130-42708
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> treeNodes = TreeUtils.buildTreeAndList(arr);
        //TreeNode p = treeNodes.get(4);
        //TreeNode q = treeNodes.get(5);

//        TreeNode p = treeNodes.get(1);
//        TreeNode q = treeNodes.get(2);

//        TreeNode p = treeNodes.get(3);
//        TreeNode q = treeNodes.get(4);

        TreeNode p = treeNodes.get(1);
        TreeNode q = treeNodes.get(7);
        TreeNode root = treeNodes.get(0);

        TreeNode lca = getlca(root, p, q);
        if (lca == null) {
            System.out.println("没有最近公共祖先");
        } else {
            System.out.println(lca.val);
        }
    }

    /**
     * 递归的方式查找最近公共祖先
     * <p>
     * 先从左子树进行查找,如果找到第一个目标节点p, 那么不再往下查找,而是直接从目标节点的父节点r的右子树进行查找
     * <p>
     * 如果右子树也找到目标节点q,说明r就是p和q的最近公共祖先
     * <p>
     * 如果右子树没有找到目标节点q, 那么目标节点q肯定在目标节点p的子节点中. 那么p就是p和q的最近公共祖先
     * <p>
     * 如果左子树没有找到目标节点, 那么再从右子树找, 如果找到第一个目标节点q, 那么q就是p和q的最近公共祖先,因为p肯定是q的子节点
     * <p>
     * 若果左右子树都没有找到目标节点, 那么直接返回null
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static TreeNode getlca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            System.out.println("递归往内中止返回null值");
            return null;
        }

        if (root == p || root == q) {
            if (root == p)
                System.out.println("递归往内中止, root == p ==" + root.val);
            if (root == q)
                System.out.println("递归往内中止, root == q ==" + root.val);
            return root;
        }

        // 遍历左子树看有没有目标节点, 有返回目标节点, 没有返回null
        TreeNode left = getlca(root.left, p, q); // 如果找到目标节点, 无需下遍历, 直接从当前节点的右子树进行遍历
        System.out.println("递归往外开始, 左边目标节点left = " + (left == null ? "null" : left.val));
        // 遍历右子树有看没有目标节点, 有返回目标节点, 没有返回null
        TreeNode right = getlca(root.right, p, q);
        System.out.println("递归往外开始, 右边目标节点right = " + (right == null ? "null" : right.val));

        // 如果左右子树都有目标节点, 说明公共祖先就是它本身
        if (left != null && right != null) {
            System.out.println("左右子树都有目标节点,公共祖先为" + root.val);
            return root;
        }

        // 如果目标节点都在左子树,返回左子树匹配的节点
        // 如果目标节点都在右子树,返回右子树匹配的节点
        //return left != null ? left : right;

        if (left != null) {
            System.out.println("公共祖先在左子树, left = " + left.val);
            return left;
        }
        if (right != null) {
            System.out.println("公共祖先在右子树, right = " + right.val);
            return right;
        }

        return null;
    }
}
