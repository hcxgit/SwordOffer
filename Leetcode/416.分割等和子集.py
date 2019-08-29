#
# @lc app=leetcode.cn id=416 lang=python3
#
# [416] 分割等和子集
#
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        """
        sums = sum(nums)
        if sums%2 != 0:
            return False
        target = sums//2
        # dp[i][j]：前i个数里能否找到和为j的数
        dp = [[False for j in range(target+1)] for i in range(len(nums))]
        for i in range(target+1):
            if nums[0]==i:
                dp[0][i] = True
                break
        for i in range(1,len(nums)):
            for j in range(target+1):
                if j >=nums[i]:
                    # 已经找到了则不选，没有则看前一个有没有
                    dp[i][j] = dp[i-1][j] or dp[i-1][j-nums[i]]
                else:
                    dp[i][j] = dp[i-1][j]
        return dp[-1][-1]
        """
         # 从第 2 行以后，当前行的结果参考了上一行的结果，
         # 因此使用一维数组定义状态就可以了
        sums = sum(nums)
        if sums%2 != 0:
            return False
        target = sums//2
        # dp[i]：前i个数里能否找到和为j的数
        dp = [False for j in range(target+1)]
        for i in range(target+1):
            if nums[0]==i:
                dp[i] = True
                break
        for i in range(1,len(nums)):
            # 因为后面的参考了前面的，我们从后向前计算
            # 先判断最后一个，一旦成立则提前终止
            dp[-1] = dp[-1] or dp[target-nums[i]]
            if dp[-1]:
                return True
            for j in range(target-1,-1,-1): # 再从倒数第二、三...判断
                if j >=nums[i]:
                    # 已经找到了则不选，没有则看前一个有没有
                    dp[j] = dp[j] or dp[j-nums[i]]
                else:
                    # 后面的容量越来越小，因此没有必要再判断了，退出当前循环
                    break
        return dp[-1]
