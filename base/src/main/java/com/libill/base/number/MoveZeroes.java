package com.libill.base.number;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int k = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < k; j++) {
                    change(nums, j, j+1);
                }
                k--;
            }
        }
    }

    private void change(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
