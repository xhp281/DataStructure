package 栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * User: FenDou
 * Date: 2019-05-14 18:51
 * Description: https://leetcode-cn.com/problems/valid-parentheses/solution/
 */

public class _20_有效的括号 {
    private static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }

    // HashMap 方式实现
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack();

        int len = s.length();
        for (int i = 0; i < len; i++) {
             char c = s.charAt(i);

             // 如果key包含左边括号
             // [{( )}]
             if (map.containsKey(c)){
                 stack.push(c);
             }else {
                  if (stack.isEmpty()) return false;

                  char  left = stack.pop();
                  if (c != map.get(left)) return false;
             }
        }
        return stack.isEmpty();
    }

    // 字符串对比方式实现
    public boolean isValid1(String s) {

        Stack<Character> stack = new Stack();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                if (stack.isEmpty()) return false;

                char left = stack.pop();
                if (left == ')' && c != '(') return false;
                if (left == ']' && c != '[') return false;
                if (left == '}' && c != '{') return false;
            }
        }
        System.out.println();
        return stack.isEmpty();
    }
}
