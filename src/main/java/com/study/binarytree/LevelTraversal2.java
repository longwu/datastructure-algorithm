package com.study.binarytree;


import com.study.utils.TreeUtils;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 二叉树遍历2
 *
 * 将一个二叉树各节点输出, 奇数行按顺序输出,偶数行倒叙输出
 * input:
 *       A
 *     /   \
 *    B     C
 *  /   \    \
 * D     E    F
 *      /      \
 *    G         H
 * output:
 * A
 * CB
 * DEF
 * HG
 */
public class LevelTraversal2 {

    public static void main(String[] args) {
        String[] nodes = {"A", "B", "C", "D", "E", null, "F", null,null, "G", null, null, null, null, "H"};
        TreeNode2 root = TreeUtils.buildTree(nodes);
        TreeUtils.show(root);
        List<List<String>> list = getTreeList(root);

        int level = 1;
        for (List<String> levelList : list) {
            // 奇数行 正序输出
            if (level % 2 != 0) {
                for (int i = 0; i < levelList.size(); i++) {
                    System.out.print(levelList.get(i));
                }
            } else {
                //偶数行 倒叙输出
                for (int i = levelList.size() - 1; i >= 0; i--) {
                    System.out.print(levelList.get(i));
                }
            }
            System.out.println();
            level++;
        }
    }

    private static List<List<String>> getTreeList(TreeNode2 root) {
        List<List<String>> list = new ArrayList<>();

        Queue<TreeNode2> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<String> levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode2 node = queue.poll();
                levelList.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(levelList);
        }

        return list;
    }
}
