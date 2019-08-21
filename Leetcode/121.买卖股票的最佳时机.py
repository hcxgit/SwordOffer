#
# @lc app=leetcode.cn id=121 lang=python3
#
# [121] 买卖股票的最佳时机
#
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # dp[i][j]  前i天的最大利润，i表示手里有没有股票0,1
        if not prices:
            return 0
        dp = [[0 for i in range(len(prices))] for j in range(2)]
        dp[1][0] = -prices[0]
        for i in range(1,len(prices)):
            dp[0][i] = max(dp[0][i-1],dp[1][i-1]+prices[i])
            dp[1][i] = max(dp[1][i-1],-prices[i])
        return dp[0][-1]

