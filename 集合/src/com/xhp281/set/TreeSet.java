package com.xhp281.set;

import com.xhp281.tree.BinaryTree;
import com.xhp281.tree.RBTree;

import java.util.Comparator;

/**
 * User: FenDou
 * Date: 2019-06-03 17:53
 * Description:
 */

public class TreeSet<E> implements Set<E> {

    RBTree<E> rbTree;

    public TreeSet(Comparator<E> comparator){
        rbTree = new RBTree<>();
    }
    public TreeSet(){
        this(null);
    }

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public boolean contains(E element) {
        return rbTree.contains(element);
    }

    @Override
    public void add(E element) {
        rbTree.add(element);
    }

    @Override
    public void remove(E element) {
        rbTree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        rbTree.inorder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
