package com.study.binarytree.binarysearchtree;


import com.study.binarytree.TreeNode;
import com.study.utils.TreeUtils;

import java.util.List;

/**
 * 查找二叉搜索树的某个节点
 */
public class CRUD {

    public static void main(String[] args) {
        // 由于二叉搜索树也是二叉树, 所以既可以使用二叉搜索树的方法也可以使用二叉树的方法求最近公共祖先
        int[] arr = {5, 3, 8, 1, 4, 7, 9};
        List<TreeNode> treeNodes = TreeUtils.buildTreeAndList(arr);
        TreeNode root = treeNodes.get(0);

        TreeUtils.show(root);

        int target = 7;
        //int target = 2;
        //boolean result = searchByRecursion(root,target);
        TreeNode result = searchByRecursion2(root, target);
        //TreeNode result = searchByLoop(root, target);
        //System.out.println(result != null ? result.val : "未找到");

        TreeUtils.show(root);
        //TreeNode node = insertByRecursion(root, 6);
        //TreeNode node = insertByRecursion2(root, 6);
        TreeNode node = insertByLoop(root, 6);
        System.out.println(node.val);
        TreeUtils.show(root);
    }

    /**
     * 后续遍历查找结果, 需要在递归的时候将参数传进去
     *
     * @param root   根节点
     * @param target 目标值
     * @return 是否找到目标
     */
    private static boolean searchByRecursion(TreeNode root, int target) {
        if (root == null) {
            return false; // 未找到目标
        }

        if (root.val > target) {
            // 将找到或者未找到目标不断回溯上去
            return searchByRecursion(root.left, target);
        }

        if (root.val < target) {
            // 将找到或者未找到目标不断回溯上去
            return searchByRecursion(root.right, target);
        }

        // 找到目标
        return true;
    }

    /**
     * 查找目标节点
     *
     * @param root   根节点
     * @param target 目标值
     * @return 目标节点
     */
    private static TreeNode searchByRecursion2(TreeNode root, int target) {
        if (root == null)
            return null;

        if (root.val > target) {
            return searchByRecursion2(root.left, target);
        }

        if (root.val < target) {
            return searchByRecursion2(root.right, target);
        }

        // 后续遍历 查找结果
        return root;
    }

    /**
     * 先序遍历查找目标节点
     *
     * @param root
     * @param target
     * @return
     */
    private static TreeNode searchByRecursion3(TreeNode root, int target) {
        // 如果为空, 说明递归到了最底层 叶子节点也没找到
        if (root == null) {
            System.out.println("没有找到目标节点");
            return null;
        }

        // 如果找到了目标
        if (root.val == target) {
            // 一旦找到, 返回结果
            System.out.println("找到了节点" + root.val);
            System.out.println("开始回溯");
            return root;
        }

        // 如果目标值小于根节点, 往左子树找
        if (root.val > target) {
            // 找到目标节点后, 如果曾经从左边找过, 会从左边回溯,并返回结果
            System.out.println("从左子树节点找" + root.left.val);
            TreeNode left = searchByRecursion2(root.left, target);
            System.out.println("回溯到左子树节点" + root.left.val);
            return left;
        } else {
            // 如果目标值大于根节点, 往右子树找
            System.out.println("从右子树节点找" + root.right.val);
            TreeNode right = searchByRecursion2(root.right, target);
            System.out.println("回溯到右子树节点" + root.right.val);
            return right;
        }
    }

    /**
     * 使用循环的方式,查找目标节点
     *
     * @param root
     * @param target
     * @return
     */
    private static TreeNode searchByLoop(TreeNode root, int target) {
        while (root != null) {
            // 找到目标节点 直接返回
            if (root.val == target)
                return root;

            // 目标节点小于根节点
            if (root.val > target) {
                // 往左找
                root = root.left;
            } else {
                // 大于目标节点往右找
                root = root.right;
            }
        }
        // 循环结束的时候,还没找到 返回空
        return null;
    }

    /**
     * 往树中插入新节点
     * <p>
     * 首先通过二分查找 找到该节点的位置, 如果节点存在, 直接返回, 如果不存在则创建并返回.
     *
     * @param root  根节点
     * @param value 要插入的节点
     * @return 根节点
     */
    private static TreeNode insertByRecursion(TreeNode root, int value) {
        if (root == null) {
            // 如果没找到, 创建该节点
            return new TreeNode(value);
        }

        // 从左边找
        if (root.val > value) {
            // 将返回的左节点添加到根节点左边, 如果创建了一个新的节点,将被接上
            root.left = insertByRecursion(root.left, value);
        }

        if (root.val < value) {
            // 从右边找
            // 将返回的右节点添加到根节点右边, 如果创建了一个新的节点,将被接上
            root.right = insertByRecursion(root.right, value);
        }

        // 不断回溯到根节点
        return root;
    }

    private static TreeNode insertByRecursion2(TreeNode root, int value) {
        // 没找到就创建目标节点并返回
        if (root == null) {
            return new TreeNode(value);
        }

        if (root.val > value) {
            // 从左边找
            root.left = insertByRecursion2(root.left, value);
        }
        if (root.val < value) {
            // 从右边找
            root.right = insertByRecursion2(root.right, value);
        }

        return root;
    }

    /**
     * 通过迭代的方式插入新节点
     *
     * @param root  根
     * @param val 新节点值
     * @return 根节点
     */
    private static TreeNode insertByLoop(TreeNode root, int val) {
        TreeNode result = root;
        while (root != null) {
            if (root.val > val) {
                // 判断左节点是否为空, 为空就说明新节点不存在,则创建
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                }
                // 往左边找
                root = root.left;
            }
            if (root.val < val) {
                // 判断右节点是否为空, 为空就说明新节点不存在,则创建
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                }
                // 往右边找
                root = root.right;
            }
            // 新节点已经存在,停止查找,无需新建
            if (root.val == val)
                break;
        }

        // 返回根节点
        return result;
    }

}
