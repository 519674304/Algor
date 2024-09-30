package com.wkk.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvalRPN {
    @Test
    public void test9(){
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens));
    }
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        List<String> opList = Arrays.asList("+", "-", "*", "/");
        for (String token : tokens) {
            if (opList.contains(token)) {
                int right = Integer.parseInt(stack.pop());
                int left = Integer.parseInt(stack.pop());
                if ("+".equals(token)) {
                    stack.push(String.valueOf(left + right));
                } else if ("-".equals(token)) {
                    stack.push(String.valueOf(left - right));
                } else if ("*".equals(token)) {
                    stack.push(String.valueOf(left * right));
                } else {
                    stack.push(String.valueOf(left / right));
                }
                continue;
            }
            stack.push(token);
        }
        return Integer.parseInt(stack.pop());
    }
}
