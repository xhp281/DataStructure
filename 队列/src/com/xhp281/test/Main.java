package com.xhp281.test;

import com.xhp281.circle.CircleDeque;
import com.xhp281.circle.CircleQueue;
import com.xhp281.queue.Deque;
import com.xhp281.queue.Queue;

/**
 * User: FenDou
 * Date: 2019-05-15 14:43
 * Description:
 */

public class Main {

    /**
     * 队列
     */
    static void QueueTest(){
        Queue<Integer> quque = new Queue<>();
        quque.enQueue(1);
        quque.enQueue(2);
        quque.enQueue(3);
        quque.enQueue(4);

        System.out.println("队列：" + quque.toString());
        System.out.println("第一个元素：" + quque.front());

        while (!quque.isempty()){
            System.out.println("出队：" + quque.deQueue());
        }
    }

    /**
     * 双端队列
     */
    static void DequeTest(){
        Deque<Integer> deque = new Deque<>();
        deque.enQueueRear(1);
        deque.enQueueRear(2);
        deque.enQueueRear(3);
        deque.enQueueFront(100);
        deque.enQueueFront(200);
        deque.enQueueFront(300);

        System.out.println(deque.toString()); // [300 200 100 1 2 3]
        deque.deQueueFront();
        System.out.println(deque.toString()); // [200 100 1 2 3]
        deque.deQueueRear();
        System.out.println(deque.toString()); // [200 100 1 2 ]
        System.out.println("第一个元素：" + deque.front());
        System.out.println("最后一个元素：" + deque.rear());
    }

    /**
     * 循环队列
     */
    static void CircleQueueTest(){
        CircleQueue<Integer> queue = new CircleQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deQueue()); // [300 200 100 1 2 3]
        }
        for (int i = 95; i < 100; i++) {
            queue.enQueue(i);
        }

        System.out.println(queue.toString()); // [300 200 100 1 2 3]
        System.out.println("第一个元素：" + queue.front());
    }

    /**
     * 双端循环队列
     */
    static void CircleDequeTest(){
        CircleDeque<Integer> queue = new CircleDeque<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }

        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueFront(12);

        System.out.println(queue.toString());
        System.out.println("第一个元素：" + queue.front());
    }
    public static void main(String[] args){
//        QueueTest();          // 队列
//        DequeTest();          // 双端队列
//        CircleQueueTest();    // 循环队列
        CircleDequeTest();    // 双端循环队列
    }
}
