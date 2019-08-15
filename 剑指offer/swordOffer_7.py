# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 10:14
'''

class solution:

    # 7.根据前序和中序遍历，重建二叉树
    def reConstructBinaryTree(self, pre, tin):    # 返回构造的TreeNode根节点
        # write code here
        if not pre:
            return None
        tree_root = TreeNode(pre[0])
        # 中序遍历找根节点
        for i in range(len(tin)):
            if tin[i] == pre[0]:
                mid = i
                break
        pre = pre[1:]
        # 左子树
        tree_root.left = self.reConstructBinaryTree(pre[:mid], tin[:mid])
        # 右子树非空
        tree_root.right = self.reConstructBinaryTree(pre[mid:], tin[mid + 1:])
        return tree_root
