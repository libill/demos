package com.libill.base.number;

public class Multiply {
    /**
     * 计算形式
     *   num1
     * x num2
     * ------
     * result
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String ans = "0";
        int m = num1.length();
        int n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append("0"); // 补0
            }
            int y = num2.charAt(i) - '0';
            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int result = x * y + add;
                curr.append(result % 10);
                add = result / 10;
            }

            if (add != 0) {
                curr.append(add % 10);
            }
            // 将当前结果与新计算的结果求和作为新的结果
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }
}
