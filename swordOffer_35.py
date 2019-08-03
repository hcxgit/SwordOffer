# coding=utf-8

'''
Author: cxhao
Date: 2019/7/23 15:00
'''

# class RandomListNode:
#     def __init__(self, x):
#         self.label = x
#         self.next = None
#         self.random = None

class Solution:
    # 返回 RandomListNode
    def Clone(self, pHead):
        if not pHead:
            return None
        pHead1 = pHead
        while pHead1:
            node = RandomListNode(pHead1.label)
            node.next = pHead1.next
            pHead1.next = node
            pHead1 = node.next
        pHead1 = pHead
        while pHead1:
            if pHead1.random:
                pHead1.next.random = pHead1.random.next
            pHead1 = pHead1.next.next
        pHead1 = pHead.next
        pHead2 = pHead1
        while pHead:
            if pHead.next.next:
                pHead.next = pHead.next.next
            else:
                pHead.next = None
            if pHead2.next:
                pHead2.next = pHead2.next.next
            pHead, pHead2 = pHead.next, pHead2.next

        return pHead1