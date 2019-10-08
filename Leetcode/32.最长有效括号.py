#
# @lc app=leetcode.cn id=32 lang=python3
#
# [32] 最长有效括号
#
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        length = len(s)
        if length <2:
            return 0
        dp = [0 for i in range(length)]  #以第i个字符为结尾的。。。的长度
        if s[0] == '(' and s[1] == ')':
            dp[1] = 2
        for i in range(2,length):
            if s[i] == ')':
                # 1、如....()的字符串
                # dp[i] = dp[i-2]+2
                if s[i-1] == '(':
                    dp[i] = dp[i-2]+2
                # 2、如 ....))的字符串：
                # 如果s[i-dp[i - 1]-1] = '('  第i-1个字符对应的'('的前一个字符
                # 那么dp[i]=dp[i−1]+2+(dp[i−dp[i−1]−2] if ,,,>0 else 0)
                elif i-dp[i-1]>0 and s[i-dp[i-1]-1]=='(':              
                    if i-dp[i-1]>=2: # 前面超过两个字符，还有可能有
                        dp[i] = dp[i-1]+2+dp[i-dp[i-1]-2]
                    else:
                        dp[i] = dp[i-1]+2 
        return max(dp)
