#
# @lc app=leetcode.cn id=1011 lang=python3
#
# [1011] 在 D 天内送达包裹的能力
#
class Solution:
    def shipWithinDays(self, weights, D: int) -> int:
        l,r = max(weights),sum(weights)
        res = 0
        while l <= r:
            mid = (l+r)//2
            if self.isTrue(weights,D,mid):
                res = mid
                r = mid-1
            else:
                l = mid+1
        return res
    def isTrue(self, weights, D, w): # 运载能力为w，船是否能运走
        temp = w
        for i in weights:
            if temp >= i:
                temp = temp -i
            else:
                D -= 1
                temp = w-i
            if D < 1:
                return False
        return True
