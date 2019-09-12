#
# @lc app=leetcode.cn id=322 lang=python3
#
# [322] 零钱兑换
#
class Solution:
    def coinChange(self, coins, amount):
        dp = [amount+1 for i in range(amount+1)]
        dp[0] = 0
        for i in range(1,amount+1):
            for j in range(len(coins)):
                if coins[j] <= i:
                    dp[i] = min(dp[i],dp[i-coins[j]]+1)
        return -1 if dp[-1] == amount+1 else dp[-1]


