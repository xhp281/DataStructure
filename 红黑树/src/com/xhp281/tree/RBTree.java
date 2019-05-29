package com.xhp281.tree;
import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-05-28 16:48
 * Description: 红黑树
 */

public class RBTree<E> extends BBST<E> {
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
        Node<E> parent = node.parent;

        // 添加的是根节点
        if (parent == null){
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = parent.parent;

        // 叔父节点是红色
        // parent染成黑色，grand染成红色
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点,进行递归调用，更新祖父节点之上的节点
            addAfterFixNode(red(grand));
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()){   // L
            red(grand);
            if (node.isLeftChild()){
                // LL parent 染成 BLACK，grand 染成 RED
                black(parent);
            }else{
                // LR 自己染成 BLACK，grand 染成 RED；parent 左旋转， grand 右旋转
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else{ // R

            red(grand);
            if (node.isLeftChild()){
                // RL 自己染成 BLACK，grand 染成 RED；parent 右旋转， grand 左旋转
                black(node);
                rotateRight(parent);
            }else{
                // RR parent 染成 BLACK，grand 染成 RED
                black(parent);
            }
            rotateLeft(grand);
        }
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
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }
    private boolean isRed(Node<E>node){
        return colorOf(node) == RED;
    }
    private boolean isBlack(Node<E>node){
        return colorOf(node) == BLACK;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    /* AVL节点模型 */
    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED){
                str = "R_";
            }
            return str + element.toString();
        }
    }

}
