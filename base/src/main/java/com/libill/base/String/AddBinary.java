package com.libill.base.String;

public class AddBinary {
    public String addBinary(String a, String b) {
        int n = a.length() > b.length() ? a.length() : b.length();
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = n - 1; i >= 0; i--) {
            int m = a.length() > i ? (a.charAt(i) - '0') : 0;
            int k = b.length() > i ? (b.charAt(i) - '0') : 0;
            int value = m + k + carry;
            carry = value / 2;
            sb.append(value % 2);
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
