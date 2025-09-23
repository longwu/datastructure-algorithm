package com.study.tree.binarytree;


import com.study.utils.TreeUtils;

import java.util.*;

/**
 * 判断是否为完全二叉树
 */
public class CompletedTree {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, null, 7};
        TreeNode root = TreeUtils.buildTree(arr);
        boolean isCompleted = isCompleteTree(root);
        System.out.println(isCompleted);
    }

    /**
     * 是否为完全二叉树,完全二叉树通过层级遍历,通过一个boolean值标记是否遇到了空节点,如果遇到了,下次再遇到非空即为非完全二叉树
     *
     * @param root
     * @return
     */
    private static boolean isCompletedTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>(); // 需要使用链表来存,如果是数组存空元素会报NPE异常
        queue.add(root);

        boolean hasEmptyNode = false;
        while (!queue.isEmpty()) {

            // 层级遍历二叉树, 找出过程中是否有空值
            int rowCount = queue.size();
            for (int i = 0; i < rowCount; i++) { // 使用for循环是为了知道每层有多少个节点
                TreeNode node = queue.remove();
                if (node == null || node.val == null) {
                    hasEmptyNode = true;
                    System.out.println("出现空节点");
                    continue;
                }
                // 说明空节点之后遇到了非空节点
                if (hasEmptyNode) {
                    System.out.println("后面出现非空节点,node:" + node.val);
                    return false;
                }
                System.out.println("节点:" + node.val);
                // 因为要判断是否有空节点,所以不能把空节点给过滤掉
                //if(node.left != null){
                queue.offer(node.left);
                //}
                //if(node.right !=null){
                queue.add(node.right);
                //}
            }
        }
        return true;
    }

    public static boolean isCompleteTree(TreeNode root) {
        //空树一定是完全二叉树
        if (root == null)
            return true;
        //辅助队列
        Queue<TreeNode> queue = new ArrayDeque<>(); // 数组队列不允许有空值出现
        queue.offer(root);
        //定义一个首次出现的标记位
        boolean notComplete = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node.val == -1) {
                notComplete = true;
                continue;
            }

            if (notComplete) {
                return false;
            }

            // 如果队列不允许空元素,可以使用一些特殊值代替
            queue.offer(node.left != null ? node.left : new TreeNode(-1));
            queue.offer(node.right != null ? node.right : new TreeNode(-1));
        }
        return true;
    }
}
