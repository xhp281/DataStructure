package com.xhp;

public class ArrayList<E> {
    /**
     *  元素数量
     * */
    private int size;

    /**
     *  所有元素
     * */
    private E[] elements;

    // 默认大小
    private static final  int DEFAULT_CAPACITY = 10;
    // 未找到
    private static final int ELEMENT_NOT_FOUND = -1;

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
     *  获取元素数量
     */
    public int size(){
        return size;
    }
    /**
     *  是否为空
     */
    public boolean isempty(){
        return size == 0;
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
     * 插入尾部
     */
    public void add(E element){
        add(size,element);
    }
    /**
     * 删除元素
     */
    public E remove(int index){
        checkRange(index);

        E old = elements[index];
        for (int i = index; i <= size; i++){
            elements[i] = elements[i+1];
        }
//        size--;
//        elements[size] = null;
        elements[--size] = null;
        return  old;
    }


//    工具类
    /**
     * 抛出错误
     */
    private void outOfBound(int index){
        throw new IndexOutOfBoundsException("Index is " + index + ", size is " + size);
    }
    /**
     *  检查范围
     */
    private void checkRange(int index){
        if (index < 0 || index >= size){
            outOfBound(index);
        }
    }
    private void cheakAddRange(int index){
        if (index < 0 || index > size){
            outOfBound(index);
        }
    }
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
        System.out.println(oldCapaticy + " 扩容为: " + newCapaticy);
    }

    /**
     *  打印字符串
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
