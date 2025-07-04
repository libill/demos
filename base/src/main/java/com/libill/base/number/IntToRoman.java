package com.libill.base.number;

/**
 * 12. 整数转罗马数字
 * https://leetcode.cn/problems/integer-to-roman/description/
 */
public class IntToRoman {
    // 硬编码数字
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman(int a) {
        StringBuffer sb = new StringBuffer();
        sb.append(thousands[a / 1000]);
        sb.append(hundreds[a % 1000 / 100]);
        sb.append(tens[a % 100 / 10]);
        sb.append(ones[a % 10]);
        return sb.toString();
    }
}
