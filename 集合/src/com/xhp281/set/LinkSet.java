package com.xhp281.set;

import com.xhp281.list.LinkedList;

/**
 * User: FenDou
 * Date: 2019-06-03 17:17
 * Description: 链表实现set
 */

public class LinkSet<E> implements Set<E> {
    // 创建链表对象
    LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isempty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        // 集合没有重复元素，解决方案
        // 1 不添加
        if (list.contains(element)) return;
        list.add(element);

        // 2 覆盖
//        int index = list.indexOf(element);
//        if (index != list.ELEMENT_NOT_FOUND){
//            list.set(index,element);
//        }else{
//            list.add(element);
//        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != list.ELEMENT_NOT_FOUND){
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) return;
        }
    }
}
