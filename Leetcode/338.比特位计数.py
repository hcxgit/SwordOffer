#
# @lc app=leetcode.cn id=338 lang=python3
#
# [338] 比特位计数
#
class Solution:
    def countBits(self, num: int):
        # 普通做法
        """
        res = []
        for i in range(num+1):
            temp = 0
            while i:
                temp += 1
                i = i&(i-1)
            res.append(temp)
        return res
        """
        # dp
        """
        n&(n-1)  会将n的最右边的1变成0
        dp[n] = dp[n&(n-1)]+1    # 数字n的1的个数
        eg: 6:110  6&5: 100   dp[6] = dp[6&5]+1
        """
        dp = [0 for i in range(num+1)]
        for i in range(1,num+1):
            dp[i] = dp[i&(i-1)]+1
        return dp
