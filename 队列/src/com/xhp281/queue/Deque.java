package com.xhp281.queue;

import com.xhp281.common.LinkedList;
import com.xhp281.common.List;

/**
 * User: FenDou
 * Date: 2019-05-15 15:26
 * Description: 双端队列
 */

public class Deque<E> {
    private List<E> list = new LinkedList<>();

    /**
     * 获取元素数量
     * @return
     */
    public int size(){
        return list.size();
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isempty(){
        return list.isempty();
    }

    /**
     * 清空队列
     */
    public void  clear(){
        list.clear();
    }

    /**
     * 尾部入队
     * @param element
     */
    public void enQueueRear(E element){
        list.add(element);
    }

    /**
     * 尾部出队
     */
    public E deQueueRear(){
        return list.remove(list.size() - 1);
    }

    /**
     * 头部入队
     * @param element
     */
    public void enQueueFront(E element){
        list.add(0,element);
    }

    /**
     * 头部出队
     */
    public E deQueueFront(){
        return list.remove(0);
    }

    /**
     * 头部元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

    /**
     * 尾部元素
     * @return
     */
    public E rear(){
        return list.get(size() - 1);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
