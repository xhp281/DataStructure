package com.xhp281.queue;

import com.xhp281.common.LinkedList;
import com.xhp281.common.List;

/**
 * User: FenDou
 * Date: 2019-05-15 14:39
 * Description: 队列
 */

public class Queue<E>{
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
     * 进队
     * @param element
     */
    public void enQueue(E element){
        list.add(element);
    }

    /**
     * 出队
     * @return
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
