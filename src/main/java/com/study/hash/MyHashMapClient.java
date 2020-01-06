package com.study.hash;

/**
 * 一个简单的hashmap, 主要用于key value存储, 查找,插入,删除的时间复杂度为O1
 */
public class MyHashMapClient {
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap<String,String>(10);
        hashMap.put("aaa","jack");
        hashMap.put("bbb","mike");

        System.out.println(hashMap.get("aaa"));// O1的时间复杂度
    }
}

/**
 * 一个简易的hashmap
 *
 * @param <K>
 * @param <V>
 */
class MyHashMap<K, V> {

    private int size;
    private Node<K, V>[] table;

    public MyHashMap(int size) {
        this.size = size;
        table = (Node<K, V>[]) new Node[size];
    }

    public void put(K key, V value) {
        int hash = hash(key);
        table[hash] = new Node<>(hash, key, value, null);
    }

    public V get(K key) {
        return table[hash(key)].getValue();
    }

    private int hash(K key) {
        int h = key.hashCode();
//        int v = h >>>16;
//        System.out.println(h ^ v);
        return h % this.size;
    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
}
