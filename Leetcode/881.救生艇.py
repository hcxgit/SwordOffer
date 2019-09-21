#
# @lc app=leetcode.cn id=881 lang=python3
#
# [881] 救生艇
#
class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people.sort()
        l, r = 0, len(people)-1
        count = 0
        while l < r:
            if people[l] + people[r] >limit:
                r -= 1
            else:
                l += 1
                r -= 1
            count += 1
        if l == r:
            count += 1
        return count
