#
# @lc app=leetcode.cn id=94 lang=python3
#
# [94] 二叉树的中序遍历
#
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        # # 递归
        # def midOrder(root,res):
        #     if not root:
        #         return
        #     if root.left:
        #         midOrder(root.left,res)
        #     res.append(root.val)
        #     if root.right:
        #         midOrder(root.right,res)
        # 非递归
        def midOrder(root,res):
            if not root:
                return
            stack = []
            while stack:
                while root:
                    stack.append(root)
                    root = root.left
                if stack:
                    root = stack.pop()
                    res.append(root.val)
                    root = root.right
        res = []
        midOrder(root,res)
        return res
