#
# @lc app=leetcode.cn id=5 lang=python3
#
# [5] 最长回文子串
#


class Solution:
    def longestPalindrome(self, s: str) -> str:
        dp = [1 for i in range(len(s))]  # 以第i个字符为结尾的最长回文子串的长度

        for i in range(len(s)):
            print(i)
            return dp

    def isPalindrome(self, s):
        length = len(s)
        if length <= 1:
            return True
        strat, end = 0, length-1
        while strat < end:
            if s[strat] == s[end]:
                strat += 1
                end -= 1
            else:
                return False
        return True
