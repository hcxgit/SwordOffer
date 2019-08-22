#
# @lc app=leetcode.cn id=647 lang=python3
#
# [647] 回文子串
#
class Solution:
    def countSubstrings(self, s: str) -> int:
        length = len(s)
        if not length:
            return 0
        # dp[i][j]: i——j能否组成回文子串
        dp = [[0 for i in range(length)] for k in range(length)]
        for i in range(length):
            dp[i][i] = 1
        for j in range(1,length):
            for i in range(j):
                if s[i]==s[j] and ((j-i)<=2 or dp[i+1][j-1]==1):
                    dp[i][j] = 1
        res = len([1 for row in dp for line in row if line ==1])
        return res

