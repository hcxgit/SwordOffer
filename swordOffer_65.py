# coding=utf-8

'''
Author: cxhao
Date: 2019/7/19 12:13
'''

class Solution:

    def Add(self, num1, num2):
        # write code here
        """ 位运算 """
        for i in range(5):
            new_num_1 = num1 ^ num2  # 异或得到不进位的数
            new_num_2 = (num1 & num2)  # 与运算然后左移得到进位的数
            print('new_num_1',new_num_1)
            print('new_num_2',new_num_2)
            if new_num_2 == 0:
                break
            new_num_2 = new_num_2 << 1
            num1, num2 = new_num_1, new_num_2
        return new_num_1

if __name__ == '__main__':
    s = Solution().Add(-2,3)
    print(s)
    # print(((-2) ^ 0xFFFFFFFF))
