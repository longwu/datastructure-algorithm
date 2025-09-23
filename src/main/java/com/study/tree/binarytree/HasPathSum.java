package com.study.tree.binarytree;

import com.study.utils.TreeUtils;

import java.util.List;
import java.util.Stack;


/**
 * 描述
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 * <p>
 * 例如： 因为存在一条路径   5→4→11→2的节点值之和为 22
 * <p>
 * https://www.nowcoder.com/practice/508378c0823c423baa723ce448cbfd0c?tpId=295&tqId=634&sourceUrl=%2Fexam%2Foj%3FquestionJobId%3D10%26subTabName%3Donline_coding_page
 */
public class HasPathSum {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        TreeNode root = TreeUtils.buildTree(arr);
        TreeUtils.show(root);
        boolean has = hasPathSumByStack2(root, 10);
        System.out.println(has);
    }

    /**
     * 分别从左子树和右子树往里面递归查找, 每次往下走一个节点,则把总和减去这个节点的值,直到为0
     * <p>
     * 深度优先遍历的方式,根->左->右
     *
     * @param root 递归会在执行过程中创建临时变量,记录每一个过程的变化值,所以无需担心值没有变量来记录
     * @return
     */
    public static boolean hasSumPath(TreeNode root, int sum) {

        if (root == null) { // 到了底层叶子节点的分支为空,准备回溯
            System.out.println(String.format("当前节点为:%s,剩余sum为:%s", "空节点", sum));
            return false; // 节点没有了依旧没找到满足条件
        }
        System.out.println(String.format("当前节点为:%s,剩余sum为:%s", root.val, sum - root.val));

        // 找到目标,到了叶子节点, 路径也刚好减为0
        if (root.left == null && root.right == null && sum - root.val == 0) {
            System.out.println("找到了目标叶子节点:" + root.val);
            return true;
        }
        // 深度优先遍历的方式,根->左->右  查找顺序 0 1 3 4 2 5 6
        // 分别从左子树和右子树来找(深度优先遍历),只要有一边找到即可
        return hasSumPath(root.left, sum - root.val) || hasSumPath(root.right, sum - root.val);
    }

    /**
     * 通过减法计算是否存在满足的路径
     *
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasSumPath2(TreeNode root, int sum) {
        // 通过深度优先遍历(根左右), 它会穷尽所有的节点
        if (root == null) { // 到了叶子下面的空节点还未找到,返回false,继续从其他节点找
            return false;
        }

        // 当找到一个叶子节点,并且结果最终路径减为0,说明存在这个路径
        if (root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }

        return hasSumPath2(root.left, sum - root.val) || hasSumPath2(root.right, sum - root.val);
    }

    /**
     * 使用双栈结构, 一个栈用于dfs遍历,一个栈用于叠加距离
     *
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSumByStack(TreeNode root, int sum) {
        //空节点找不到路径
        if (root == null)
            return false;
        //栈辅助深度优先遍历
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        //跟随s1记录到相应节点为止的路径和
        Stack<Integer> s2 = new Stack<Integer>();
        s1.push(root);
        s2.push(root.val);
        while (!s1.isEmpty()) {
            //弹出相应节点
            TreeNode temp = s1.pop();
            System.out.println("弹出节点:" + temp.val);
            //弹出到该点为止的当前路径和
            int cur_sum = s2.pop();
            //叶子节点且当前路径和等于sum
            if (temp.left == null && temp.right == null && cur_sum == sum) {
                System.out.println("找到目标节点为" + temp.val);
                return true;
            }

            // 先右再左节点入栈  也是DFS的做法, 根->左->右
            //右节点及路径和入栈
            if (temp.right != null) {
                s1.push(temp.right);
                s2.push(cur_sum + temp.right.val);
            }

            //左节点及路径和入栈
            if (temp.left != null) {
                s1.push(temp.left);
                s2.push(cur_sum + temp.left.val);
            }
        }
        return false;
    }

    public static boolean hasPathSumByStack2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackValue = new Stack<>();

        stackNode.push(root);
        stackValue.push(root.val);
        while (!stackNode.isEmpty()) {
            // 深度优先遍历, 根 左 右

            // 弹出节点
            TreeNode node = stackNode.pop();
            Integer currentPathSum = stackValue.pop();
            System.out.println(String.format("当前节点为%s,路径和为%s", node.val, currentPathSum));
            // 当前节点的值 + 之前节点的路径值 等于 总路径
            // 目标节点必须是叶子节点
            if (node.left == null && node.right == null && node.val + currentPathSum == sum) {
                System.out.println("找到了目标叶子节点" + node.val);
                return true;
            }

            // 把每个节点和的左右子节点之和记录下来
            if (node.right != null) {
                stackNode.push(node.right);
                stackValue.push(node.right.val + currentPathSum); // 放入下一个节点的值 + 之前路径和
            }

            if (node.left != null) {
                stackNode.push(node.left);
                stackValue.push(node.left.val + currentPathSum);
            }
        }
        return false;
    }
}
