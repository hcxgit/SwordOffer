#
# @lc app=leetcode.cn id=215 lang=python3
#
# [215] 数组中的第K个最大元素
#
class Solution:
    def findKthLargest(self, nums, k):
        # nums = sorted(nums)
        # return nums[-k]
        import heapq
        return heapq.nlargest(k,nums)[-1]

