package com.libill.base;

import java.util.HashMap;

public class TestCode {
    public static void main(String[] args) {
        String state = new String("demo");
//        String state = new String("de") + new String("mo");
        String internState = state.intern();
//        String name = "demo";
//        System.out.println(name == state);
//        System.out.println(name == internState);
        System.out.println(state.equals(internState));
        System.out.println(state == internState);
        System.out.println(state.hashCode());
        System.out.println(internState.hashCode());
    }
}


class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[] { map.get(diff), i };
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}