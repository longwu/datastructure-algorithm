package com.study.trietree;

/**
 * 基本性质
 * 根节点不包含字符，除根节点外的每一个子节点都包含一个字符
 * 从根节点到某一节点。路径上经过的字符连接起来，就是该节点对应的字符串
 * 每个节点的所有子节点包含的字符都不相同
 * <p>
 * 应用场景
 * 典型应用是用于统计，排序和保存大量的字符串(不仅限于字符串)，经常被搜索引擎系统用于文本词频统计。
 * <p>
 * 优点
 * 利用字符串的公共前缀来减少查询时间，最大限度的减少无谓的字符串比较，查询效率比哈希树高。
 * <p>
 * https://www.cnblogs.com/xujian2014/p/5614724.html
 */
public class Trie {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
//        String[] strs = {"banana", "band", "bee", "absolute", "acm",};
//        String[] prefix = {"ba", "b", "band", "abc",};
        String[] strs = {"北京大学", "北京科技大学", "南京北京路", "南京大学", "南京金陵十二拆",};
        String[] prefix = {"北", "北京", "北京大学", "南京", "上海浦东新区",};
        for (String str : strs) {
            insert(str, root);
        }
        //System.out.println(tree.has("abc"));
        System.out.println(has("北京", root));
        preTraverse(root);
        System.out.println();
        //tree.printAllWords();
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
        char[] letters = str.toCharArray();//将目标单词转换为字符数组
        for (int i = 0, len = str.length(); i < len; i++) {
            //计算每个char的位置
            int pos = letters[i] - 'a';
            if (node.son[pos] == null)  //如果当前节点的儿子节点中没有该字符，则构建一个TrieNode并复值该字符
            {
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            } else   //如果已经存在，则将由根至该儿子节点组成的字符串模式出现的次数+1
            {
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    /**
     * 计算单词前缀的数量
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
     * 打印指定前缀的单词
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
     * 遍历经过此节点的单词.
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
    public static boolean has(String str, TrieNode root) {
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
     * 前序遍历字典树.
     * <p>
     * 根 - 左 - 右
     *
     * @param root
     */
    public static void preTraverse(TrieNode root) {
        if (root != null) {
            System.out.print(root.val + "-");
            for (TrieNode child : root.son) {
                preTraverse(child);
            }
        }
    }
}