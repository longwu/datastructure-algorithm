package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {

    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};

        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);

        //List<Integer> list = inorderTraversal(root);
        //List<Integer> list = inorderTraversal2(root);
        //List<Integer> list = inorderTraversal3(root);
        List<Integer> list = inorderTraversal4(root);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }

    /**
     * 递归遍历 左 - 根 - 右
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root, list);
        return list;
    }

    private static void traversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            //递归左子树
            traversal(root.left, list);
            list.add(root.val);
            //递归右子树
            traversal(root.right, list);
        }
    }


    /**
     * 递归遍历2  左 - 根 - 右
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root != null) {
            // 递归左(右)子树
            if (root.left != null) {
                list.addAll(inorderTraversal2(root.left));
            }
            // 把某个根节点的值 加入到list中
            list.add(root.val);
            // 递归右(左)子树
            if (root.right != null) {
                list.addAll(inorderTraversal2(root.right));
            }
        }
        return list;
    }

    /**
     * 使用迭代法 进行中序遍历
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        // 遍历条件为当前节点不为空 或者 栈不为空
        while (root != null || !stack.isEmpty()) {
            // 将当前节点的所有左节点入栈
            // 如果当前节点不为空,递归其左节点
            while (root != null) {
                stack.push(root);//将左节点入栈
                System.out.println(String.format("节点%d被放入栈中", root.val));
                root = root.left;
            }

            // 从栈中弹出节点
            if (!stack.isEmpty()) {
                // 将栈中的节点的第一个弹出
                // 存在左节点的,优先将最深层左节点弹出
                root = stack.pop();
                System.out.println(String.format("节点%d从栈中弹出", root.val));
                list.add(root.val);
                System.out.println(String.format("将节点%d放入集合中", root.val));
                // 切到右子树,给下一轮找左子树使用
                root = root.right;
            }
        }
        return list;
    }

    private static List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();

        // 中序遍历条件有两个 当前节点不为空 或者 栈不为空
        while (root != null || !stack.isEmpty()) {

            // 将当前节点的所有左边子节点入栈(包括根节点)
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 处理栈中的节点
            if (!stack.isEmpty()) {
                // 将栈顶节点弹出并放入list
                root = stack.pop();
                list.add(root.val);

                // 将节点切到弹出节点的右子节点, 便于下次迭代处理
                root = root.right;
            }
        }
        return list;
    }
}
