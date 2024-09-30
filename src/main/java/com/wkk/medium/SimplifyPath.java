package com.wkk.medium;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SimplifyPath {
    @Test
    public void test7(){
        System.out.println(simplifyPath("/../"));
        Stack<Integer> stack = new Stack<>();

    }
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int start = 0, end = 1;
        while(end < path.length()){
            if ('/' == path.charAt(end)){
                if(!path.substring(start, end).equals("/")){
                    if("/..".equals(path.substring(start, end))){
                        if(!stack.isEmpty()){
                            stack.pop();
                        }
                    }else if (!"/.".equals(path.substring(start, end))){
                        stack.push(path.substring(start, end));
                    }
                }
                start = end;
            }
            end++;
        }
        if (start < end - 1) {
            String lastDir = path.substring(start, end);
            if("/..".equals(lastDir)){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else if (!"/.".equals(lastDir)){
                stack.push(path.substring(start, end));
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            sb.append("/");
        }
        while(!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}

class SimplifyPathSolution {
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (!name.isEmpty() && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }
}
