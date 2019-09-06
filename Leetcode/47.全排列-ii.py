#
# @lc app=leetcode.cn id=47 lang=python3
#
# [47] 全排列 II
#
class Solution:
    def permuteUnique(self, nums):
        res = []
        def backtrack(nums,res_i):
            if not nums:
                res.append(res_i)
                return
            temp = set() # 去重
            for i in range(len(nums)):
                if nums[i] not in temp:
                    temp.add(nums[i])
                    backtrack(nums[:i]+nums[i+1:],res_i+[nums[i]])
        backtrack(nums,[])
        return res

