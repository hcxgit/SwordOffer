# coding=utf-8

'''
Author: cxhao
Date: 2019/8/5 14:53
'''

class Solution:
    # 这里要特别注意~找到任意重复的一个值并赋值到duplication[0]
    # 函数返回True/False
    def duplicate(self, numbers, duplication):
        """ 一
        for n in numbers:
            if n in duplication:
                duplication[0]=n
                return True
            else:
                duplication.append(n)
        return False
        """
        # 二
        nlen = len(numbers)
        if nlen < 2:
            return False
        else:
            for l in range(nlen):
                while l != numbers[l]:
                    temp = numbers[l]
                    if temp == numbers[temp]:
                        duplication[0]= temp
                        return True
                    else:
                        numbers[temp], numbers[l]= temp,numbers[temp]
            return False