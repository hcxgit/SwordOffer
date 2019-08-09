#
# @lc app=leetcode.cn id=1 lang=python3
#
# [1] 两数之和
#
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dic = []
        for i in range(len(nums)):
            if target-nums[i] in dic:
                return [dic.index(target-nums[i]),i]
            else:
                dic.append(nums[i])
        return []
    
