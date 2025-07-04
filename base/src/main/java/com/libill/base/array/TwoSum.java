package com.libill.base.array;

import java.util.HashMap;

/**
 * 两数之和
 */
public class TwoSum {
    public int[] twoSum(int a[], int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int key = target - a[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(a[i], i);
        }
        return new int[0];
    }
}
