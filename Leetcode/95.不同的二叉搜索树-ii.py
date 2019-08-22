#
# @lc app=leetcode.cn id=95 lang=python3
#
# [95] 不同的二叉搜索树 II
#
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def generateTrees(self, n: int) -> List[TreeNode]:
        
        def generateChildTrees(start,end):
            if start>end: # 只剩一个节点时，左右子树都填None
                return[None]
            res = []
            for i in range(start,end+1):
                leftTree = generateChildTrees(start,i-1)
                rightTree = generateChildTrees(i+1,end)

                for l in leftTree:
                    for r in rightTree:
                        root = TreeNode(i)
                        root.left = l
                        root.right = r
                        res.append(root)
            return res
        return generateChildTrees(1,n) if n else []


