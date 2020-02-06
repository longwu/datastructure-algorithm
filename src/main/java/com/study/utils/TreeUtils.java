package com.study.utils;

import com.study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public final class TreeUtils {

    /**
     * 构建二叉树并返回根节点,
     *
     * 使用层级遍历的方式 一层层创建
     *
     * @param nodes
     * @return 返回二叉树的根节点
     */
//    public static TreeNode buildTree(int[] nodes) {
//        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
//        for (int data : nodes) {
//            treeNodes.add(new TreeNode(data));
//        }
//        //创建一个二叉树
//        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
//        for (int i = 0; i < nodes.length / 2; i++) {
//            treeNodes.get(i).left = treeNodes.get(i * 2 + 1);
//            if (i * 2 + 2 < treeNodes.size()) {
//                treeNodes.get(i).right = treeNodes.get(i * 2 + 2);
//            }
//        }
//        return treeNodes.get(0);
//    }

    /**
     * 构建二叉树并返回所有节点
     *
     * @param nodes
     * @return 所有节点
     */
    public static List<TreeNode> buildTreeAndList(int[] nodes) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (int data : nodes) {
            treeNodes.add(new TreeNode(data));
        }
        //创建一个二叉树
        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
        for (int i = 0; i < nodes.length / 2; i++) {
            treeNodes.get(i).left = treeNodes.get(i * 2 + 1);
            if (i * 2 + 2 < treeNodes.size()) {
                treeNodes.get(i).right = treeNodes.get(i * 2 + 2);
            }
        }
        return treeNodes;
    }

    public static TreeNode buildTree(Integer[] nodes) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (Integer data : nodes) {
            treeNodes.add(new TreeNode(data));
        }
        //创建一个二叉树
        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
        for (int i = 0; i < nodes.length / 2; i++) {
            if (treeNodes.get(i * 2 + 1).val != null) {
                treeNodes.get(i).left = treeNodes.get(i * 2 + 1);
            }
            if (i * 2 + 2 < treeNodes.size()) {
                if (treeNodes.get(i * 2 + 2).val != null)
                    treeNodes.get(i).right = treeNodes.get(i * 2 + 2);
            }
        }
        return treeNodes.get(0);
    }



    // 用于获得树的层数
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    /**
     * 打印出二叉树的结构
     *
     * 版权声明：本文为CSDN博主「LenFranky」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/lenfranky/article/details/89645755
     *
     * @param root
     * @return
     */
    public static void show(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }
}
