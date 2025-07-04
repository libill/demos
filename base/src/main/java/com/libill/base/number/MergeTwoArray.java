package com.libill.base.number;

public class MergeTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int cur = 0;
        // 从nums1的第一个位置开始排序,待排序的值拷贝到nums2
        while (i < m + n && j < n) {
            int a = nums1[i];
            int b = nums2[j];
            if (i >= m) {
                cur = b;
                j++;
            } else if (a <= b) {
                cur = a;
            } else  {
                cur = b;
                // 把nums1[i]存到nums2[j]，再次排序
                nums2[j] = a;
            }
            nums1[i] = cur;
            i++;
        }
    }
}
