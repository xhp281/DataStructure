package com.xhp281;
import com.xhp281.stack.Stack;

/**
 * User: FenDou
 * Date: 2019-05-14 18:08
 * Description:
 */

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (!stack.isempty()){
            System.out.println(stack.pop());
        }
    }
}
