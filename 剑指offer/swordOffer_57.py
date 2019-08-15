# coding=utf-8

'''
Author: cxhao
Date: 2019/7/25 14:51
'''

# -*- coding:utf-8 -*-

#  找到和为tsum的连续序列

class Solution:
    def FindContinuousSequence(self, tsum):
        # write code here
        res = []
        left,right = 1,2
        summ = 3
        while right < tsum:
            if summ < tsum:
                right += 1
                summ += right
            elif summ == tsum:
                res.append([i for i in range(left, right+1)])
                right += 1
                summ += right
            else:
                summ -= left
                left += 1
        return res
if __name__ == '__main__':
    result = Solution().FindContinuousSequence(5)
    print(result)