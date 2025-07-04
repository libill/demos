package com.libill.base.String;

public class BreakPalindrome {
    public String breakPalindrome(String s) {
        int n = s.length();
        if (n <= 1) {
            return "";
        }
        int low = 0;
        int high = n - 1;
        char[] arr = s.toCharArray();
        while (low < high) {
            char a = arr[low];
            // 左半部分不等于a,直接赋值a
            if (a != 'a') {
                arr[low] = 'a';
                return new String(arr);
            }
            low++;
            high--;
        }
        // 回文串的特性，全是a，最后一个赋值b即可
        arr[n - 1] = 'b';
        return new String(arr);
    }
}
