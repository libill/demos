package com.libill.base.dynamicprogramming;

import java.util.Arrays;

/**
 * 最小路径和
 * 动态规划最核心的思想，就在于拆分子问题，记住过往，减少重复计算。
 * https://leetcode.cn/problems/minimum-path-sum/description/
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int dp[] = new int[columns];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < rows; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < columns; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[columns - 1];
    }

    public int minPathSum1(int[][] gird) {
        int rows = gird.length;
        int columns = gird[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = gird[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + gird[i][0];
        }
        for (int i = 1; i < columns; i++) {
            dp[0][i] = dp[0][i - 1] + gird[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + gird[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    // 原地修改
    public int minPathSum2(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[rows - 1][columns - 1];
    }

    public int minPathSum8(int[][] grid) {
        int dp[] = new int[grid.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < grid[0].length; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[grid[0].length - 1];
    }
}
