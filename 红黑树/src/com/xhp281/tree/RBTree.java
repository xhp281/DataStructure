package com.xhp281.tree;
import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-05-28 16:48
 * Description: 红黑树
 */

public class RBTree<E> extends BST<E> {
    // 红黑树定义
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    /**
     * 初始化方法
     */
    public RBTree(){
        this(null);
    }
    public RBTree(Comparator<E> comparator){
        super(comparator);
    }


    /**
     * 添加之后的操作
     * @param node
     */
    @Override
    protected void addAfterFixNode(Node<E> node) {
    }

    /**
     * 删除之后的操作
     * @param node
     */
    @Override
    protected void removeAfterFixNode(Node<E> node) {
    }

    //=================================== 功能方法

    /* 节点染色 */
    private Node<E> color(Node<E> node,boolean color){
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }
    private Node<E> red(Node<E>node){
        return color(node,RED);
    }
    private Node<E> black(Node<E>node){
        return color(node,BLACK);
    }

    /* 获取节点颜色 */
    private boolean colorOf(Node<E>node){
        return node == null ? BLACK : ((RBNode<E>)node).color == RED;
    }
    private boolean isRed(Node<E>node){
        return colorOf(node) == RED;
    }
    private boolean isBlack(Node<E>node){
        return colorOf(node) == BLACK;
    }

    /* AVL节点模型 */
    private static class RBNode<E> extends Node<E> {
        boolean color;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
