#
# @lc app=leetcode.cn id=494 lang=python3
#
# [494] 目标和
#
class Solution:
    def findTargetSumWays(self, nums: List[int], S: int) -> int:
        dp = [[0 for i in range(len(nums)+1)] for k in range(2)]
        dp[0][0] = 3 
        dp[1][0] = 3
        for i in range(1,len(nums)+1):
            for j in range(2):
            # 0补-号，1补+号
                dp[0][i] = dp[0][i-1]
                dp[1][i] = dp[1][i-1]
        return 0
                

