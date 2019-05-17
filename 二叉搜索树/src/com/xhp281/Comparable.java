package com.xhp281;

/**
 * User: FenDou
 * Date: 2019-05-17 18:22
 * Description: 比较方法
 */

public interface Comparable<E> {
    /**
     * 参数比较
     * @param e
     * @return
     */
    int compareTo(E e);

}
