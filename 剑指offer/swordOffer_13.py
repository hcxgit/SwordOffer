# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:16
'''

class solution:

    # 13. 机器人的运动范围
    # -*- coding:utf-8 -*-
    def __init__(self):
        self.number = 0

    def movingCount(self, threshold, rows, cols):
        # write code here
        if rows < 1 or cols < 1 or threshold < 0:
            return 0
        visited = [False for i in range(rows * cols)]
        self.countCore(threshold, rows, cols, 0, 0, visited)
        del visited
        return self.number

    def countCore(self, threshold, rows, cols, curRow, curCol, visited):
        if curRow >= 0 and curCol >= 0 and curRow < rows and curCol < cols and (self.sumK(curRow) + self.sumK(curCol)) <= threshold and not visited[curRow * cols + curCol]:
            visited[curRow * cols + curCol] = True
            self.number += 1
            self.countCore(threshold, rows, cols, curRow + 1, curCol, visited)
            self.countCore(threshold, rows, cols, curRow, curCol + 1, visited)
            self.countCore(threshold, rows, cols, curRow - 1, curCol, visited)
            self.countCore(threshold, rows, cols, curRow, curCol - 1, visited)

    def sumK(self, ans):
        count = 0
        while ans > 0:
            count += ans % 10
            ans = ans // 10
        return count