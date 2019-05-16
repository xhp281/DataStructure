package 栈;

import java.util.Stack;

/**
 * User: FenDou
 * Date: 2019-05-15 14:53
 * Description: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */

public class _232_用栈实现队列 {

   private static Stack<Integer> insStack;
   private static Stack<Integer> outStack;

    /** Initialize your data structure here. */
   public _232_用栈实现队列(){
        insStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        insStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return outStack.isEmpty() && insStack.isEmpty();
    }

    /** check and set outStack */
    public void checkOutStack(){
        if (outStack.isEmpty()){
            while (!insStack.isEmpty()){
                outStack.push(insStack.pop());
            }
        }
    }

}
