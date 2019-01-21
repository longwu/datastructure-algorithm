package com.study.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 求第k层有多少个节点, 根节点为第0层
 * <p>
 * 我们知道: 一个满二叉树的第k层节点个数n为: n = 2 ^ k  (根节点为第0层)
 * <p>
 * 而第k层的节点数与第(k - 1)层的节点数比例为 2 : 1
 *
 * https://www.cnblogs.com/hapjin/p/5505988.html
 */
public class NodesInALevel {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<TreeNode> datas = new ArrayList<TreeNode>();
        for (int data : arr) {
            datas.add(new TreeNode(data));
        }

        TreeNode root = datas.get(0);
        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
        for (int i = 0; i < arr.length / 2; i++) {
            datas.get(i).left = datas.get(i * 2 + 1);
            if (i * 2 + 2 < datas.size()) {
                datas.get(i).right = datas.get(i * 2 + 2);
            }
        }

        System.out.println(k_nodes(root, 3));
    }

    /**
     * 满二叉树
     * 1                     1
     * 2          3               2
     * 4    5     6     7            4
     * 8  9 10 11 12 13 14 15          8
     * <p>
     * 非满二叉树
     * 1                    1
     * 2          3               2
     * 4    5     6     7            4
     * 8  9 10 11 12                   5
     * <p>
     * 正常情况下每多一层, 节点数都是上一层的两倍, 相当于 两个 上一层节点数相加  即  N(k) =  N(k-1) + N(k-1)
     * <p>
     * 当时 如果
     *
     * @param root
     * @param k
     * @return
     */
    private static int k_nodes(TreeNode root, int k) {
        if (k < 0) {
            return 0;
        }
        // 当root为空或者 递归到root为空, 说明没有节点了
        if (root == null) {
            return 0;
        }
        // 当k ==0 或者 递归到 k==0, 说明k层有节点
        if (k == 0) {
            // 说明递归已经结束, 于是从根节点开始往下每层计算, 比如 第0层(根节点)为1, 第1层(根的左右子节点)为1+1=2, 第2层为(根的左右子节点的左右子节点) 2+2=4, 依次下去
            return 1;
        } else {
            //从根节点开始往下走, 每走一层当前层的节点数 为上一层的两倍(两个上一层节点数相加)
            return k_nodes(root.left, k - 1) + k_nodes(root.right, k - 1);
        }

    }
}
