# coding=utf-8

'''
Author: cxhao
Date: 2019/7/26 13:35
'''

'''
32、
3：之字形打印二叉树
'''


# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def Print(self, pRoot):
        # write code here
        if not pRoot:
            return []
        stack1 = [pRoot]
        stack2 = []
        res = []
        flag = True  # 控制入哪个栈 True是左到右遍历的这一层
        while stack1 or stack2:
            r = []
            if flag: # 左到右遍历
                while stack1:
                    node = stack1.pop()
                    r.append(node.val)
                    if node.left:
                        stack2.append(node.left)
                    if node.right:
                        stack2.append(node.right)
                flag = False
            else:    # 右到左
                while stack2:
                    node = stack2.pop()
                    r.append(node.val)
                    if node.right:
                        stack1.append(node.right)
                    if node.left:
                        stack1.append(node.left)
                flag = True
            res.append(r)
        return res
