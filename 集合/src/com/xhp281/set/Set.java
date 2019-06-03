package com.xhp281.set;

/**
 * User: FenDou
 * Date: 2019-06-03 17:17
 * Description: set接口
 */

public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }

}
