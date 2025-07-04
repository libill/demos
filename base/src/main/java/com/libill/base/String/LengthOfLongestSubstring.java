package com.libill.base.String;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        char[] ss = s.toCharArray();
        Set<Character> set = new HashSet<>(); // 去重
        int res = 0; // 结果
        for (int left = 0, right = 0; right < ss.length; right++) { // 每一轮右边的点都扩展一个
            char ch = ss[right]; // right指向的元素，行业是当前考虑的元素
            while (set.contains(ch)) {
                set.remove(ss[left]);
                left++;
            }
            set.add(ch);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    /**
     滑动窗口的模板题
     //外层循环扩展右边界，内层循环扩展左边界
     for (int l = 0, r = 0 ; r < n ; r++) {
     //当前考虑的元素
     while (l <= r && check()) {//区间[left,right]不符合题意
     //扩展左边界
     }
     //区间[left,right]符合题意，统计相关信息
     }
     */
}
