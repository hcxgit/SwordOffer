#
# @lc app=leetcode.cn id=300 lang=python3
#
# [300] 最长上升子序列
#
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if not nums:
            return 0
        # dp[i]: 以第i个数为结尾的最长上升子序列的长度 
        dp = [1 for i in range(len(nums))]
        for i in range(1,len(nums)):
            for j in range(i):
                if nums[i]>nums[j]:
                    dp[i] = max(dp[j]+1,dp[i])
        return max(dp)
            

