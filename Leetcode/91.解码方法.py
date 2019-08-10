#
# @lc app=leetcode.cn id=91 lang=python3
#
# [91] 解码方法
#
class Solution:
    def numDecodings(self, s: str) -> int:
        # dp[i]: 以 i为结尾的编码方式的总数
        # dp[i] = dp[i+1] + (1 if ....)  因为0只能和前面的数组和，所以从后往前遍历
        if s < '1':
            return 0
        elif s=='' or '1' <= s < '10':
            return 1
        elif '10'< s <= '26':
            return 2
        s1,s2 = 0,0
        if '0'<s[:1]<='26':
            s1 = self.numDecodings(s[1:])
        if '0'<s[:2]<='26':
            s2 = self.numDecodings(s[2:])
        return s1+s2
