#
# @lc app=leetcode.cn id=46 lang=python3
#
# [46] 全排列
#
class Solution:
    def permute(self, nums):
        res = []
        def backtrack(nums,res_i):
            if not nums: # 空，说明每个都遍历了，返回
                res.append(res_i)
                return
            for i in range(len(nums)):
                backtrack(nums[:i]+nums[i+1:],res_i+[nums[i]])
        backtrack(nums,[])
        return res           

