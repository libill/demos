package com.libill.base.stack;

import java.util.Deque;
import java.util.LinkedList;

public class SortedStack {
    private Deque<Integer> stack = new LinkedList<>();
    private Deque<Integer> temp = new LinkedList<>();

    public SortedStack() {
    }

    public void push(int val) {
        // 栈为空，入栈
        if (isEmpty()) stack.push(val);
        else {
            // 将栈中小于val的值暂存到temp中
            while (!stack.isEmpty() && val > stack.peek()) {
                temp.push(stack.pop());
            }
            // 入栈
            stack.push(val);
            // 将temp中暂存内容push回来
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        stack.pop();
    }

    public int peek() {
        if (stack.isEmpty()) return -1;
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
