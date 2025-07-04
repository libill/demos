package com.libill.base.stack;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * 有效的括号
 * https://leetcode.cn/problems/valid-parentheses
 */
public class ValidBracket {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character item : s.toCharArray()) {
            if ('(' == item) {
                stack.push(')');
            } else if ('[' == item) {
                stack.push(']');
            } else if ('{' == item) {
                stack.push('}');
            } else if (!stack.isEmpty() && stack.pop() != item) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> hashMap = new HashMap() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        for (int i = 0; i < s.length(); i++) {
            Character item = s.charAt(i);
            if (hashMap.containsKey(item)) {
                if (stack.isEmpty() || stack.peek() != hashMap.get(item)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(item);
            }
        }
        return stack.isEmpty();
    }
}
