package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
public class MaximumDepth {

    /**
     * 可以通过深度优先进行递归 或者 层级遍历
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        //Integer[] arr = {5, 1, 4, null, null, 3, 6};
        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);

        //System.out.println(maxDepthByRecursion(root, "根", root.val));
        //System.out.println(maxDepthByLoop(root));
        //System.out.println(maxDepthByRecursion(root, "根", root.val));

        System.out.println(maxDepthByRecursion(root));
    }

    /**
     * 使用dfs深度优先的策略(递归)的方式来解决这个问题
     * <p>
     * 从根递归到最底层叶子节点, 值为1, 然后每往上面一层+1,同时每层会对左右两边的值做对比,取最大的. 如果是算最大深度,那就是取根的左子树和右子树中的最大值
     * <p>
     * 我们每个结点只访问一次，因此时间复杂度为 O(N)，
     *
     * @param root
     * @return
     */
    private static int maxDepthByRecursion(TreeNode root, String position, int val) {
        if (root == null) {
            System.out.println(String.format("%s从%d节点到叶子节点递归终止", position, val));
            return 0;
        }

        //System.out.println(String.format("%s从%d节点到叶子节点递归", position, val));

        int left = maxDepthByRecursion(root.left, "左边", root.val);
        // 左边递归到了叶子节点, 开始往根节点走
        System.out.println(String.format("左边从叶子节点往根节点开始, 当前节点为: %d left = %d", root.val, left));

        int right = maxDepthByRecursion(root.right, "右边", root.val);
        // 右边递归到了叶子节点, 开始往根节点走
        System.out.println(String.format("右边从叶子节点往根节点开始, 当前节点为: %d right = %d", root.val, right));

        int result = Math.max(left, right) + 1; // 1是因为每一层算1
        System.out.println(String.format("result = %d, left = %d, right = %d", result, left, right));

        return result;
    }

    /**
     * 深度优先遍历, 计算每层左右节点的层数,取最大值
     * <p>
     * 时间复杂度为O(n)
     *
     * @param root
     * @return
     */
    private static int maxDepthByRecursion2(TreeNode root) {
        // 当某个分支到达了叶子节点, 开始回溯
        if (root == null)
            return 0;

        int left = maxDepthByRecursion2(root.left);
        // 左边到了叶子节点开始回溯
        int right = maxDepthByRecursion2(root.right);

        // 从最底层往根节点回溯的过程中, 计算每层的层数, 取左右两边最大的层数并+1(每层的层数)
        // 每次回溯到上一层 都会将当前层左右节点的最大层数+1 带给上一层
        return Math.max(left, right) + 1;// 最底层为0+1=1, 倒数第2层为0+1+1=2
    }


    /**
     * 使用广度优先的方式找出最大深度  遍历+队列
     * 时间复杂度为O(n)
     *
     * @param root
     * @return
     */
    private static int maxDepthByLoop(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        // 返回最大层级数, 即最深层级
        return level;
    }

    private static int maxDepthByRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepthByRecursion(root.left);

        int right = maxDepthByRecursion(root.right);

        return Math.max(left, right) + 1; // 左边子树和右边子树的最大高度 + 1 = 当前层级高度
    }
}
