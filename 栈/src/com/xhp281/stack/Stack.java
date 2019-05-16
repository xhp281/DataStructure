package com.xhp281.stack;

import com.xhp281.common.ArrayList;
import com.xhp281.common.List;

/**
 * User: FenDou
 * Date: 2019-05-14 17:45
 * Description: 栈
 */

public class Stack<E> {

    private List<E> list = new ArrayList<>();

    public boolean isempty(){
        return list.isempty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E top(){
        return list.get(list.size() - 1);
    }

}
