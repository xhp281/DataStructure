package com.xhp281.circle;

import com.xhp281.common.AbstractList;

/**
 * User: FenDou
 * Date: 2019-05-14 14:50
 * Description: 双向循环列表
 */

public class CircleDoubleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    // 内部类
    private static class Node<E>{
        E element;
        Node<E> next;
        Node<E> prev;

        // 构造方法
        public Node( Node<E> prev,E element, Node<E> next) {
            this.element = element;
            this.next  = next;
            this.prev  = prev;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            if (prev != null){
                buffer.append(prev.element);
            }else{
                buffer.append("null");
            }

            buffer.append("<——").append(element).append("——>");

            if (next != null){
                buffer.append(next.element);
            }else{
                buffer.append("null");
            }
            return buffer.toString();
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
        last  = null;
    }

    /**
     *  添加到指定位置
     */
    public void add(int index,E element){
        cheakAddRange(index);

        // 往后面添加
        if (index == size){
            Node<E> oldLast = last;
            // 更新last为新加入的元素，并设置前后节点
            last = new Node<>(oldLast,element,first);

            // size == 0 的的时候，第一个元素指向自己
            if (oldLast == null){
                first = last;
                first.prev = first;
                first.next = first;
            }else{
                // size > 1 的时候first last要循环指向
                oldLast.next = last;
                first.prev   = last;
            }
        }else{
            // 其他位置添加
            Node<E> next = node(index);
            Node<E> pre  = next.prev;
            Node<E> node = new Node<>(pre,element,next);
            next.prev    = node;
            pre.next     = node;

            if (index == 0){  // first == next
                first = node;
            }
        }
        size++;
    }

    /**
     * 判断元素下标
     */
    public int indexOf(E element){

        Node<E> node = first;
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
        return com.xhp281.common.List.ELEMENT_NOT_FOUND;
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

    public void reset(){
        current = first;
    }

    /**
     * 切换下一个元素
     */
    public E next(){
        if (current == null) return null;
        current = current.next;
        return  current.element;
    }

    /**
     * 删除元素
     * @return
     */
    public E remove(){
        if (current == null) return  null;
        E element = remove(current);
        if (size == 0){
            current = null;
        }else{
            current =  current.next;
        }
        return element;
    }

    /**
     * 根据下标删除元素
     */
    public E remove(int index){
        checkRange(index);
        return remove(node(index));
    }

    /**
     * 删除元素
     * @param node
     * @return
     */
    public E remove(Node<E> node){
        if (size == 1){
            first = null;
            last  = null;
        }else{
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next     = next;
            next.prev     = prev;

            if (node == first){ // index == 0
                first = next;
            }
            if (node == last){ // index == size - 1
                last = prev;
            }
        }
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
//      判断位置
        if (index < (size >> 1)){
            Node<E> node = first;
            // 从前往后找
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else{
            // 从后往前找
            Node<E> node = last;
            for (int i = size - 1; i > index ; i--) {
                node = node.prev;
            }
            return node;
        }
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
        Node<E> node = first;

        for (int i = 0; i < size ; i++){
            if (i == 0){
                buffer.append(node);
            }else{
                buffer.append("," + node);
            }
            node = node.next;
        }
        buffer.append("]");
        return  buffer.toString();
    }
}
