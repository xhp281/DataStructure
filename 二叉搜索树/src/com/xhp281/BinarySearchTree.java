package com.xhp281;
import com.xhp281.printer.BinaryTreeInfo;
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
     * 删除
     * @param element
     */
    public void remove(E element){

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
}
