# coding=utf-8

'''
Author: cxhao
Date: 2019/7/24 20:28
'''

#  字符串转换成整数

# -*- coding:utf-8 -*-
class Solution:
    def StrToInt(self, s):
        # write code here
        if not s:
            return 0
        flag = 1
        index = 0
        if s[0] == '-':
            flag = -1
            index = 1
        if s[0] == '+':
            index = 1
        num = 0
        sets = {'0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9}  # 或者用 ord(s[i])-ord('0')
        for i in range(index,len(s)):
            if s[i] in sets.keys():
                num += sets[s[i]]
                num = num * 10
            else:
                return 0
        num = num // 10
        return flag*num
    def quicksort(self,left,right,alist):
        mid = alist[left]
        while left !    = right:
            while alist[right] > mid:
                right -= 1
            while alist[left] <= mid:
                left += 1
            alist[left], alist[right] = alist[right], alist[left]

if __name__ == '__main__':
    s = Solution().StrToInt('-qwe')
    print(s)