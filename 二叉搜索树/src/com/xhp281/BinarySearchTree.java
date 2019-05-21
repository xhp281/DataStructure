package com.xhp281;
import com.xhp281.printer.BinaryTreeInfo;
import org.w3c.dom.NodeList;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * User: FenDou
 * Date: 2019-05-17 13:53
 * Description: 二叉搜索树
 */

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    /**
     * 初始化方式
     */
    public BinarySearchTree(){
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // 节点对象
    public static class Node<E>{
        E element;
        Node<E> leftNode;
        Node<E> rightNode;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }

        /**
         * 是不是叶子节点
         * @return
         */
        public boolean isLeaf(){
            return leftNode == null && rightNode == null;
        }

        /**
         * 拥有两个节点
         * @return
         */
        public boolean hasTwoChildren(){
            return leftNode != null && rightNode != null;
        }
    }

    /**
     * 大小
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isempry(){
        return size == 0;
    }

    /**
     * 清空
     */
    public void  clear(){
    }

    /**
     * 添加
     * @param element
     */
    public void add(E element){
        checkElementIsNull(element);

        // 添加第一个节点
        if (root == null){
            root = new Node<>(element,null);
            size++;
            return;
        }

        // 父节点
        Node<E> parent = root;
        // 添加其他节点，首先找到父节点
        Node<E> node = root;
        // 比较结果
        int cmp = 0;
        while (node != null){
            // 比较大小
            cmp = compare(element,node.element);
            // 获取父节点
            parent = node;
            // 新元素 > 父节点：去右边查找
            if (cmp > 0){
                node = node.rightNode;
                // 新元素< 父节点：去左边查找
            }else if (cmp < 0){
                node = node.leftNode;
            }else{
                // 相等直接返回
                node.element = element;
                return;
            }
        }
        // 判断是设置为左子树还是右子树
        // 获取新节点
        Node<E> newNode = new Node<>(element,parent);
        if (cmp > 0){ // 右
            parent.rightNode = newNode;
        }else{
            parent.leftNode  = newNode;
        }
        size++;
    }

    /**
     * 是否包含
     * @param element
     * @return
     */
    public boolean contains(E element){
        return false;
    }

    /**
     * 判断是不是二叉树
     * @return
     */
    public Boolean isComplete(){
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 是不是叶子节点
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            // 如果不是叶子节点返回false
            if (leaf && !node.isLeaf()) return false;

            if (node.hasTwoChildren()){
                queue.offer(node.leftNode);
                queue.offer(node.rightNode);
            }else if (node.leftNode == null && node.rightNode != null){
                return false;
            }else{
                // 到此处的时候是叶子节点
                leaf = true;
            }
        }
        return  true;
    }

    /**
     * 检测是不是为空
     * @param element
     */
    private void checkElementIsNull(E element){
        if (element == null) {
             throw new IllegalArgumentException("element must be not null");
        }
    }

    /**
     * 比较大小
     * @param e1
     * @param e2
     * @return 返回值等于0：e1 = e2 返回值大于0：e1 > e2 返回值小于0: e1 < e2
     */
    private int compare(E e1,E e2){
        // 有判断条件的时候使用判断条件
        if (comparator != null){
            return  comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 获取前驱节点
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E>node){
        if (node == null) return null;
        // 前驱节点在左子树中

        Node<E> p = node.leftNode;
        // 左子树不为空 node.left != null
        if (p != null){
            // 循环遍历右子树
            while (p.rightNode != null){
                p = p.rightNode;
            }
            return p;
        }

        // node.left = null && parent != null
        while (node.parent != null && node == node.parent.leftNode){
              node = node.parent;
        }

        // node.parent == null && node == node.parent.right
        return node.parent;
    }
    /**
     * 获取后驱节点
     * @param node
     * @return
     */
    public Node<E> successor(Node<E>node){
        if (node == null) return null;
        // 前驱节点在右子树中

        Node<E> p = node.rightNode;
        // 右子树不为空 node.right != null
        if (p != null){
            // 循环遍历左子树
            while (p.leftNode != null){
                p = p.leftNode;
            }
            return p;
        }

        // node.right = null && parent != null
        while (node.parent != null && node == node.parent.rightNode){
            node = node.parent;
        }

        // node.parent == null && node == node.parent.left
        return node.parent;
    }
// ================================= 删除操作
    public void remove(E element){
        remove(node(element));
    }

    /**
     * 删除节点
     * @param node
     */
    private void remove(Node<E> node){
        if (node == null) return;
        size--;
        // 度数为2的节点
        if (node.hasTwoChildren()){
            // 找到后继节点
            Node<E> sNode = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = sNode.element;
            // 删除后继节点
            node = sNode;
        }

        // 删除node节点，度为1或者0
        Node<E> replaceElement = node.leftNode != null ? node.leftNode : node.rightNode;
        // node 是度数为1的节点
        if (replaceElement != null){
            // 更改父节点
            replaceElement.parent = node.parent;
            // 更改 parent 的left,right指向
            if (node.parent == null){
                root = replaceElement;
            }else if (node == node.parent.leftNode){
                node.parent.leftNode  = replaceElement;
            }else{
                node.parent.rightNode = replaceElement;
            }
        }else if(node.parent == null){
            // node 是叶子节点并且是根节点
            root = null;
        }else{
            // node 叶子节点但不是根节点
            if (node == node.parent.leftNode){
                node.parent.leftNode  = null;
            }else{
                node.parent.rightNode = null;
            }
        }

    }

    /**
     * 根据内容获取节点
     * @param element
     * @return
     */
    private Node<E> node(E element){
        Node<E> node = root;
        while (node != null){
            int cmp = compare(element,node.element);
            // 相等的时候返回
            if (cmp == 0) return node;
            // 输入值大于当前节点，从右面找
            if (cmp > 0 ){
                node = node.rightNode;
            }else{
                // 输入值小于当前节点，从左面开始找
                node = node.leftNode;
            }
        }
        return null;
    }


// ================================= 二叉树打印接口设置
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).rightNode;
    }

    @Override
    public Object string(Object node) {
        // 获取父节点内容
        Node<E> currNode =  (Node<E>)node;
        String parentString = "null";
        if (currNode.parent != null){
            parentString = currNode.parent.element.toString();
        }
        return currNode.element + "(" + parentString + ")";
    }

//======================================= 遍历

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node){
        if (node == null) return;

        System.out.print("_" + node.element + "_ ");
        preorderTraversal(node.leftNode);
        preorderTraversal(node.rightNode);
    }

    /**
     * 中序遍历
     * 二叉搜索树的遍历结果是可以控制升序还是降序
     * 升序：中序遍历左子树、根节点、中序遍历右子树
     * 降序：中序遍历右子树、根节点、中序遍历左子树
     */
    public void inorderTraversal(){
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node){
        if (node == null) return;

        inorderTraversal(node.leftNode);
        System.out.print("_" + node.element + "_ ");
        inorderTraversal(node.rightNode);
    }
    /**
     * 后续遍历遍历
     */
    public void postorderTraversal(){
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node){
        if (node == null) return;

        postorderTraversal(node.leftNode);
        postorderTraversal(node.rightNode);
        System.out.print("_" + node.element + "_ ");
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal(){
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.println(node.element);

            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }

//======================================= 使用访问器接口进行遍历

    /**
     * 访问器接口
     * @param <E>
     */
    public static interface Visitor<E>{
        void visit(E element);
    }

    /**
     * 层序遍历
     * @param visitor
     */
    public void levelOrder(Visitor<E> visitor){
        if (root == null|| visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }
    /**
     * 后续遍历遍历
     */
    public void postorderOrder(Visitor<E> visitor){
        postorderOrder(root,visitor);
    }

    private void postorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        postorderOrder(node.leftNode,visitor);
        postorderOrder(node.rightNode,visitor);
        visitor.visit(node.element);
    }

    /**
     * 中序遍历
     * 二叉搜索树的遍历结果是可以控制升序还是降序
     * 升序：中序遍历左子树、根节点、中序遍历右子树
     * 降序：中序遍历右子树、根节点、中序遍历左子树
     */
    public void inorderOrder(Visitor<E> visitor){
        inorderOrder(root,visitor);
    }

    private void inorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        inorderOrder(node.leftNode,visitor);
        visitor.visit(node.element);
        inorderOrder(node.rightNode,visitor);
    }

    /**
     * 前序遍历
     */
    public void preorderOrder(Visitor<E> visitor){
        preorderOrder(root,visitor);
    }

    private void preorderOrder(Node<E> node,Visitor<E> visitor){
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preorderOrder(node.leftNode,visitor);
        preorderOrder(node.rightNode,visitor);
    }

    // ================================== 打印方法

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root,sb,"");
        return sb.toString();
    }
    private void toString(Node<E> node,StringBuilder sb,String prefix){
        if (node == null) return;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.leftNode,sb,prefix + "L --> ");
        toString(node.rightNode,sb,prefix + "R --> ");
    }

// ===================================== 计算二叉树高度

    /**
     * 方式1
     * 获取树的高度，非递归方式
     * @return
     */
    public int height(){
        if (root == null) return 0;

        // 树的高度
        int height    = 0;
        // 每层的数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            // 访问的时候数量减少
            levelSize--;

            if (node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if (node.rightNode != null){
                queue.offer(node.rightNode);
            }

            // 遍历完一层，将要遍历下一层的时候获取下一层的数量
            if (levelSize == 0){
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
    /**
     * 方式2
     * @return
     */
//    public int height(){
//        return  height(root);
//    }

    /**
     * 递归方式获取高度
     * @param node
     * @return
     */
    public int height(Node<E> node){
        if (node == null) return 0;
        return  1 + Math.max(height(node.leftNode),height(node.rightNode));
    }

// ===================================== 计算二叉树高度

}
