package com.libill.base.stack;

import java.util.ArrayDeque;

/**
 * 逆波兰表示试求值
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        for (String item : tokens) {
            // 判断是符号还是数字
            if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
                int a = stack.pop();
                int b = stack.pop();
                if ("+".equals(item)) {
                    stack.push(b + a);
                } else if ("-".equals(item)) {
                    stack.push(b - a);
                } else if ("*".equals(item)) {
                    stack.push(b * a);
                } else if ("/".equals(item)) {
                    stack.push(b / a);
                }
            } else {
                stack.push(Integer.valueOf(item));
            }
        }
        return stack.pop();
    }

}
