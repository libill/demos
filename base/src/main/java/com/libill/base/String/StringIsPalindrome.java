package com.libill.base.String;

public class StringIsPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char leftC = ' ';
            while (left <= right) {
                char c = s.charAt(left);
                if (isLetterOrDigit(c)) {
                    leftC = c;
                    break;
                }
                left++;
            }

            char rightC = ' ';
            while (left <= right) {
                char c = s.charAt(right);
                if (isLetterOrDigit(c)) {
                    rightC = c;
                    break;
                }
                right--;
            }

            if (Character.toLowerCase(leftC) != Character.toLowerCase(rightC)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isLetterOrDigit(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
