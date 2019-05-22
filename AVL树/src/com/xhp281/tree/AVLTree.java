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
        public int updateHeight(){
            int leftHeight  = leftNode  == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;
            return 1 + Math.max(leftHeight,rightHeight);
        }

        /* 获取平衡因子 */
        public int balanceFactor(){
            int leftHeight  = leftNode  == null ? 0 : ((AVLNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;
            return leftHeight - rightHeight;
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
     * @param node
     */
    public void rebalance(Node<E> node){

    }

    /**
     * 修复失衡节点
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
            }
        }
    }

}
