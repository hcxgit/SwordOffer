# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:19
'''

class solution:

    # -----------63、股票的最大利润---------------
    def max_diff(self, numbers):
        """
        1、蛮力法  
        2、标准动态规划  dp[0][n] = max(dp[1][n-1]+numbers[n],dp[0][n-1])
        :return: int
        """
        dp = [[0 for i in range(len(numbers))] for j in range(2)]  # dp[][n]第n天的最大利润
        dp[1][0] = -numbers[0]                   # 0表示 手里没股票， 1表示手里有股票
        res = 0
        for i in range(1,len(numbers)):
            dp[0][i] = max(dp[0][i-1], dp[1][i-1]+numbers[i])
            dp[1][i] = max(-numbers[i-1], dp[1][i-1])
            if dp[0][i] > res:
                res = dp[0][i]
        return res