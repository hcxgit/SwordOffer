# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:19
'''

class solution:

    # 51、求数组中的逆序对的总数
    def Inversepairs(self, data):  # 暴力法
        count = 0
        for i in range(len(data)):
            for j in range(i+1, len(data)):
                if data[i] > data[j]:
                    count += 1
        return count

    count = 0
    def Inversepairs_2(self, alist):
        if len(alist) > 1:
            # print(alist)   # 显示每次排序开始时的list内容
            mid = len(alist) // 2
            leftList = alist[:mid]
            rightList = alist[mid:]
            self.Inversepairs_2(leftList)
            self.Inversepairs_2(rightList)

            # 从左右列表中选择小的值合并，直到其中一个完全合并，另一个直接拼接
            i, j, k = len(leftList)-1, len(rightList)-1, len(alist)-1  # i：左， j:右,  k： 合并的
            while k >= 0:  # 没有合并完时
                if i >= 0 and j >= 0:  # 左右没有遍历完
                    if leftList[i] > rightList[j]:
                        alist[k] = leftList[i]
                        self.count += (j+1)
                        i -= 1
                    else:
                        alist[k] = rightList[j]
                        j -= 1
                    k -= 1
                else:
                    while i >= 0:  # 右遍历完，左直接加上
                        alist[k] = leftList[i]
                        i -= 1
                        k -= 1
                    while j >= 0:  # 左遍历完，右直接加上
                        alist[k] = rightList[j]
                        j -= 1
                        k -= 1
            return self.count