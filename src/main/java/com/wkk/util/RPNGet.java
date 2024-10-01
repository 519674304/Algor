package com.wkk.util;

import org.junit.Test;

import java.util.*;

public class RPNGet {

    @Test
    public void test8(){
        System.out.println(getRpn("2 * ( ( 5 - 3 ) * 4 ) - 16 / 2"));
    }

    public String getRpn(String expression) {
        Deque<String> a = new LinkedList<>();
        Stack<String> b = new Stack<>();
        int start = 0, end = 1;
        Map<String, Integer> operatorLevel = new HashMap<>();
        operatorLevel.put("+", 1);
        operatorLevel.put("-", 1);
        operatorLevel.put("*", 2);
        operatorLevel.put("/", 2);
        operatorLevel.put("(", 3);

        while (end <= expression.length() && start < expression.length()) {
            if (expression.charAt(start) == ' ') {
                start++;
                continue;
            }
            if (end <= start) {
                end++;
                continue;
            }
            String currKey = expression.substring(start, end);
            if (")".equals(currKey)) {
                while (true) {
                    String prevVal = b.pop();
                    if ("(".equals(prevVal)) {
                        break;
                    }
                    a.add(prevVal);
                }
                start++;
            } else if (operatorLevel.containsKey(currKey)) {
                while (!b.isEmpty()) {
                    if (operatorLevel.get(currKey) > operatorLevel.get(b.peek()) || "(".equals(b.peek())) {
                        break;
                    }
                    a.add(b.pop());
                }
                b.push(currKey);
                start++;
            } else if (end == expression.length() || expression.charAt(end) == ' ' || operatorLevel.containsKey(String.valueOf(expression.charAt(end)))) {
                a.add(currKey);
                start = end;
            }
            end++;
        }

        while (!b.isEmpty()) {
            a.add(b.pop());
        }
        StringBuilder res = new StringBuilder();
        while (!a.isEmpty()) {
            res.append(a.pollFirst());
        }
        return res.toString();
    }
}
