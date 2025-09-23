package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

/**
 * 验证平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 * <p>
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {
        //Integer[] nodes = {3, 9, 20, null, null, 15, 7};
        Integer[] nodes = {1, 2, 3, 4, null, 6, 7, 8, 9};
        TreeNode root = TreeUtils.buildTree(nodes);
        TreeUtils.show(root);

        System.out.println(isBalanced(root));
        //System.out.println(isBalanced2(root));
    }

    /**
     * 使用前序遍历(根左右),先处理根节点获取高度, 再遍历左右子树是否为平衡二叉树.  从顶至底（暴力法）
     * 通过比较左右子树最大高度差是否大于1 来判断以此节点为根节点下是否是二叉平衡树
     * <p>
     * 从顶至底DFS，以每个节点为根节点，递归判断是否是平衡二叉树：
     * 若所有根节点都满足平衡二叉树性质，则返回 True ；
     * 若其中任何一个节点作为根节点时，不满足平衡二叉树性质，则返回False。
     * <p>
     * 最差时间复杂度为O(N^2)
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        // 根节点为空, 说明是空树,肯定是平衡的
        if (root == null)
            return true;

        // 先处理根节点, 获取高度
        // 获取每个节点的左右子树的高度
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        // 判断当前root节点为根的树是否为平衡二叉树
        if (Math.abs(left - right) > 1)
            return false;

        // 光最大高度满足不行, 还必须每个子树都是平衡二叉树才行. 有可能存在左子树有1和4的高度,右子树都是3的高度,这样最大高度差也是1
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 获取一颗树的最大高度
     *
     * @param root
     * @return
     */
    private static int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        int left = getHeight(root.left);

        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 后续遍历(左右根), 先获取左右子树的高度, 再作判断是否为平衡二叉树
     * 从底部到顶部, 获取每个节点为root根时候的子树最大高度差是否大于1(不平衡), 如果是返回-1; 否则返回当前子树的最大高度
     *
     * 时间复杂度为O(n)
     *
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/
     *
     * @return
     */
    private static boolean isBalanced2(TreeNode root) {
        return depth(root) != -1;
    }

    /**
     * 后续遍历
     * @param root
     * @return
     */
    private static int depth(TreeNode root) {
        if (root == null)
            return 0;

        // 左子树出现不满足条件的
        int left = depth(root.left);
        if (left == -1) // 得到-1 说明不满足条件
            return -1;

        // 右子树出现不满足条件的
        int right = depth(root.right);
        if (right == -1) // 得到-1 说明不满足条件
            return -1;

        // 检查是否满足条件, 左右子树高度差绝对值不超过1
        if (Math.abs(left - right) > 1)
            return -1; //不满足条件就返回-1

        // 返回当前左右子树的最大高都
        return Math.max(left, right) + 1;
    }
}
