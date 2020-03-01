package com.study.tree.binarytree;


import java.util.*;

/**
 * 有一个数组, 存着二叉树所有不为空的叶子节点, 通过该数组构建整个二叉树
 * <p>
 * array = [-9,8,3,-4,1]
 * <p>
 * int v1 = hash(-9,8);
 * v2 = hash(3,-4)
 * v3 = hash(v1,v2)
 * v4 = hash(1,0)
 * v5 = hash(v4,0)
 * <p>
 * root
 * v3                        v5
 * v1            v2           v4           0
 * -9     8      3    -4      1     0      0      0
 */
public class ArrayToTree {

    public static void main(String[] args) {
        int[] arr = {-9, 8, 3, -4, 1};

        buildTree(arr, 4);
    }

    /**
     * 构建二叉树
     */
    private static void buildTree(int[] arr, int level) {
        // 记算叶子节点总结点数
        int fullNodes = 1;
        int i = 1;
        while (i < level) {
            fullNodes *= 2;
            i++;
        }

        List<List<TreeNode>> allNodes = new ArrayList<>();

        List<TreeNode> knifeNodes = new ArrayList<>();
        // 将叶子节点放入集合中
        for (int a = 0; a < arr.length; a++) {
            knifeNodes.add(new TreeNode(arr[a]));
        }

        i = arr.length;
        // 将叶子节点补全
        while (i < fullNodes) {
            knifeNodes.add(new TreeNode(0));
            i++;
        }

        allNodes.add(knifeNodes);

        // 构建叶子节点以上所有的层节点, 并把他们加入到集合中
        int currentLevel = 0;
        while (currentLevel < level - 1) {
            List<TreeNode> parentNodes = new ArrayList<>();
            // 构造父节点
            for (i = 0; i < allNodes.get(currentLevel).size(); i += 2) {
                parentNodes.add(getParentNode(allNodes.get(currentLevel).get(i), allNodes.get(currentLevel).get(i + 1)));
            }
            allNodes.add(parentNodes);

            currentLevel++;
        }

        TreeNode root = allNodes.get(currentLevel).get(0);
        System.out.println(root.val);

        // 通过根节点遍历树
        List<List<Integer>> lists =  levelOrder(root);
        for (List<Integer> nodesInALevel : lists) {
            System.out.println();
            for (Integer node : nodesInALevel) {
                System.out.print(node + " ");
            }
        }
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        // 创建一个2维的节点集合， 第1维度为层集合,第2维度为每层的节点集合
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        //如果根节点为空，直接返回空列表
        if (root == null) return levels;

        // 创建一个当前层的队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 往节点结合中添加当前层级的空节点集合
            List<Integer> currentLevelNodes =  new ArrayList<>();
            // 获取当前层次的节点个数
            int level_length = queue.size();
            // 遍历当前层的节点个数
            for (int i = 0; i < level_length; i++) {
                TreeNode node = queue.remove();//取出并移除队列的第一个节点 根据先进先出原则, 取出的顺序为 根 左 右(根据添加时候的顺序一致)

                // 往节点集合中添加当前层级的节点
                currentLevelNodes.add(node.val);

                // 往当前层队列中添加当前层的子节点,用于下一次迭代处理
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            levels.add(currentLevelNodes);
        }
        return levels;
    }

    private static TreeNode getParentNode(TreeNode left, TreeNode right) {
        int parentValue = left.val + right.val;
        TreeNode parentNode = new TreeNode(parentValue);
        parentNode.left = left;
        parentNode.right = right;
        return parentNode;
    }
}
