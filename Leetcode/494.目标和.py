#
# @lc app=leetcode.cn id=494 lang=python3
#
# [494] 目标和
#
class Solution:
    def findTargetSumWays(self, nums, S: int) -> int:
        # res = 0
        # def find(alist,target,res):
        #     if len(alist) == 1:
        #         if alist[0] == target: 
        #             res += 1
        #         if -alist[0] == target:
        #             res += 1
        #         return res
        #     else:
        #         res = find(alist[1:],target-alist[0],res)
        #         res = find(alist[1:],target+alist[0],res)
        #         return res
        """
        dp[i][j]: 存储到达下标 i 位置所能产生的数字 j 的个数。
        j 的取值范围是 -sum(nums) ~ sum(nums)。
        dp[i][j] = dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]]
        """
        dp = [dict() for i in range(len(nums))]
        sumArange = sum(nums)
        if nums[0] == 0:
            dp[0][0]=2
        else:
            dp[0][nums[0]] = 1
            dp[0][-nums[0]] = 1
        for i in range(1,len(nums)):
            for j in range(-sumArange,sumArange+1):
                dp[i][j] = dp[i-1].get(j-nums[i],0)+dp[i-1].get(j+nums[i],0)
        return dp[-1].get(S,0)
                

