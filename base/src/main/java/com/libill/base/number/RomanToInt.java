package com.libill.base.number;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * https://leetcode.cn/problems/roman-to-integer/description/
 */
public class RomanToInt {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int n = s.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        return result;
    }
}
