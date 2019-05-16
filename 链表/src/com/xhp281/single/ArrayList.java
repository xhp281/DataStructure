package com.xhp281.single;
import com.xhp281.common.AbstractList;

/**
 * User: FenDou
 * Date: 2019-05-07 18:28
 * Description:
 */

public class ArrayList<E> extends AbstractList<E> {

    /**
     *  所有元素
     * */
    private E[] elements;

    /**
     * 默认大小
     */
    private static final  int DEFAULT_CAPACITY = 10;

    /**
     * 构造函数
     */
    public ArrayList(int capaticy){
        capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     *  清除所有元素
     */
    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        System.gc();
    }

    /**
     * 判断元素下标
     */
    public int indexOf(E element){
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        }else{
            for(int i = 0; i < size; i++){
                if (elements[i].equals(element)) return  i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     *  根据下标获取元素
     */
    public E get(int index){
        checkRange(index);

        return elements[index];
    }

    /**
     * 设置index位置的元素
     */
    public E set(int index,E element){
        checkRange(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     *  添加到指定位置
     */
    public void add(int index,E element){
        cheakAddRange(index);

        // 容量检查
        updateCapaticy(size + 1);

        for (int i = size; i > index; i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除元素
     */
    public E remove(int index){
        checkRange(index);

        E old = elements[index];

        // 第一种方式
//        size--;
//        for (int i = index; i < size; i++){
//             elements[i] = elements[i + 1];
//        }
//        elements[size] = null;

        // 第二种方式
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        trim();
        return  old;
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
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapaticy + "- 扩容为 -" + newCapaticy);
    }

    /**
     * 动态缩容
     */
    private void trim(){
        // 30
        int oldCapacity = elements.length;
        // 15
        int newCapacity = oldCapacity >> 1;
        if (size > (newCapacity) || oldCapacity <= DEFAULT_CAPACITY) return;

        // 剩余空间还很多
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity + "缩容为" + newCapacity);
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
        for (int i = 0; i < size ; i++){
            if (i == 0){
                buffer.append(elements[i]);
            }else{
                buffer.append("," + elements[i]);
            }
        }
        buffer.append("]");
        return  buffer.toString();
    }
}
