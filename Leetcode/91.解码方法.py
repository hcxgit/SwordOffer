#
# @lc app=leetcode.cn id=91 lang=python3
#
# [91] 解码方法
#
class Solution:
    def numDecodings(self, s: str) -> int:
        # dp[i]: 以 i为开始的编码方式的总数
        # dp[i] = dp[i+1] + dp[i+2](if i+2不越界  else +1)  
        # 因为0只能和前面的数组和，所以从后往前遍历
        dp = [0 for i in range(len(s))]
        dp[-1] = 1 if s[-1] != '0' else 0
        for i in range(len(s)-2,-1,-1):
            if s[i] == '0':
                dp[i] = 0
            else:
                 # i和i+1合法，则dp[i] = dp[i+1]+dp[i+2] if i+2合法 else +1
                if 0< int(s[i:i+2]) <= 26:
                    if i+2 <len(s):
                        dp[i] = dp[i+1]+dp[i+2]
                    else:
                        dp[i] = dp[i+1]+1
                # i和i+1不合法， dp=dp[i+1]
                else:
                    dp[i] = dp[i+1]
        return dp[0]
