package com.study.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU least Recently Used 最近最少使用, 是一种淘汰策略,选择最久未使用的元素进行淘汰
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * LRUCache cache = new LRUCache(2); //2为缓存容量
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
public class LRUCacheClient {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); //2为缓存容量
        // 元素的热程度为最新放的最热, 下面是2最热
        cache.put(1, 1);
        cache.put(2, 2);
        // 获取key为1的值, 获取后 key为1的节点由于会到链表的头部,变成最热的节点(最不容易不被淘汰的节点)
        System.out.println("cache.get(1): " + cache.get(1));       // 返回  1
        // 之后再将一个key为3的元素放入缓存中, 放完之后3的热度比1高
        // 由于缓存只能放2个元素,所以
        cache.put(3, 3);    // 由于key为2的节点没有被访问过, 所以该操作会使得key为2的元素 作废
        System.out.println("cache.get(2): " + cache.get(2));       // 由于key为2的元素被淘汰了,所以返回-1  // 返回 -1
        // 再放入一个key为4的元素, 放完之后由于空间限制只能保留2个元素,所以将最不热的1给淘汰了
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }
}

/**
 * 方法一:使用LinkedHashMap实现, LinkedHashMap是一个有序的hashmap, 可以对内部的节点进行排序
 * 在调用put和get方法会执行afterNodeInsertion()和afterNodeAccess()方法, 将当前操作的节点放到链表的头部,成为最热的节点(最不容易被删除的节点),
 * 同时移除最老的元素(链表尾部的元素),一旦容量不够的情况下
 * <p>
 * 时间复杂度：对于 put 和 get 都是 O(1)O(1)。
 * 空间复杂度：O(capacity)O(capacity)，因为哈希表和双向链表最多存储 capacity + 1 个元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/lru-cache/solution/lru-huan-cun-ji-zhi-by-leetcode/
 */
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 获取key的值,如果没有返回-1, 访问后,该key的节点会被放到头部,变成最热的节点(最不容易被删除的节点)
     *
     * @param key
     * @return
     */
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    /**
     * 插入键值对
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        super.put(key, value);
    }

    /**
     * 移除最老元素的策略是容量不够
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > this.capacity;
    }
}


class LRUCache2{
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    /**
     * 添加新节点, 将新节点放到链表头部
     * @param node
     */
    private void addNode(DLinkedNode node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * 删除一个节点
     *
     * @param node
     */
    private void removeNode(DLinkedNode node){
        /**
         * Remove an existing node from the linked list.
         */
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * 将当前节点放到链表头部
     *
     * @param node
     */
    private void moveToHead(DLinkedNode node){
        /**
         * Move certain node in between to the head.
         */
        removeNode(node);
        addNode(node);
    }

    /**
     * 弹出尾部节点
     * @return
     */
    private DLinkedNode popTail() {
        /**
         * Pop the current tail.
         */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        // head.prev = null;

        tail = new DLinkedNode();
        // tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // 将当前节点放到链表头部
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if(node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            ++size;

            if(size > capacity) {
                // 从链表中删除尾部节点,同时从hashmap中删除
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            // 更新节点 也会将节点放到链表头部
            moveToHead(node);
        }
    }
}