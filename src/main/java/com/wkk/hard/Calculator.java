package com.wkk.hard;

import org.junit.Test;

import java.util.*;

public class Calculator {
    @Test
    public void test10(){
        System.out.println(calculate("1-(-2)"));
    }
    public int calculate1(String s) {
        s = s.replaceAll(" ", "");
        Stack<String> stack = new Stack<>();
        List<Character> opList = Arrays.asList('+', '-');
        int start = 0, end = 0;
        while (end < s.length()) {
            if ("(".equals(s.substring(start, end))) {
                stack.push(s.substring(start, end));
                start++;
                continue;
            }
            if (opList.contains(s.charAt(end))) {
                if (end > start) {
                    stack.push(s.substring(start, end));
                }
                stack.push(s.substring(end, end + 1));
                start = end + 1;
                end++;
                continue;
            }
            if (')' == s.charAt(end)) {
                if (end > start) {
                    stack.push(s.substring(start, end));
                }
                Stack<String> calStack = new Stack<>();
                while (!stack.isEmpty()) {
                    String left = stack.pop();
                    if ("(".equals(left)) {
                        break;
                    }
                    calStack.add(left);
                }
                while (calStack.size() > 1) {
                    String left = calStack.pop();
                    if ("-".equals(left)) {
                        left = String.valueOf(-Integer.parseInt(calStack.pop()));
                    }
                    if (calStack.isEmpty()) {
                        calStack.push(left);
                        break;
                    }
                    String operator = calStack.pop();
                    String right = calStack.pop();
                    Integer li = Integer.valueOf(left);
                    Integer ri = Integer.valueOf(right);
                    if ("+".equals(operator)) {
                        calStack.push(String.valueOf(li + ri));
                    } else if ("-".equals(operator)) {
                        calStack.push(String.valueOf(li - ri));
                    }
                }
                stack.push(calStack.pop());
                start = end + 1;
            }
            end++;
        }
        if (end > start) {
            stack.push(s.substring(start, end));
        }

        Stack<String> calStack = new Stack<>();
        while (!stack.isEmpty()) {
            calStack.push(stack.pop());
        }
        while (calStack.size() > 1) {
            String left = calStack.pop();
            if ("-".equals(left)) {
                left = String.valueOf(-Integer.parseInt(calStack.pop()));
            }
            if (calStack.isEmpty()) {
                calStack.push(left);
                break;
            }
            String operator = calStack.pop();
            String right = calStack.pop();
            Integer li = Integer.valueOf(left);
            Integer ri = Integer.valueOf(right);
            if ("+".equals(operator)) {
                calStack.push(String.valueOf(li + ri));
            } else if ("-".equals(operator)) {
                calStack.push(String.valueOf(li - ri));
            }
        }
        stack.push(calStack.pop());
        return Integer.parseInt(stack.pop());
    }

    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        Stack<Integer> flagStack = new Stack<>();
        flagStack.push(1);
        int sign = 1;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('-' == s.charAt(i)) {
                sign = -flagStack.peek();
            } else if ('+' == s.charAt(i)) {
                sign = flagStack.peek();
            } else if ('(' == s.charAt(i)) {
                flagStack.push(sign);
            } else if (')' == s.charAt(i)) {
                flagStack.pop();
            }else {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    sb.append(s.charAt(i));
                    i++;
                }
                int val = Integer.parseInt(sb.toString());
                sum += sign * val;
                i--;
            }
        }
        return sum;
    }
}

class CalculatorSolution {
    public static void main(String[] args) {
        calculate("2 + 3 - 4 - (9 - (5 - 3))");
    }
    public static int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}
