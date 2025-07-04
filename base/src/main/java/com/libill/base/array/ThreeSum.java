package com.libill.base.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) break;
            if (i > 0 && a[i] == a[i - 1]) continue;
            int target = 0 - a[i]; // 两数之和的目标值
            int k = i + 1;
            int j = n - 1;
            while (k < j) {
                if (a[k] + a[j] == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(a[i]);
                    temp.add(a[k]);
                    temp.add(a[j]);
                    result.add(temp);
                    // 跳过重复元素
                    while (k < j && a[k] == a[k + 1]) k++;
                    while (k < j && a[j] == a[j - 1]) j--;
                    // 移动位置
                    k++;
                    j--;
                } else if (a[k] + a[j] < target) {
                    k++;
                } else {
                    j--;
                }
            }
        }
        return result;
    }

}

