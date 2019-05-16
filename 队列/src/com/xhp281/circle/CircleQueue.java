package com.xhp281.circle;

import java.util.Objects;

/**
 * User: FenDou
 * Date: 2019-05-15 17:08
 * Description: 循环队列
 */

public class CircleQueue<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_COUNT = 10;

    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_COUNT];
    }

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
     * 清空队列
     */
    public void  clear(){
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size  = 0;
    }

    /**
     * 入队
     * @param element
     */
    public void enQueue(E element){
        updateCapaticy(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 出队
     * @return
     */
    public E deQueue(){
        E elementFront  = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return elementFront;
    }

    public E front(){
        return elements[front];
    }

    public int index(int index){
        index += front;
//   return index % elements.length;
//   优化：n % m == n - (m >= n ? 0 : m) 前提：n < 2m
//   n = 10
//   m = 13
     return index - (elements.length >= index ? 0 : elements.length);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("front = " + front)
        .append(" size = " + elements.length)
        .append(", list = [");

        for (int i = 0; i < elements.length ; i++){
            if (i == 0){
                buffer.append(elements[i]);
            }else{
                buffer.append("," + elements[i]);
            }
        }
        buffer.append("]");
        return  buffer.toString();
    }

    /**
     * 动态扩容
     * @param capaticy
     */
    private void updateCapaticy(int capaticy){
        int oldCapaticy = elements.length;
        if (oldCapaticy >= capaticy) return;
        // 扩容为1.5倍
        int newCapaticy = oldCapaticy + (oldCapaticy >> 1);
        E[] newElements   = (E[]) new Object[newCapaticy];
        // 移动之前元素
        for (int i = 0; i < size; i++){
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        // 重置头
        front = 0;
    }
}
