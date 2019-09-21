#
# @lc app=leetcode.cn id=354 lang=python3
#
# [354] 俄罗斯套娃信封问题
#
class Solution:
    def maxEnvelopes(self, envelopes) -> int:
        if not envelopes:
            return 0
        # 排序，w升序，h降序（防止w相同时多计算）
        envelopes.sort(key=lambda x: (x[0],-x[1]))
        # 降维，只用h就可以了
        envelopes = [i[1] for i in envelopes]
        #  方法一、 动态规划（超时）
        """
        length = len(envelopes)
        dp = [1 for i in range(length)]
        for i in range(1,length):
            for j in range(i):
                if envelopes[i]>envelopes[j]:
                    dp[i] = max(dp[i],dp[j]+1)
        return max(dp)
        """
        # 方法二、贪心+二分查找
        # tail[i] 表示长度为 i + 1的所有“上升子序列”里结尾最小的元素。
        #  因此，有序数组 tail 的长度就是题目所求的“最长上升子序列”的长度。
        size = len(envelopes)
        tail = [envelopes[0]] 
        for i in range(1, size):
            target = envelopes[i]
            if target > tail[-1]:  # 先尝试是否可以接在末尾
                tail.append(target)
                continue
            # 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
            left,right = 0, len(tail) - 1
            while left < right:
                mid = (left + right) >> 1
                if tail[mid] < target:
                    left = mid + 1
                else:
                    right = mid
            tail[left] = target
        return len(tail)

