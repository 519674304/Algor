package com.wkk.test;

import java.util.Stack;

public class TT1 {

    /*
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

        有效字符串需满足：

        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。

        示例 1：
        输入：s = "()"
        输出：true

        示例 2：
        输入：s = "()[]{}"
        输出：true

        示例 3：
        输入：s = "(]"
        输出：false

        示例 4：
        输入：s = "([)]"
        输出：false

        示例 5：
        输入：s = "{[]}"
        输出：true

        提示：
        1 <= s.length <= 104
        s 仅由括号 '()[]{}' 组成
     **/
    public static void main(String[] args) {
       // String inStr = "()";
 //       String inStr = "()[]{}";
 //       String inStr = "(]";
 //       String inStr = "([)]";
        String inStr = "))))";
        Stack<Character> stack = new Stack<>();
        String check = "([{";
        boolean flag = true;
        for (int i = 0; i < inStr.length(); i++) {
            if (check.contains(String.valueOf(inStr.charAt(i)))) {
                stack.push(inStr.charAt(i));
                continue;
            }
            if (stack.isEmpty()) {
                flag = false;
                break;
            }
            if (inStr.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
            }else if (inStr.charAt(i) == ']' && stack.peek() == '[') {
                stack.pop();
            }else if (inStr.charAt(i) == '}' && stack.peek() == '{') {
                stack.pop();
            }else {
                flag = false;
                break;
            }
        }
        if (flag) {
            flag = stack.isEmpty();
        }
        System.out.println(flag);
    }


}
