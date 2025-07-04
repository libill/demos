package com.libill.base.array;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum > target) {
                    int right0 = right - 1;
                    while (left < right0 && nums[right0] == nums[right]) {
                        right0--;
                    }
                    right = right0;
                } else {
                    int left1 = left + 1;
                    while (left1 < right && nums[left1] == nums[left]) {
                        left1++;
                    }
                    left = left1;
                }
            }
        }
        return best;
    }
}
