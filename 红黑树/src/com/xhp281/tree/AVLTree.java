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

        /* 打印内容 */
        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "";// + "_p(" + parentString + ")_h(" + height + ")";
        }
    }

    /**
     * 调整二叉树
     * @param node
     */
    @Override
    protected void addAfterFixNode(Node<E> node) {
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
     * 删除之后调整失衡节点
     * @param node
     */
    protected void removeAfterFixNode(Node<E>node){
        while ((node = node.parent) != null){
            if (isBalance(node)){
                updateHeight(node);
            }else{
                rebalance(node);
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
     * 恢复平衡方式1
     @param grand 高度最低的不平衡节点
     */
    public void rebalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node   = ((AVLNode<E>)parent).tallerChild();

        // 属于左边
        if (parent.isLeftChild()){
            if (node.isLeftChild()){ // LL
                rotate(grand,node.leftNode,node,node.rightNode,parent,parent.rightNode,grand,grand.rightNode);
            }else{ // LR
                rotate(grand,parent.leftNode,parent,node.leftNode,node,node.rightNode,grand,grand.rightNode);
            }
        }else{
            if (node.isLeftChild()){ // RL
                rotate(grand,grand.leftNode,grand,node.leftNode,node,node.rightNode,parent,parent.rightNode);
            }else{ // RR
                rotate(grand,grand.leftNode,grand,parent.leftNode,parent,node.leftNode,node,node.rightNode);
            }
        }
    }
    /**
     * 恢复平衡方式2
     @param grand 高度最低的不平衡节点
     */
    public void rebalance2(Node<E> grand){
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
     * 旋转
     */
    private void rotate(
            Node<E> p,
            Node<E> a,Node<E> b,Node<E> c,
            Node<E> d,
            Node<E> e,Node<E> f,Node<E> g){

        // 设置d成为根节点
        d.parent = p.parent;
        if (p.isLeftChild()){
            p.parent.leftNode  = d;
        }else if(p.isRightChild()){
            p.parent.rightNode = d;
        }else{
            root = d;
        }

        // 设置a-b-c
        b.leftNode  = a;
        if (a != null){
            a.parent = b;
        }
        b.rightNode = c;
        if (c != null){
            c.parent = b;
        }
        updateHeight(b);

        // 设置e-f-g
        f.leftNode  = e;
        if (e != null){
            e.parent = f;
        }
        f.rightNode = g;
        if (g != null){
            g.parent = f;
        }
        updateHeight(f);

        // 设置b-d-f
        d.leftNode  = b;
        d.rightNode = f;
        b.parent    = d;
        f.parent    = d;
        updateHeight(d);
    }

    /**
     * 左旋转
     * @param grand
     */
    private void rotateLeft(Node<E> grand){
        // RR的情况
        // 获取parent
        Node<E> parent  = grand.rightNode;
        Node<E> parentOldLeft = parent.leftNode;

        // 交换子节点
        grand.rightNode = parentOldLeft;
        parent.leftNode = grand;

        // 旋转之后处理
        afterRotate(grand,parent,parentOldLeft);
    }

    /**
     * 右旋转
     * @param grand
     */
    private void rotateRight(Node<E> grand){
        // LL情况
        Node<E> parent         = grand.leftNode;
        Node<E> parentOldRight = parent.rightNode;

        // 交换子节点
        grand.leftNode          = parentOldRight;
        parent.rightNode        = grand;

        // 旋转之后处理
        afterRotate(grand,parent,parentOldRight);
    }

    /**
     * 旋转之后的处理
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
        // 更新parent根节点
        parent.parent           = grand.parent;

        // 设置parent在根节点的左右位置
        if (grand.isLeftChild()){
            grand.parent.leftNode  = parent;
        }else  if (grand.isRightChild()){
            grand.parent.rightNode = parent;
        }else {
            root = parent;
        }

        // 更新parentOldRight的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }
}
