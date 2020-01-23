package com.study.trietree;

/**
 * 字典树节点
 */
public class TrieNode
{
    /**
     * 中文
     */
    //private int SIZE = 65536;

    /**
     * 英文 一共26个字母
     */
    private int SIZE = 256;

    public int num;// 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
    public TrieNode[] son = new TrieNode[SIZE];// 所有的儿子节点
    public  boolean isEnd;// 是不是最后一个节点
    public char val;// 节点的值

    public TrieNode() {
        num = 1;
        isEnd = false;
    }
}