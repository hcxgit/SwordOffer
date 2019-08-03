# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:15
'''


class solution:

    # 12.矩阵中的路径
    # -*- coding:utf-8 -*-
    def hasPath(self, matrix, rows, cols, path):
        # write code here
        if path is None or path == '' or rows <= 0 or cols <= 0:
            return False
        visited = [False for i in range(rows * cols)]
        curPath = 0
        for i in range(rows):
            for j in range(cols):
                if self.isHasPath(matrix, rows, cols, i, j, path, curPath, visited):
                    return True
        return False

    def isHasPath(self, matrix, rows, cols, curRow, curCol, path, curPath, visited):
        if curPath == len(path):
            return True
        hasPath = False
        if curRow >= 0 and curRow < rows and curCol >= 0 and curCol < cols and matrix[curRow * cols + curCol] == \
                path[curPath] and not visited[curRow * cols + curCol]:
            curPath += 1
            visited[curRow * cols + curCol] = True
            hasPath = self.isHasPath(matrix, rows, cols, curRow, curCol - 1, path, curPath,visited) or \
                      self.isHasPath(matrix, rows, cols, curRow - 1, curCol, path,curPath, visited) or \
                      self.isHasPath(matrix, rows,cols, curRow,curCol + 1,path, curPath,visited) or \
                      self.isHasPath(matrix, rows, cols, curRow + 1, curCol, path, curPath, visited)
            if not hasPath:
                curPath -= 1
                visited[curRow * cols + curCol] = False
        return hasPath
