package com.wkk.medium;

import java.util.Stack;

class MinStack {
    private Stack<Integer> minRec = new Stack<>();
    private Stack<Integer> rec = new Stack<>();
    public MinStack() {

    }

    public void push(int val) {
        rec.push(val);
        if(minRec.isEmpty()){
            minRec.push(val);
            return;
        }
        if(minRec.peek() >= val){
            minRec.push(val);
        }
    }

    public void pop() {
        int val = rec.pop();
        if(minRec.peek() == val){
            minRec.pop();
        }
    }

    public int top() {
        return rec.peek();
    }

    public int getMin() {
        return minRec.peek();
    }
}
