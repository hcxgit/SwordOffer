#
# @lc app=leetcode.cn id=1162 lang=python3
#
# [1162] 地图分析
#
class Solution:
    def maxDistance(self, grid: List[List[int]]) -> int:
        # 1、从左上角遍历，看左上的两个格判断，然后从右下角遍历，根据右下两个格子判断
        line = len(grid[0])
        row = len(grid)
        dp = [[row*line for j in range(line)] for i in range(row)]
        
        for i in range(row):
            for j in range(line):
                if grid[i][j] == 1:
                    dp[i][j] = 0
                else:
                    if i-1>=0 and j-1>=0:
                        dp[i][j] = min(dp[i-1][j],dp[i][j-1])+1
                    elif i-1 >=0:
                        dp[i][j] = dp[i-1][j]+1
                    elif j-1 >=0:
                        dp[i][j] = dp[i][j-1]+1
        res = 0
        for i in range(row-1,-1,-1):
            for j in range(line-1,-1,-1):
                if grid[i][j] == 0:
                    if i+1<row and j+1<line:
                        dp[i][j] = min(min(dp[i+1][j],dp[i][j+1])+1,dp[i][j])
                    elif i+1 < row:
                        dp[i][j] = min(dp[i+1][j]+1,dp[i][j])
                    elif j+1 < line:
                        dp[i][j] = min(dp[i][j+1]+1,dp[i][j])
                if 0<dp[i][j] <row*line:
                    res = max(res,dp[i][j])
        return res if res else -1
                    
