package com.xhp281.list;

/**
 * User: FenDou
 * Date: 2019-05-07 18:16
 * Description: 接口
 */

public interface List<E> {

    /**
     *  未找到
     */
    static final int ELEMENT_NOT_FOUND = -1;

    /**
     *  清除所有元素
     */
    public void clear();

    /**
     *  获取元素数量
     */
    public int size();
    /**
     *  是否为空
     */
    public boolean isempty();

    /**
     * 判断元素下标
     */
    public int indexOf(E element);
    /**
     *  根据下标获取元素
     */
    public E get(int index);
    /**
     * 设置index位置的元素
     */
    public E set(int index,E element);
    /**
     *  添加到指定位置
     */
    public void add(int index,E element);
    /**
     * 插入尾部
     */
    public void add(E element);
    /**
     * 删除元素
     */
    public E remove(int index);
}
