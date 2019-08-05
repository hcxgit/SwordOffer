# coding=utf-8

'''
Author: cxhao
Date: 2019/8/5 15:00
'''

class Solution:
    # s 源字符串
    def replaceSpace(self, s):
        # write code here
        '''
        b = ''
        for i in s:
            if i == ' ':
                b += '%20'
            else:
                b += i
        return b
        '''
        s = list(s)
        count=len(s)
        for i in range(0,count):
            if s[i]==' ':
                s[i]='%20'
        return ''.join(s)