package com.study.tree.trietree;

/**
 * 字典树 也叫 trie树
 * <p>
 * 基本性质
 * 1.根节点不包含字符，除根节点外的每一个子节点都包含一个字符
 * 2.从根节点到某一节点。路径上经过的字符连接起来，就是该节点对应的字符串
 * 3.每个节点的所有子节点包含的字符都不相同
 * <p>
 * 应用场景
 * 典型应用是用于统计，排序和保存大量的字符串(不仅限于字符串)，经常被搜索引擎系统用于文本词频统计。
 * <p>
 * 优点
 * 利用字符串的公共前缀来减少查询时间，最大限度的减少无谓的字符串比较，查询效率比哈希树高。
 * <p>
 * 遍历算法: bfs深度优先遍历 先根再子节点
 *
 * <p>
 * https://www.cnblogs.com/xujian2014/p/5614724.html
 * <p>
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Trie {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        String[] strs = {"banana", "band", "bee", "absolute", "acm",};
        String[] prefix = {"ba", "b", "band", "abc",};
//        String[] strs = {"北京大学", "北京科技大学", "南京北京路", "南京大学", "南京金陵十二拆",};
//        String[] prefix = {"北", "北京", "北京大学", "南京", "上海浦东新区",};
        for (String str : strs) {
            insert(str, root);
        }

        System.out.println("------打印字典树中所有单词--------");
        printAllWords(root, "#");
        System.out.println("--------------------------------");

        // 是否包含这个单词
        System.out.println("------判断是否有ba这个单词--------");
        System.out.println(containsWord("ba", root));
        //System.out.println(containsWord("北京", root));
        System.out.println("--------------------------------");

        // 打印包含该前缀的单词
        System.out.println("-------打印所有包含ba前缀的单词----");
        System.out.println(hasPrefix("ba", root));
        System.out.println("--------------------------------");

        //preTraverse(root);
        preTraverse2(root);
        System.out.println();
        for (String pre : prefix) {
            int num = countPrefix(pre, root);
            System.out.println(pre + " 数量:" + num);
        }
    }

    /**
     * 在字典树中插入一个单词
     *
     * @param str
     */
    public static void insert(String str, TrieNode root) {
        if (str == null || str.length() == 0) {
            return;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();//将目标单词转换为char数组
        for (int i = 0, len = str.length(); i < len; i++) {
            //计算每个char的位置(哈希值), 这样在当前层级查找该元素或节点的的时间复杂度为O(1)
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {  //如果当前节点的儿子节点中没有该字符，则构建一个TrieNode并复值该字符
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            } else {  //如果已经存在，则将由根至该儿子节点组成的字符串模式出现的次数+1
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    /**
     * 计算拥有该前缀的单词
     *
     * @param prefix
     * @return
     */
    public static int countPrefix(String prefix, TrieNode root) {
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {
                return 0;
            } else {
                node = node.son[pos];
            }
        }
        return node.num;
    }

    /**
     * 找出并打印出拥有指定前缀的所有单词
     *
     * @param prefix
     * @return
     */
    public static String hasPrefix(String prefix, TrieNode root) {
        if (prefix == null || prefix.length() == 0) {
            return null;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null) {
                return null;
            } else {
                node = node.son[pos];
            }
        }
        preTraverse(node, prefix);
        return null;
    }

    /**
     * 打印经过此节点的所有单词.
     *
     * @param root
     * @param prefix
     */
    public static void preTraverse(TrieNode root, String prefix) {
        if (!root.isEnd) {
            for (TrieNode child : root.son) {
                if (child != null) {
                    preTraverse(child, prefix + child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }

    /**
     * 在字典树中查找一个完全匹配的单词
     *
     * @param str
     * @return
     */
    public static boolean containsWord(String str, TrieNode root) {
        if (str == null || str.length() == 0) {
            return false;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - 'a';
            if (node.son[pos] != null) {
                node = node.son[pos];
            } else {
                return false;
            }
        }
        //走到这一步，表明可能完全匹配，可能部分匹配，如果最后一个字符节点为末端节点，则是完全匹配，否则是部分匹配
        return node.isEnd;
    }

    /**
     * 打印字典树里面所有字符char
     * <p>
     * 前序遍历字典树. bfs深度优先遍历
     *
     * <p>
     * 根 - 所有子节点
     *
     * @param root
     */
    public static void preTraverse(TrieNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + ":" + root.num + " ");
        for (TrieNode child : root.son) {
            preTraverse(child);
        }
    }

    /**
     * 打印字典树里面所有字符char
     *
     * @param root
     */
    public static void preTraverse2(TrieNode root) {
        if (root == null) {
            return;
        }
        for (TrieNode child : root.son) {
            if (child != null) {
                System.out.print(child.val + ":" + child.num + " ");
                if (child.isEnd)
                    System.out.println();
            }
            preTraverse2(child);
        }
    }

    /**
     * 打印字典树里所有的单词
     * 将递归走的每个char进行拼接,直到单词尾部,然后输出这个单词
     *
     * @param root   根节点
     * @param prefix 前缀
     */
    private static void printAllWords(TrieNode root, String prefix) {
        if (root != null && root.isEnd) {
            System.out.println(prefix);
            return;
        }
        if (root == null)
            return;
        for (TrieNode child : root.son) {
            if (child != null)
                printAllWords(child, prefix + child.val);
        }
    }
}