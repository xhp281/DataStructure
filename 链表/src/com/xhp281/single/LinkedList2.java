package com.xhp281.single;
import com.xhp281.common.AbstractList;
import com.xhp281.common.List;

/**
 * User: FenDou
 * Date: 2019-05-08 10:32
 * Description: 虚拟头结点
 */

public class LinkedList2<E> extends AbstractList<E> {
    private Node<E> first;

    public LinkedList2() {
        first = new Node<>(null,null);
    }

    // 内部类
    private static class Node<E>{
        E element;
        Node<E> next;
        // 构造方法
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("释放了");
        }
    }

    /**
     *  清除所有元素
     */
    public void clear(){
        size  = 0;
        first = null;
    }

    /**
     *  添加到指定位置
     */
    public void add(int index,E element){
        cheakAddRange(index);

        Node<E> pre = index == 0 ? first : node(index - 1);
        pre.next = new Node<>(element,pre.next); // 设置 index 位置的数据
        size++;
    }

    /**
     * 判断元素下标
     */
    public int indexOf(E element){

        Node<E> node = first.next;
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        }else{
            for(int i = 0; i < size; i++){
                if (element.equals(node.element)) return  i;
                node = node.next;
            }
        }
        return List.ELEMENT_NOT_FOUND;
    }

    /**
     *  根据下标获取元素
     */
    public E get(int index){
        return  node(index).element;
    }

    /**
     * 设置index位置的元素
     */
    public E set(int index,E element){
        Node<E>  node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * 删除元素
     */
    public E remove(int index){
        checkRange(index);
        Node<E> pre   = index == 0 ? first : node(index - 1);
        Node<E>  node = pre.next;     // 获取要删除的元素
        pre.next      = node.next;    // 修改之前的对象元素指向 index+1 对象
        size--;
        return node.element;
    }

    /**
     * 根据下标查找对象
     * @param index
     * @return
     */
    private Node<E> node(int index){
        // 检查下标是不是合理
        checkRange(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 打印字符串
     * @return
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("size = " + size);
        buffer.append(", list = [");
        Node<E> node = first.next;

        for (int i = 0; i < size ; i++){
            if (i == 0){
                buffer.append(node.element);
            }else{
                buffer.append("," + node.element);
            }
            node = node.next;
        }
        buffer.append("]");
        return  buffer.toString();
    }
}