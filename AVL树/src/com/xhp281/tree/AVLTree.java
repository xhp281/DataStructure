package com.xhp281.tree;

import java.util.Comparator;

public class AVLTree<E>  extends BST<E>{
    /**
     * 初始化方法
     */
    public AVLTree(){
        this(null);
    }
    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }

    /**
     * 创建节点
     * @param element
     * @param parent
     * @return
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element,parent);
    }

    /**
     * AVL节点
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {
        // 叶子节点默认高度 1
        int height = 1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /* 更新自己的高度 */
        public void updateHeight(){
            int leftHeight  = leftNode  == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        /* 获取平衡因子 */
        public int balanceFactor(){
            int leftHeight  = leftNode  == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;
            return leftHeight - rightHeight;
        }

        /* 获取度最高的节点 */
        public Node<E> tallerChild() {
            int leftHeight = leftNode == null ? 0 : ((AVLNode<E>) leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>) rightNode).height;
            if (leftHeight > rightHeight) return leftNode;
            if (leftHeight < rightHeight) return rightNode;
            return isLeftChild() ? leftNode : rightNode;
        }
    }

    /**
     * 调整二叉树
     * @param node
     */
    @Override
    protected void fixNode(Node<E> node) {
        // 循环
        while ((node = node.parent) != null){

            // 是平衡节点，更新高度
            if (isBalance(node)){
                 updateHeight(node);

             // 调整平衡
            }else{
                // 恢复平衡
                rebalance(node);
                // 整棵树都恢复平衡，结束循环
                break;
            }
        }
    }
    /**
     * 判断是不是平衡
     * @param node
     * @return
     */
    public boolean isBalance(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    /**
     * 更新高度
     * @param node
     */
    public void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    /**
     * 恢复平衡
     @param grand 高度最低的不平衡节点
     */
    public void rebalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node   = ((AVLNode<E>)parent).tallerChild();

        // 属于左边
        if (parent.isLeftChild()){
            if (node.isLeftChild()){ // LL
                rotateRight(grand);
            }else{ // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{
            if (node.isLeftChild()){ // RL
                rotateRight(parent);
                rotateLeft(grand);
            }else{ // RR
                rotateLeft(grand);
            }
        }
        }

    /**
     * 左旋转
     * @param grand
     */
    private void rotateLeft(Node<E> grand){

    }

    /**
     * 右旋转
     * @param grand
     */
    private void rotateRight(Node<E> grand){

    }

}
