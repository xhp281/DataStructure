package com.xhp281.map;

import com.xhp281.printer.BinaryTreeInfo;
import com.xhp281.printer.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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

        // 根据key获取下标
        int index = index(key);
        // 取出下标对应的节点
        Node<K,V> root = table[index];
        if (root == null){
            root = new Node<>(key,value,null);
            table[index] = root;
            size++;
            // 添加之后调整
            afterPut(root);
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<K,V> parent = root;
        Node<K,V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K,V> result = null;
        boolean searched = false;
        do {
//            cmp = compare(key, node.key, h1, node.hashCode());
            parent = node;
            K   k2 = node.key;
            int h2 = node.hash;

            if (h1 > h2){
                cmp = 1;
            }else if (h1 < h2){
                cmp = -1;
            }else if(Objects.equals(k1,k2)){
//                已经存在相同的值,直接覆盖
                cmp = 0;
            }else if (k1 != null && k2 != null
                     && k1.getClass() == k2.getClass()
                     && k1 instanceof Comparable
                     && ((cmp = ((Comparable) k1).compareTo(k2) )!= 0)){
                // k1 k2 不为空，且类型相同，哈希值相等，可以进行比较
                // compareTo 相等不认为是相等
//              cmp = ((Comparable) k1).compareTo(k2);
            }else if (searched){
                // search == true，使用地址值比较过了，直接用地址比较
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);

                }else{// 没有使用过地址值比较

                // 哈希值相等，不具备可比较性
                if (node.left != null && (result = node(node.left,k1)) != null
                        || node.right != null && (result = node(node.right,k1)) != null){
                    // 已经存在key,直接覆盖
                    cmp = 0;
                    node = result;
                }else{
                    // 不存在key
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                    searched = true;
                }
                }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K,V> newNode = new Node<>(key,value,parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K,V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size == 0) return false;

        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]); // 入队
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if (Objects.equals(value,node.value)) return true;

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) return;

        Queue<Node<K,V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]); // 入队
            while (!queue.isEmpty()){
                Node<K,V> node = queue.poll();
                if (visitor.visit(node.key,node.value)) return;;

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
    }

    /* 根据key找到节点 */
    private Node<K,V> node(K key){
        Node<K,V> root = table[index(key)];
        return root == null ? null : node(root,key);
    }

    /* 根据 root 和 key 查找 node */
    private Node<K,V> node(Node<K,V> node,K k1){
        int h1 = k1 == null ? 0 : k1.hashCode();
        // 存储查找结果
        Node<K,V> result = null;

        while (node != null){
            K k2   = node.key;
            int h2 = node.hash;
            int cmp = 0;
            // 先比较哈希值
            if (h1 > h2){
                node = node.right;
            }else if (h1 < h2){
                node = node.left;
            }else if (Objects.equals(k1, k2)) { // 哈希值相等的时候判断内容
                return node;
            }else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && ((cmp = ((Comparable) k1).compareTo(k2)) != 0)){
                // k1 k2 不为空，且类型相同，哈希值相等，可以进行比较, compareTo相等的时候不算等于（有可能不同对象hash值相等）
                node = cmp > 0 ? node.right : node.left;

                // 哈希值相等，不具备可比较性
            }else if (node.right != null && (result = node(node.right,k1)) != null){
                return result;
            }else{
                node = node.left;
            }
        }
        return null;
    }

    /* 根据key生成对应的下标值 */
    private int index(K key){
        if (key == null) return 0;
        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);
        return hash & (table.length - 1);
    }
    private int index(Node<K,V>node){
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

//    /**
//     * @return
//     * 返回值等于0，代表e1和e2相等；
//     * 返回值大于0，代表k1大于k2；
//     * 返回值小于于0，代表k1小于k2
//     */
//    private int compare(K k1, K k2, int h1, int h2) {
//
//        // 比较哈希值
//        int result = h1 - h2;
//        if (result != 0) return result;
//
//        // 哈希值相等，value相等
//        if (Objects.equals(k1,k2)) return 0;
//
//        // 哈希值相等，value不equals
//        // 比较类名
//        if (k1 != null && k2 != null){
//            String k1Clas = k1.getClass().getName();
//            String k2Clas = k2.getClass().getName();
//            // 类进行比较
//            result = k1Clas.compareTo(k2Clas);
//            // 不相等直接返回比较结果
//            if (result != 0) return result;
//            // 是同一种类型 并且具备可比较性
//            if (k1 instanceof Comparable){
//                return ((Comparable) k1).compareTo(k2);
//            }
//        }
//
//        // 是同一种类型，哈希值一样，但是不具备可比较性
//        // k1 != null k2 == null
//        // k1 == null k2 != null
//
//        // 比较内存地址的hashCode
//        return System.identityHashCode(k1) - System.identityHashCode(k2);
//    }

    //   私有方法
    private boolean valEquals(V v1,V v2){
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    /* 打印红黑树 */
    public void print(){
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            final  Node<K,V> root = table[i];
            System.out.println("【index=" + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K,V>)node).right;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K,V>)node).left;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("--------------------------------------------------------");
        }
    }

    /* 删除 */
    private V remove(Node<K,V> node){
        if (node == null) return null;

        V oldValue = node.value;
        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K,V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key    = s.key;
            node.value  = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K,V> replacement = node.left != null ? node.left : node.right;
        int index = index(node.key);

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;

            // 删除节点之后的处理
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
        return oldValue;
    }

    protected Node<K,V> predecessor(Node<K,V> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<K,V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    protected Node<K,V> successor(Node<K,V> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K,V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    protected void afterRemove(Node<K,V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K,V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K,V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    private void afterPut(Node<K,V>node){
        Node<K,V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<K,V> uncle = parent.sibling();
        // 祖父节点
        Node<K,V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

// 红黑树操作
    private Node<K,V> color(Node<K,V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    private Node<K,V> red(Node<K,V> node) {
        return color(node, RED);
    }

    private Node<K,V> black(Node<K,V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K,V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K,V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K,V> node) {
        return colorOf(node) == RED;
    }

    protected void rotateLeft(Node<K,V> grand) {
        Node<K,V> parent = grand.right;
        Node<K,V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<K,V> grand) {
        Node<K,V> parent = grand.left;
        Node<K,V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /* 节点 */
    private static class Node<K,V>{
        int hash;
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        boolean color = RED;

        public Node(K key, V value, Node<K,V> parent) {
            this.key    = key;
            this.value  = value;
            this.hash   = key == null ? 0 : key.hashCode();
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
        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }
}
