package com.libill.base.number;

/**
 * 整数反转
 * https://leetcode.cn/problems/reverse-integer/
 */
public class IntegerReverse {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result;
    }

    public int reverse2(int x) {
        String a = String.valueOf(x);
        String temp = reverse(a);
        try {
            return Integer.parseInt(temp);
        } catch (Exception e) {
            return 0;
        }
    }

    public String reverse(String s) {
        char[] c = s.toCharArray();
        char[] temp = new char[c.length];
        int n = c.length;
        int start = 0;
        if (c[0] == '-') {
            start = 1;
            temp[0] = c[0];
        }
        for (int i = start; i < n; i++) {
            if (start == 1) {
                temp[n - i] = c[i];
            } else {
                temp[n - i - 1] = c[i];
            }
        }
        return String.valueOf(temp);
    }
}
