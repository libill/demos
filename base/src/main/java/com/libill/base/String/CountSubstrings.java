package com.libill.base.String;

public class CountSubstrings {

    int sum = 0;

    public int countSubstrings(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            sum++;
            isc(s, i - 1, i + 1);
            isc(s, i, i + 1);
        }
        return sum;
    }

    private void isc(String s, int low, int high) {
        int n = s.length();
        while (low >= 0 && high < n) {
            char a = s.charAt(low);
            char b = s.charAt(high);
            if (a != b) {
                break;
            }
            System.out.println("low:" + low + ", high:" + high);
            sum++;
            low--;
            high++;
        }
    }
}
