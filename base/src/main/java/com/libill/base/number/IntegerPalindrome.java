package com.libill.base.number;

/**
 * 回文数
 * https://leetcode.cn/problems/palindrome-number/
 */
public class IntegerPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int revertNumber = 0;
        // 反转一半数字
        while (x > revertNumber) {
            revertNumber = revertNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertNumber || x == revertNumber / 10;
    }
}
