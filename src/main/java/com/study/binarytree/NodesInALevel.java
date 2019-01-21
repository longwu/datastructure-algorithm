package com.study.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 求第k层有多少个节点, 根节点为第0层
 * <p>
 * 我们知道: 一个满二叉树的第k层节点个数n为: n = 2 ^ k  (根节点为第0层)
 * <p>
 * 而第k层的节点数与第(k - 1)层的节点数比例为 2 : 1
 * <p>
 * https://www.cnblogs.com/hapjin/p/5505988.html
 */
public class NodesInALevel {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        List<TreeNode> datas = new ArrayList<TreeNode>();
        for (int data : arr) {
            datas.add(new TreeNode(data));
        }
//
//        TreeNode root = datas.get(0);
//        //创建其他节点  每个节点的左子节点都是这个节点的2倍+1, 右子节点都是这个节点的2倍+2
//        for (int i = 0; i < arr.length / 2; i++) {
//            datas.get(i).left = datas.get(i * 2 + 1);
//            if (i * 2 + 2 < datas.size()) {
//                datas.get(i).right = datas.get(i * 2 + 2);
//            }
//        }

        TreeNode root = datas.get(0);
        root.left = datas.get(1);
        root.right = datas.get(2);
        root.left.left = datas.get(3);
        root.left.right = datas.get(4);
//        root.right.left = datas.get(5);
//        root.right.right = datas.get(6);
        root.left.left.left = datas.get(7);

        int k = 2;
        System.out.printf("第%d层节点数:%d\r\n", k, k_nodes(root, k));
        System.out.println("分别为:");
        List<TreeNode> nodes = k_nodes2(root, k);
        for (TreeNode node : nodes) {
            System.out.println(node.val);
        }
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
     *
     * @param root
     * @param k
     * @return
     */
    private static int k_nodes(TreeNode root, int k) {
        if (k < 0) {
            return 0;
        }
        // 当root为空或者 递归到root为空, 说明到第k层或到k层之前就已经没有节点了
        if (root == null) {
            //System.out.println(0);
            return 0;
        }
        // 当k ==0 或者 递归到 k==0, 说明k层有节点
        if (k == 0) {
            // 到达第k层节点数, 返回多少次1, 说明有多少个节点到达了第k层,(即第k层有多少个节点)
            //System.out.println(1);
            return 1;
        } else {
            // 递归一次 最多得到 1+1==2个节点, 递归两次 2+2 == 4个节点, 最终递归 k-1词 最多得到 2 ^ (k-1)个数节点
            // 如果中途有空节点, 那么只能算没有空节点的节点个数, 即递归到k==0时候的那些情况节点, 求1出现次数之和即是第k层节点数
            // 从最后一层 k==0的时候开始计算, 如果k==0的时候 返回

//            int a = k_nodes(root.left, k - 1);
//            int b = k_nodes(root.right, k - 1);
//            return  a + b;
            // 先递归完左边子树的直到return返回值,然后往回走向根节点, 在走向根节点同时递归右子树
            // 每递归完一次右子树, 就将左右子树的结果进行相加. 并return给下一次递归相加
            return k_nodes(root.left, k - 1) + k_nodes(root.right, k - 1);
        }
    }

    /**
     * 返回第k层的节点集合
     *
     */
    private static List<TreeNode> k_nodes2(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (k < 0) return list;

        nodes(root, k, list);
        return list;
    }

    private static void nodes(TreeNode root, int k, List<TreeNode> list) {
        if (root == null) {
            return;
        }

        if (k == 0) {
            // 将第k层节点加入到集合中
            list.add(root);
        }

        // 递归左(右)子树, 找出k=0节点
        nodes(root.left, k - 1, list);

        // 递归右(左)子树, 找出k=0节点
        nodes(root.right, k - 1, list);
    }
}
