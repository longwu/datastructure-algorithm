package com.study.binarytree.binarysearchtree;

import com.study.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证二叉搜索树 又名 二叉排序树/二叉平衡(查找)树
 * <p>
 * BinarySearchTree BST
 *
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {
    /**
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param args
     */
    public static void main(String[] args) {
        //Integer[] arr = {5, 3, 7, 1, 4, 6, 8};
        //Integer[] arr = {1, 1};

        Integer[] arr = {5, 1, 6, null, null, 3, 7};
        //Integer[] arr = {3, 9, 20, null, null, 15, 7};

        TreeNode root = TreeUtils.buildTree(arr);
        System.out.println("中序遍历-----");
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        inorder(root, nodes); // 验证树是否正确,从小到大输出

        for (TreeNode node : nodes) {
            System.out.print(node.val + " ");
        }
        System.out.println("\r\n中序遍历-----");

        System.out.println("打印出树的结构-----");
        // 将刚刚创建的树打印出来
        TreeUtils.show(root);
        System.out.println("打印出树的结构-----");

        // 中序遍历获取所有节点 判断是否为有序节点
        //System.out.println(isValidBST_InOrder(root));
        //System.out.println(isValidBST_InOrder_2(root));

        // 递归的方式判断是否所有子树 都满足 左子树<节点<右子树
        System.out.println(isValidBST(root, null, null));
        //System.out.println(isValidBST_2(root, null, null));

        //System.out.println(isValidBST2(root));
    }


    /**
     * 判断该tree是否为一个有效的二叉搜索树
     * <p>
     * 先使用中序遍历, 将每个节点放到一个arraylist集合中
     * <p>
     * 然后遍历集合,对比前后两个元素, 看看是不是后面的元素都比前面元素大,如果是则说明集合是从小到大排列
     * <p>
     * 同理说明二叉搜索树里的节点也是按左 根 右 从小到大排列的
     *
     * @param root
     * @return
     */
    private static boolean isValidBST_InOrder(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        inorder(root, nodes); // 验证树是否正确,从小到大输出

        for (int i = 0; i < nodes.size() - 1; i++) {
            //如果前一个元素大于后一个元素, 则说明不是有效二叉搜索树
            if (nodes.get(i).val >= nodes.get(i + 1).val) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidBST_InOrder_2(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        // 中序遍历后, 返回的节点为从小到大有序的
        inorder_2(root, nodes);

        for (int i = 0; i < nodes.size() - 1; i++) {
            // 如果后面的节点大于前面的节点 说明它不是二叉搜索树
            if (nodes.get(i).val >= nodes.get(i + 1).val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证是否为二叉搜索树, 可以判断左边所有与节点值是否小于根节点, 右边所有节点值 大于 根节点.
     * 以及每棵子树 都是 左子树 < 节点 < 右子树
     * <p>
     * 深度优先搜索: 使用递归的方式遍历左右子树, 判断是否每个节点 都满足  左子树 < 节点 < 右子树
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    private static boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) //如果root为空 说明当前节点已经递归到最底层了 或者树本身就是空树
            return true;
        if (min != null && root.val < min) //如果root小于最小值, 不是二叉搜索树
            return false;
        if (max != null && root.val > max) //如果root大于最大值, 也不是二叉搜索树
            return false;

        System.out.print(root.val + " ");

        // 是否左子树的所有节点肯定都比根节点小 右子树的所有节点都比根大
        boolean left = isValidBST(root.left, min, root.val);
        boolean right = isValidBST(root.right, root.val, max);

        if (!left) {
            System.out.println(String.format("不满足的左子树节点: node: %d, min: %d", root ==null || root.left == null || root.left.val == null ? "空节点" : root.left.val, min));
        }

        if(!right){
            System.out.println(String.format("不满足的右子树节点: node: %d, max: %d", root ==null || root.right == null || root.right.val == null ? "空节点" : root.left.val, max));
        }

        return left && right; //如果左边和右边都满足条件, 则说明该数为二叉搜索树
        //return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    private static boolean isValidBST_2(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if (min != null && root.val < min) //跟节点不能小于最小值
            return false;
        if (max != null && root.val > max) //跟节点也不能大于最大值
            return false;

        return isValidBST_2(root.left, min, root.val) && isValidBST_2(root.right, root.val, max);
    }

    /**
     * 使用中序遍历 获取二叉树每个节点
     * <p>
     * 左 根 右
     *
     * @param root
     */
    private static void inorder(TreeNode root, List<TreeNode> nodes) {
        if (root != null) {
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }


    private static void inorder_2(TreeNode root, List<TreeNode> nodes) {
        if (root != null) {
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }
}
