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

}
