package com.libill.base.number;

public class Divide {

    /**
     * 暴力解法，超时
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        long dividend_long = dividend;
        long divisor_long = divisor;
        int pre = 1;
        if (dividend_long < 0 && divisor_long > 0 || dividend_long > 0 && divisor_long < 0) {
            pre = -1;
        }
        long result = 0;
        if (dividend_long < 0) {
            dividend_long = -dividend_long;
        }
        if (divisor_long < 0) {
            divisor_long = -divisor_long;
        }
        if (dividend_long < divisor_long) {
            return 0;
        }
        while (dividend_long >= divisor_long) {
            dividend_long -= divisor_long;
            result++;
        }

        if (pre == -1) {
            result = -result;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }
}
