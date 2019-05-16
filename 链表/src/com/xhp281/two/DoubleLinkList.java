package com.xhp281.two;
import com.xhp281.common.AbstractList;
import com.xhp281.common.List;

/**
 * User: FenDou
 * Date: 2019-05-13 18:10
 * Description: 双向链表
 */

public class DoubleLinkList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

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
            last = new Node<>(oldLast,element,null);
            if (oldLast == null){
                first = last;
            }else{
                oldLast.next = last;
            }
        }else{
            Node<E> next = node(index);
            Node<E> pre  = next.prev;
            Node<E> node = new Node<>(pre,element,next);
            next.prev     = node;
            if (pre == null){
                first = node;
            }else{
                pre.next     = node;
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

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null){
            first = next;
        }else{
            prev.next     = next;
        }

        if (next == null){
            last = prev;
        }else{
            next.prev     = prev;
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
