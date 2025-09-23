package com.study.tree.binarytree;


import com.alibaba.fastjson2.JSON;
import com.study.utils.ArrayUtils;
import com.study.utils.TreeUtils;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.util.*;


/**
 * 二叉树遍历2
 * <p>
 * 将一个二叉树各节点输出, 奇数行按顺序输出,偶数行倒叙输出
 * input:
 * A
 * /   \
 * B     C
 * /   \    \
 * D     E    F
 * /      \
 * G         H
 * output:
 * A
 * CB
 * DEF
 * HG
 */
public class LevelTraversal2 {

    public static void main(String[] args) {
//        String[] nodes = {"A", "B", "C", "D", "E", null, "F", null, null, "G", null, null, null, null, "H"};
//        TreeNode2 root = TreeUtils.buildTree(nodes);
//        TreeUtils.show(root);
//        List<List<String>> list = getTreeList(root);
//        print(list);

        Integer[] nodes2 =  {1, 2, 3, 4, 5, 6, null, 7, 8 ,9 , 10};
        TreeNode root2 = TreeUtils.buildTree(nodes2);

        // 层级正向遍历，如果是偶数行，将偶数行的数组反向遍历一下放回去
        List<List<Integer>> resultList = traversal(root2);

        ArrayUtils.print(resultList);

        // 使用两个栈，利用栈先进后出的原理，不停的反转，奇数层一个栈，偶数层一个栈
        List<List<Integer>> resultList2 =  traversalByTwoStack(root2);
        //ArrayUtils.print(resultList2);
        System.out.println(JSON.toJSON(resultList2));
    }

    /**
     * 使用两个栈配合使用,利用栈先进后出的原理，不停的反转，奇数层一个栈，偶数层一个栈
     */
    private static List<List<Integer>> traversalByTwoStack(TreeNode root){
        List<List<Integer>> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }

        Stack<TreeNode> s1 = new Stack<>(); // 奇数层
        Stack<TreeNode> s2 = new Stack<>(); // 偶数层
        // 奇数层先放入根节点
        s1.push(root);

        while(!s1.isEmpty() || !s2.isEmpty()){
            List<Integer> rows = new ArrayList<>();

            // 正序输出
            while (!s1.isEmpty()){
                // 反向弹出
                TreeNode node = s1.pop();
                rows.add(node.val);
                System.out.println("结果集中加入了:"+node.val);

                // 将节点正向加入到s2的栈中, 用于下一轮s2反向输出
                if(node.left!=null){
                    s2.push(node.left);
                    System.out.println("s2放了:"+node.left.val);
                }
                if(node.right!=null){
                    s2.push(node.right);
                    System.out.println("s2放了:"+node.right.val);
                }
            }
            if (!rows.isEmpty()) { // 避免空集合被加入
                resultList.add(new ArrayList<>(rows));
                rows.clear(); // 避免污染下一层结果数据
            }

            // 反序输出
            while(!s2.isEmpty()){
                // 反向弹出
                TreeNode node = s2.pop();
                rows.add(node.val);
                System.out.println("结果集中加入了:"+node.val);

                // 将节点反向加入s1的栈中, 用于下一轮s1正向输出
                if(node.right!=null){
                    s1.push(node.right);
                    System.out.println("s1放了:"+node.right.val);
                }
                if(node.left!=null){
                    s1.push(node.left);
                    System.out.println("s1放了:"+node.left.val);
                }
            }
            if (!rows.isEmpty()) {
                resultList.add(rows);
            }
        }
        return resultList;
    }
    /**
     * 将二叉树遍历后放入二维集合中，一维为层数，一维为每层的节点
     *
     * @param root
     * @return
     */
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

    /**
     * 按奇数层从左往右，偶数层从右往左的方式打印
     *
     * @param list
     */
    private static void print(List<List<String>> list) {
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


    /**
     * 奇数行按顺序输出,偶数行倒叙输出
     * 通过队列遍历 + 偶数层结果集合反转的方式遍历二叉树
     */
    private static List<List<Integer>> traversal(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        // 是否反转, 1不转，2转，3不转，4转
        boolean reverse = false;
        Queue<TreeNode> queue = new ArrayDeque<>(); // ArrayDeque有序队列数组
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> rows = new ArrayList<>();

            int rowCount = queue.size();
            for (int i = 0; i < rowCount; i++) {
                TreeNode node = queue.remove();
                rows.add(node.val);

                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            if(reverse){
                // 如果需要反转，将当前层集合中的节点进行翻转
                rows = reverse(rows);
            }

            resultList.add(rows);
            // 通过反向赋值控制是否反转
            reverse = !reverse;
        }
        return resultList;
    }

    /**
     * 集合反转
     */
    private static List<Integer> reverse(List<Integer> list){
        List<Integer> tempList = new ArrayList<>();
        for(int i = list.size()-1; i >=0 ; i--){
            tempList.add(list.get(i));
        }
        return tempList;
    }
}
