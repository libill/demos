package com.libill.base.stack;

import java.util.LinkedList;

/**
 * 最小栈
 * https://leetcode.cn/problems/min-stack/
 */
public class MinStack {
    private LinkedList stack = new LinkedList<Integer>();
    private LinkedList minStack = new LinkedList<Integer>();

    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        int min = min((Integer) minStack.peek(), val);
        minStack.push(min);
    }

    private int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return (Integer) stack.peek();
    }

    public int getMin() {
        return (Integer) minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
