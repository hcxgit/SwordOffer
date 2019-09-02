#
# @lc app=leetcode.cn id=46 lang=python3
#
# [46] 全排列
#
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []
        def backtrack(nums,res_i):
            if not nums: # 空，说明每个都遍历了，返回
                res.append(res_i)
                return
            for i in range(len(nums)):
                backtrack(nums[:i]+nums[i+1:],res_i+[nums[i]])
        backtrack(nums,[])
        return res           

# import sys
# [t,k] = map(int,sys.stdin.readline().strip().split())
# res = []
# for i in range(t):
#         count = 0
#         [l,r] = map(int,sys.stdin.readline().strip().split())
#         for i in range(1,r+1):
#                 if i==1:
#                         dp1,dp2 = 1,1
#                 elif i==2:
#                         dp1,dp2 == 1,2
#                 else:
#                         dp1,dp2 = dp2,dp1+dp2
#                 if i>=l:
#                         count += dp2
#         res.append(count)
# for i in res:
#         print(i)

