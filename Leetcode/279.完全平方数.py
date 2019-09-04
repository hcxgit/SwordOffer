#
# @lc app=leetcode.cn id=279 lang=python3
#
# [279] 完全平方数
#
"""
dp：背包问题
"""
class Solution:
    def numSquares(self, n: int) -> int:
        dp  = [i for i in range(n+1)]
        for i in range(1,n+1):
            j = 1
            while i-j**2>=0:
                dp[i] = min(dp[i],dp[i-j**2]+1)
                j += 1
        return dp[-1]
