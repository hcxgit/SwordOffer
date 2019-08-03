# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:18
'''

class solution:

    # 46、把数字翻译成字符串，计算一个数字有多少种不同的翻译方法
    # f(i) = f(i+1)+g(i,i+1)*f(i+2)
    def count_translate(self, num):
        if num < 10 or 25 < num < 100:
            return 1
        elif 10 <= num <= 25:
            return 2
        elif 10 <= int(str(num)[:2]) <= 25:
            return self.count_translate(int(str(num)[1:]))+self.count_translate(int(str(num)[2:]))
        else:
            return self.count_translate(num[1:])