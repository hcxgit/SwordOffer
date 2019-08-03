# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:16
'''

class solution:

    # 17.打印从1到最大的n位数
    def printNumber(self, number):
        isBegin0 = True
        num = ''
        for i in range(len(number)):
            if isBegin0 and number[i] != '0':
                isBegin0 = False
            if not isBegin0:
                num += number[i]
        print(num)

    def printToMaxDigitsRecursivesly(self, number, length, index):
        if index == length - 1:
            self.printNumber(number)
            return
        for i in range(10):
            number[index + 1] = str(i)
            self.printToMaxDigitsRecursivesly(number, length, index + 1)

    def printToMaxDigits(self, n):  # 过程（2位）： [0,0]，[0,1],[0,2]...[0,9],...[1,0],[1,1],[1,2]...[9,9]
        if n <= 0:
            return
        number = ['' for i in range(n)]
        for i in range(10):
            number[0] = str(i)  # 最高位数字
            self.printToMaxDigitsRecursivesly(number, n, 0)
        del number