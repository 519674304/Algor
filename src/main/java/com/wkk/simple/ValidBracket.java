package com.wkk.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class ValidBracket {

    @Test
    public void test11(){
        StringBuilder sb = new StringBuilder("bb");
        sb.insert(0, "aa");
        System.out.println(sb);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        List<Character> list = Arrays.asList('(', '[', '{');
        for(int i = 0; i < s.length(); i++){
            if(list.contains(s.charAt(i))){
                stack.push(s.charAt(i));
                continue;
            }
            if(stack.isEmpty()){
                return false;
            }
            Character left = stack.pop();
            if(')' == s.charAt(i) && !Objects.equals('(', left)){
                return false;
            }
            if(']' == s.charAt(i) && !Objects.equals('[', left)){
                return false;
            }
            if('}' == s.charAt(i) && !Objects.equals('{', left)){
                return false;
            }
        }


        return stack.isEmpty();
    }
}
