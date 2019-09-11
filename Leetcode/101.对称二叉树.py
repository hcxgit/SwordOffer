#
# @lc app=leetcode.cn id=101 lang=python3
#
# [101] 对称二叉树
#
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        # 1、递归查看左子树和右子树是否相同，如果左空，返回右是否空；否则如果右空，返回false；
        # 否则如果左等于右，递归查看左的左和右的右以及坐的右和右的左
        # if not root:
        #     return True
        # return self.isTrue(root.left,root.right)

        # 2、对比先序和对称先序是否一样
        def preorder(root,res):
            if not root:
                res.append('')
                return 
            res.append(root.val)
            preorder(root.left,res)
            preorder(root.right,res)
        def preorder2(root,res):
            if not root:
                res.append('')
                return 
            res.append(root.val)
            preorder2(root.right,res)
            preorder2(root.left,res)
        res1,res2 = [],[]
        preorder(root,res1)
        preorder2(root,res2)
        if res1 == res2:
            return True
        else:
            return False
    def isTrue(self,left,right):
        if not left:
            return not right
        elif not right:
            return False
        else:
            if left.val == right.val:
                return self.isTrue(left.left,right.right) and self.isTrue(left.right,right.left)
            else:
                return False
