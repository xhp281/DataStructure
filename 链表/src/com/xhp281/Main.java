package com.xhp281;

import com.xhp281.circle.CircleDoubleLinkedList;
import com.xhp281.circle.CircleLinkedList;
import com.xhp281.common.List;
import com.xhp281.single.ArrayList;

import com.xhp281.single.LinkedList;
import com.xhp281.single.LinkedList2;
import com.xhp281.two.DoubleLinkList;

public class Main {

    /**
     * 通用测试方法
     * @param list
     */
    static void testList(List<Integer> list){
        // 添加
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(0,5);
        System.out.println("添加后的列表：" + list.toString()); // [5,1,2,3,4]

        // 删除
        list.remove(1);
        System.out.println("第一次删除后的列表：" + list.toString()); // [5,2,3]
        list.remove(3);
        System.out.println("第二次删除后的列表：" + list.toString()); // [5,2,3]
    }

    static void josephus(){
        CircleDoubleLinkedList<Integer> list = new CircleDoubleLinkedList<>();
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
        // 指向头结点
        list.reset();
        while (!list.isempty()){
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }

    public static void main(String[] args) {
//        testList(new ArrayList<>());               // 数组
//        testList(new LinkedList<>());              // 链表
//        testList(new LinkedList2<>());             // 虚拟头结点
//        testList(new DoubleLinkList<>());          // 双向链表
//        testList(new CircleLinkedList<>());        // 单向循环列表
//        testList(new CircleDoubleLinkedList<>());  // 双向循环列表
          josephus();                                // 约瑟夫问题
    }
}
