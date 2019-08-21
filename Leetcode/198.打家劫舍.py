#
# @lc app=leetcode.cn id=198 lang=python3
#
# [198] 打家劫舍
#
class Solution:
    def rob(self, nums: List[int]) -> int:
        # dp[i]：第i天偷的最大金额
        # dp[i] = max(dp[:i-1])+nums[i]
        if not nums:
            return 0
        elif 0< len(nums) <=2:
            return max(nums)
        dp = [0 for i in range(len(nums))]
        dp[0],dp[1] = nums[0],nums[1]
        for i in range(2,len(nums)):
            dp[i] = max(dp[:i-1])+nums[i]
        return max(dp[-1],dp[-2])

