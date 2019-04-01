package com.study.binarytree.binarysearchtree;

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
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 1, 4, 6, 8};

        List<SearchTreeNode> treeNodes = TreeUtils.buildSearchTree(arr);

        List<SearchTreeNode> nodes = new ArrayList<SearchTreeNode>();

        SearchTreeNode root = treeNodes.get(0);
        inorder(root, nodes); // 验证树是否正确,从小到大输出

        for (SearchTreeNode node : nodes) {
            System.out.print(node.val + " ");
        }

        System.out.println(isValidBST(root));

        //System.out.println(isValidBST(root, 1, 8));
        System.out.println(isValidBST2(root, 1, 8));
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
    private static boolean isValidBST(SearchTreeNode root) {
        List<SearchTreeNode> nodes = new ArrayList<SearchTreeNode>();
        inorder(root, nodes); // 验证树是否正确,从小到大输出

        for (int i = 0; i < nodes.size() - 1; i++) {
            //如果前一个元素大于后一个元素, 则说明不是有效二叉搜索树
            if (nodes.get(i).val >= nodes.get(i + 1).val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证是否为二叉搜索树, 可以根据它的 左子节点 < 父节点 < 右子节点的特性来 判断每个节点是否都满足
     *
     * @param root
     * @return
     */
    private static boolean isValidBST(SearchTreeNode root, Integer min, Integer max) {
        if (root == null) //如果root为空 说明当前节点已经递归到最底层了 或者树本身就是空树
            return true;
        if (min != null && root.val < min) //如果root小于最小值, 不是二叉搜索树
            return false;
        if (max != null && root.val > max) //如果root大于最大值, 也不是二叉搜索树
            return false;

        //左子树的所有节点肯定都比根节点小 右子树的所有节点都比根大
        boolean left = isValidBST(root.left, min, root.val);// 左子树跟节点的值为最大值
        boolean right = isValidBST(root.right, root.val, max); //右子树跟节点为最小值

        return left && right; //如果左边和右边都满足条件, 则说明该数为平衡二叉树
        //return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }


    private static boolean isValidBST2(SearchTreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if (min != null && root.val < min) //跟节点不能小于最小值
            return false;
        if (max != null && root.val > max) //跟节点也不能大于最大值
            return false;

        //不停的递归左子树 和 右子树, 判断是否有不满足条件(左<父<右)的节点存在
        return isValidBST2(root.left, min, root.val) && isValidBST2(root.right, max, root.val);
    }


    /**
     * 使用中序遍历 获取二叉树每个节点
     * <p>
     * 左 根 右
     *
     * @param root
     */
    private static void inorder(SearchTreeNode root, List<SearchTreeNode> nodes) {
        if (root != null) {
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }
}
