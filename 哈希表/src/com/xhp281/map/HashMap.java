package com.xhp281.map;

/**
 * User: FenDou
 * Date: 2019-06-06 13:22
 * Description:
 */

public class HashMap<K,V> implements Map<K,V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 1<<4;

    public HashMap(){
        this(DEFAULT_CAPACITY);
    }
    public HashMap(int capacity){
        table = new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }

    /* 节点 */
    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        boolean color = RED;

        public Node(K key, V value, Node<K,V> parent) {
            this.key    = key;
            this.value  = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K,V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }
}
