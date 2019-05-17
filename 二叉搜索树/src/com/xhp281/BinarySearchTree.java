package com.xhp281;

import com.xhp281.printer.BinaryTreeInfo;

import java.util.Comparator;

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

    /**
     * 二叉树打印接口设置
     * @return
     */
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
        return ((Node<E>)node).element;
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

}
