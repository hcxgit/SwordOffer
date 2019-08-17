"""
小红书笔试：
给定二叉树T（树深度不超过H<=10，深度从1开始，节点个数N<1024，节点编号1~N）的层序和中序遍历，输出T从左向右叶子节点以及树先序和后序遍历序列
"""

class TreeNode(object):
    def __init__(self, x):
        self.left = None
        self.right = None
        self.val = x
  
  
class Solution(object):
    def __init__(self):
        self.leaf = []
  
    def creatTree(self, bfsorder, midorder):
        """
        从中序遍历找出左右子树，然后再从序列层次遍历中找出左右子树序列，重建二叉树
        :param bfsorder:
        :param midorder:
        :return:
        """
        if len(bfsorder) < 1:
            return
        if len(bfsorder) == 1 and bfsorder[0] not in self.leaf:
            self.leaf.append(bfsorder[0])
            # print(self.leaf)

        root = TreeNode(bfsorder[0])
        root_index = midorder.index(root.val)  # 根节点在中序遍历的索引

        left_in = midorder[:root_index]  # 中序遍历的左子树
        right_in = midorder[root_index + 1:]  # 中序遍历的右子树

        left_bsf = [x for x in bfsorder if x in left_in]  # 层次遍历的左子树、
        right_bfs = [x for x in bfsorder if x in right_in] # 层次遍历的右子树
        
        root.left = self.creatTree(left_bsf, left_in)
        root.right = self.creatTree(right_bfs, right_in)
        return root
  
    def preorder(self, root):
        if not root:
            return []
        return [root.val] + self.preorder(root.left) + self.preorder(root.right)
  
    def postorder(self, root):
        if not root:
            return []
        return self.postorder(root.left) + self.postorder(root.right) + [root.val]
  
s = Solution()
bfsorder = [int(x) for x in input().split()]
inorder = [int(x) for x in input().split()]
root = s.creatTree(bfsorder, inorder)
  
pre = s.preorder(root)
post = s.postorder(root)
print(' '.join(list(map(str,s.leaf))))
print(' '.join(list(map(str,pre))))
print(' '.join(list(map(str,post))))
