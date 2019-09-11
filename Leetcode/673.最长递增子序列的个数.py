#
# @lc app=leetcode.cn id=673 lang=python3
#
# [673] 最长递增子序列的个数
#
class Solution(object):
    def findNumberOfLIS(self, nums):
        N = len(nums)
        if N <= 1: return N
        dp = [0] * N # longest ending in nums[i]
        counts = [1] * N #  number of longest ending in nums[i]

        for j in range(len(nums)):
            for i in range(j):
                if nums[i] < nums[j]:
                    # 第一次找到长度为 dp[i]+1，且以nums[i]为结尾的最长递增子序列
                    if dp[i]+1 > dp[j]:
                        dp[j] = 1 + dp[i]
                        counts[j] = counts[i]
                    # 已经找到过一次了
                    elif dp[i] + 1 == dp[j]:
                        counts[j] += counts[i]
        longest = max(dp)
        return sum(c for i, c in enumerate(counts) if dp[i] == longest)
