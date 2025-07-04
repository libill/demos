package com.libill.base.String;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 1) {
            return strs[0];
        }
        StringBuffer sb = new StringBuffer();
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (strs[i].length() <= max) {
                max = strs[i].length();
            }
        }

        for (int i = 0; i < max; i++) {
            boolean isSame = true;
            for (int j = 0; j < n - 1; j++) {
                if (strs[j].charAt(i) == strs[j+1].charAt(i)) {
                    isSame = true;
                } else {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
