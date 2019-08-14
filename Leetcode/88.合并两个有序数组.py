#
# @lc app=leetcode.cn id=88 lang=python3
#
# [88] 合并两个有序数组
#
class Solution:
    def merge(self, nums1, m: int, nums2, n):
        """
        Do not return anything, modify nums1 in-place instead.
        两个指针，从后往前扫描`
        """
        m,n,mer = m-1,n-1,len(nums1)-1
        while m >= 0 and n >= 0:
            if nums2[n]>=nums1[m]:
                nums1[mer] = nums2[n]
                n -= 1
            else:
                nums1[mer] = nums1[m]
                m -= 1
            mer -= 1
        while n >= 0:
            nums1[mer] = nums2[n]
            n -= 1
            mer -= 1
        return nums1
