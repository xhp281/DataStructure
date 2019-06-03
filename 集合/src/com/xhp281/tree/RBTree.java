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
        Node<E> grand = red(parent.parent);

        // 叔父节点是红色
        // parent染成黑色，grand染成红色
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点,进行递归调用，更新祖父节点之上的节点
            addAfterFixNode(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()){   // L
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
    protected void removeAfterFixNode(Node<E> node,Node<E> replaceElement) {
        // 删除红色节点
        if (isRed(node)) return;

        // 用以取代的节点是红色
        if (isRed(replaceElement)){
            black(replaceElement);
            return;
        }

        // 删除的是根节点
        Node<E> parent = node.parent;
        if (parent == null) return;

        // 删除的是黑色叶子节点
        // 判断被删除的节点是左还是右
        // 父节点左边是空，证明是属于左子树
        boolean left = parent.leftNode == null || node.isLeftChild();
        // 左子树的兄弟节点取右，右子树取左
        Node<E> sibling = left ? parent.rightNode : parent.leftNode;

        // 被删除的节点在左边，兄弟节点在右边
        if (left){
            if (isRed(sibling)){ // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(sibling);

                // 更换兄弟节点
                sibling = parent.rightNode;
            }

            // 兄弟节点都是黑色
            if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)){
                // 染色之前判断父节点是不是黑色
                boolean parentIsBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentIsBlack){
                    // 递归调用处理
                    removeAfterFixNode(parent,null);
                }
            }else{
                // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                // 兄弟节点左边是黑色，兄弟要先旋转
                if (isBlack(sibling.rightNode)){
                    rotateRight(sibling);
                    sibling = parent.rightNode;
                }

                color(sibling,colorOf(parent));
                black(sibling.rightNode);
                black(parent);
                rotateLeft(parent);
            }
        }else{
            // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)){ // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(sibling);

                // 更换兄弟节点
                sibling = parent.leftNode;
            }

            // 兄弟节点都是黑色
            if (isBlack(sibling.leftNode) && isBlack(sibling.rightNode)){
                // 染色之前判断父节点是不是黑色
                boolean parentIsBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentIsBlack){
                    // 递归调用处理
                    removeAfterFixNode(parent,null);
                }
            }else{
                // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                // 兄弟节点左边是黑色，兄弟要先旋转
                if (isBlack(sibling.leftNode)){
                    rotateLeft(sibling);
                    sibling = parent.leftNode;
                }

                color(sibling,colorOf(parent));
                black(sibling.leftNode);
                black(parent);
                rotateRight(parent);
            }
        }
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
