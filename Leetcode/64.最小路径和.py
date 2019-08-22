#
# @lc app=leetcode.cn id=64 lang=python3
#
# [64] 最小路径和
#
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        row = len(grid)
        line = len(grid[0])
        dp = [[0 for i in range(line)] for j in range(row)]
        dp [0][0] = grid[0][0]
        for i in range(row):
            for j in range(line):
                if i==0 and j!=0:
                    dp[i][j] = dp[i][j-1]+grid[i][j]
                elif j==0 and i!=0:
                    dp[i][j] = dp[i-1][j]+grid[i][j]
                elif j !=0 and i!=0:
                    dp[i][j] = min(dp[i-1][j],dp[i][j-1])+grid[i][j]
        return dp[-1][-1]
