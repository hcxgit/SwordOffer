#
# @lc app=leetcode.cn id=215 lang=python3
#
# [215] 数组中的第K个最大元素
#
class Solution:
    def findKthLargest(self, nums, k):
        # nums = sorted(nums)
        # return nums[-k]
        # import heapq
        # return heapq.nlargest(k,nums)[-1]
        def buildHeapq(alist):
            length = len(alist)
            for i in range(length//2-1,-1,-1):
                adjustHeapq(i,alist)
        def adjustHeapq(root,alist):
            if root > len(alist)//2-1:
                return
            left = 2*root+1
            right = left+1
            minn = root
            if alist[left] < alist[minn]:
                minn = left
            if right < len(alist) and alist[right] < alist[minn]:
                minn = right
            if minn != root:
                alist[root],alist[minn] = alist[minn],alist[root]
                adjustHeapq(minn,alist)
        res = nums[:k]
        buildHeapq(res)
        for i in nums[k:]:
            if i > res[0]:
                res[0] = i
                adjustHeapq(0,res)
        return res[0]
