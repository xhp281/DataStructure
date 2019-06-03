package com.xhp281.tree;

import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-05-29 14:17
 * Description: 平衡二叉搜索树
 */

public class BBST<E> extends BST<E> {

    /**
     * 初始化方法
     */
    public BBST(){
        this(null);
    }
    public BBST(Comparator<E> comparator){
        super(comparator);
    }

    /**
     * 左旋转
     * @param grand
     */
    protected void rotateLeft(Node<E> grand){
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
    protected void rotateRight(Node<E> grand){
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
     * 旋转
     */
    protected void rotate(
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

        // 设置e-f-g
        f.leftNode  = e;
        if (e != null){
            e.parent = f;
        }
        f.rightNode = g;
        if (g != null){
            g.parent = f;
        }

        // 设置b-d-f
        d.leftNode  = b;
        d.rightNode = f;
        b.parent    = d;
        f.parent    = d;

    }

    /**
     * 旋转之后的处理
     * @param grand
     * @param parent
     * @param child
     */
    protected void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
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
    }
}
