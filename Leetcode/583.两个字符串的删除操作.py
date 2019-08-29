#
# @lc app=leetcode.cn id=583 lang=python3
#
# [583] 两个字符串的删除操作
#
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        len1 = len(word1)
        len2 = len(word2)
        # dp[i][j]: word1前i个字符和word2前j个字符的最长公共子序列
        dp  = [[0 for i in range(len2+1)] for j in range(len1+1)]
        for i in range(1,len1+1):
            for j in range(1,len2+1):
                if word1[i-1]==word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]+1
                else:
                    dp[i][j] = max(dp[i-1][j],dp[i][j-1])
        return (len1+len2-2*dp[-1][-1])
