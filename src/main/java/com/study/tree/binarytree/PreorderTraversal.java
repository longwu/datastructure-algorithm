package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历  深度优先遍历dfs
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。 前序遍历: 根 - 左 - 右
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
 * 输出: [1,2,3]
 * <p>
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class PreorderTraversal {
    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);

        //List<Integer> list = preorderTraversal(root);
        List<Integer> list = preorderTraversalByLoop(root);
        // List<Integer> list = preorderTraversalByLoop2(root);
        //List<Integer> list = preorderTraversalByLoopWithList(root);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }


    /**
     * 通过递归的方式遍历整个二叉树 时间复杂度O(n)
     * <p>
     * 前序遍历: 根 - 左 - 右
     *
     * @param root
     * @param list
     */
    private static void traversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            traversal(root.left, list);
            traversal(root.right, list);
        }
    }


    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        traversal(root, list);
        return list;
    }

    /**
     * 使用迭代的方式 遍历二叉树
     * <p>
     * 用到栈后入先出的原理, 将左子树放在右子树后面入栈, 确保左子树先弹出,优先处理左子树,之后再处理右子树
     * <p>
     * 关于为什么循环中没专门放入根节点, 因为除了最顶层的根节点, 其他层级的根节点都是左右子树
     * <p>
     * 时间复杂度O(n), 空间复杂度O(n)
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalByLoop(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return list;
        }

        stack.push(root);
        System.out.println(String.format("节点%d入栈", root.val));

        while (!stack.isEmpty()) {
            // 每次优先弹出左节点,
            // 由于每次左子树都是最后放进去,而每次循环都只pop一次, 存在左子树优先pop左子树,没有再pop右节点
            TreeNode node = stack.pop();
            System.out.println(String.format("节点%d弹出", node.val));
            list.add(node.val);

            // 根据栈后进先出的原理, 把left节点放在right后面放入栈中
            if (node.right != null) {
                stack.push(node.right);
                System.out.println(String.format("右节点%d入栈", node.right.val));
            }

            if (node.left != null) {
                stack.push(node.left);
                System.out.println(String.format("左节点%d入栈", node.left.val));
            }
        }
        return list;
    }

    public static List<Integer> preorderTraversalByLoop2(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();

        if (root == null)
            return nodes;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); //将节点放到栈顶

        while (!stack.isEmpty()) {

            // 弹出栈顶的节点
            TreeNode node = stack.pop();
            nodes.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            // 根据后进先出的原理,将左子树节点放在后面入栈
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return nodes;
    }

    /**
     * 使用循环 + 集合 实现前序遍历
     * 每次都将节点放到集合顶部, 并且从集合顶部取出节点
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalByLoopWithList(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();

        if (root == null)
            return nodes;

        List<TreeNode> list = new ArrayList<>();
        list.add(0, root); //将节点放到集合顶部

        while (!list.isEmpty()) {

            // 取出并删除集合顶部元素
            TreeNode node = list.remove(0);
            nodes.add(node.val);

            if (node.right != null) {
                // 将右节点放到集合顶部
                list.add(0, node.right);
            }

            // 根据后进先出的原理,将左子树节点放在后面入栈
            if (node.left != null) {
                // 将左节点放到集合顶部
                list.add(0, node.left);
            }
        }
        return nodes;
    }
}
