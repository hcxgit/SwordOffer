# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:18
'''

class solution:

    # 20.表示数值的字符串
    def isNumeric(self, s):
        if not s:
            return False
        self.start = 0
        numeric = self.scanInter(s)  # 判断A部分
        # print('A部分：',numeric)
        if self.start > len(s)-1:
            return numeric
        elif self.start <= len(s)-1 and s[self.start] == '.':
            self.start += 1
            numeric = self.scanUnsignedInter(s)   # 判断B部分
            # print('B部分：', numeric)
            # print('B部分：', self.start)
        if self.start > len(s) - 1:
            return numeric
        elif s[self.start] == 'e' or s[self.start] == 'E':
            self.start +=1
            numeric = numeric and self.scanInter(s)
            # print('C部分：', numeric)
        return numeric and self.start == len(s)
    def scanInter(self,s):  # 有符号数
        if self.start > len(s)-1:
            return False
        elif self.start <= len(s) and s[self.start] == '+' or s[self.start] == '-':
            self.start +=1
        return self.scanUnsignedInter(s)
    def scanUnsignedInter(self,s):    # 无符号数
        before = self.start
        while self.start <= len(s)-1 and s[self.start] >= '0' and s[self.start] <= '9':
            self.start += 1
        return self.start > before

    def isNumeric2(self, s):  # 利用float转换类型，看是否抛出异常
        try:
            s = float(s)
            return True
        except:
            return False
