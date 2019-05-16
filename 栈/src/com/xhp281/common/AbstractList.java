package com.xhp281.common;

/**
 * User: FenDou
 * Date: 2019-05-07 18:47
 * Description:
 */

public abstract class AbstractList<E> implements List<E> {

    /**
     *  元素数量
     * */
    public int size;

    /**
     * 获取元素数量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isempty(){
        return size == 0;
    }

    /**
     * 是不是包含某个元素
     * @param element
     * @return
     */
    public boolean contains(E element){
        return indexOf(element) != List.ELEMENT_NOT_FOUND;
    }

    /**
     * 添加数据尾部
     * @param element
     */
    public void add(E element){
        add(size,element);
    }

    /**
     * 抛出错误
     * @param index
     */
    protected void outOfBound(int index){
        throw new IndexOutOfBoundsException("Index is " + index + ", size is " + size);
    }

    /**
     * 检查范围
     * @param index
     */
    protected void checkRange(int index){
        if (index < 0 || index >= size){
            outOfBound(index);
        }
    }

    /**
     * 检测添加的范围
     * @param index
     */
    protected void cheakAddRange(int index){
        if (index < 0 || index > size){
            outOfBound(index);
        }
    }

}
