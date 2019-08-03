# coding=utf-8

'''
Author: cxhao
Date: 2019/7/24 19:43
'''

class Solution:

    def Sum_Solution(self, n):

        return n and n+self.Sum_Solution(n-1)

if __name__ == '__main__':
    # s = Solution().Sum_Solution(3)
    # print(s)
    print(len('ww'))