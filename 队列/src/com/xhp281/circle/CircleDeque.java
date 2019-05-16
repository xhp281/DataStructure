package com.xhp281.circle;

/**
 * User: FenDou
 * Date: 2019-05-15 17:08
 * Description: 双端循环队列
 */

public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_COUNT = 10;

    /**
     * 初始化
     */
    public CircleDeque(){
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
     * 头部入队
     * @param element
     */
    public void enQueueFront(E element){
        updateCapaticy(size + 1);

        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 尾部入队
     * @param element
     */
    public void enQueueRear(E element){
        updateCapaticy(size + 1);

        elements[index(size)] = element;
        size++;
    }

    /**
     * 尾部出队
     */
    public E deQueueRear(){
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rear;
    }

    /**
     * 头部出队
     */
    public E deQueueFront(){
        E firstElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return firstElement;
    }

    /**
     * 头部元素
     * @return
     */
    public E front(){
        return elements[front];
    }

    /**
     * 尾部元素
     * @return
     */
    public E rear(){
        return elements[index(size - 1)];
    }

    /**
     * 转换成需要的循环 index
     * @param index
     * @return
     */
    private int index(int index){
        index += front;
        if (index < 0){
            return index + elements.length;
        }
//        return index % elements.length;
           return  index - (elements.length <= index ? elements.length : 0);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("capcacity = ").append(elements.length)
        .append(" front = " + front)
        .append(" size = " + size)
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
