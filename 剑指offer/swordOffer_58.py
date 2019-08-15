# coding=utf-8

'''
Author: cxhao
Date: 2019/7/25 15:42
'''

#  坐旋转字符串 （两种方法）
class Solution:
    # 二、3次翻转，   已知xy  求yx    yx = (xTyT)T  T指翻转，看成转置
    def LeftRotateString(self, s, n):
        if not s:
            return ''
        s = self.reservesString(s[:n])+self.reservesString(s[n:])
        return self.reservesString(s)
    def reservesString(self,s):
        s2 = ''
        for i in s:
            s2 = i + s2
        return s2
    '''
    # 一、字符串剪切
    def LeftRotateString(self, s, n):
        # write code here
        if not s:
            return ''
        length = n%len(s)
        return s[length:]+s[:length]
    '''
if __name__ == '__main__':
