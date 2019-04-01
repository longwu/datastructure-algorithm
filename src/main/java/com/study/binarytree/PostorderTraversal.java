package com.study.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * <p>
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 */
public class PostorderTraversal {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> treeNodes = TreeUtils.buildTree(arr);
        TreeNode root = treeNodes.get(0);

        //List<Integer> list = postorderTraversal(root);
        //List<Integer> list = postorderTraversal2(root);
        //List<Integer> list = postorderTraversal3(root);
        List<Integer> list = postorderTraversal4(root);
        for (Integer num : list) {
            System.out.println(num);
        }
    }

    /**
     * 使用递归方法进行后续遍历
     * <p>
     * 后续遍历 左 - 右 - 根
     *
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root, list);
        return list;
    }

    private static void traversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            traversal(root.left, list);
            traversal(root.right, list);
            list.add(root.val);
        }
    }

    /**
     * 使用递归2
     *
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root != null) {
            if (root.left != null) {
                list.addAll(postorderTraversal2(root.left));
            }

            if (root.right != null) {
                list.addAll(postorderTraversal2(root.right));
            }

            list.add(root.val);
        }
        return list;
    }

    /**
     * 使用迭代法后序遍历
     * <p>
     * 从右边节点开始处理, 先右再左, 将节点值倒序插到集合中
     *
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return list;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            //和前序比那里不一样, 先将左节点入栈
            if (root.left != null)
                stack.push(root.left);
            //再将右节点入栈
            if (root.right != null)
                stack.push(root.right);

            //逆序添加节点值
            list.add(0, root.val);
        }
        return list;
    }

    /**
     * 迭代遍历 左 右 根
     * <p>
     * 利用根节点和子节点的关系,来找出根节点并巧妙处理
     *
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return list;
        }

        // 使用前置节点来找出其根节点
        TreeNode pre = null;

        stack.push(root);
        while (!stack.isEmpty()) {
            // 拿到栈顶上的元素, 不移除
            TreeNode cur = stack.peek();

            // 如果当前节点没有子节点 或者 当前节点是前置节点的根节点
            if ((cur.left == null && cur.right == null || (pre != null && (pre == cur.left || pre == cur.right)))) {
                list.add(cur.val);
                // 记录前置节点
                pre = cur;
                // 移除已处理的当前节点, 便于下一轮循环处理下一个节点
                stack.pop();
            } else {
                // 先右子节点入栈, 再左子节点, 根据后入先出原则, 下一轮循环先处理左子节点
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }

        return list;
    }
}
