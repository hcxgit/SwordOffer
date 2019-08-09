#
# @lc app=leetcode.cn id=5 lang=python3
#
# [5] 最长回文子串
#


class Solution:
    def longestPalindrome(self, s: str) -> str:
        length= len(s)
        if length < 2:
            return s
        dp = [[0 for i in range(length)] for j in range(length)]  # dp[i][j]：表示s[i,j]能否构成回文子串(闭环，包括j)
        
        res = s[0]
        max_len = 1
        for r in range(1,length):
            for l in range(r):   # 左边 < 右边
                if s[l] == s[r] and (r-l <=2 or dp[l+1][r-1]): # 左右各缩进1后小于等于1 或者 True
                    dp[l][r] = True
                    cur_len = r-l+1
                    if cur_len > max_len:
                        max_len = cur_len
                        res = s[l:r+1]    
        return res

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
