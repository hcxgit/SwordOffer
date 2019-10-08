#
# @lc app=leetcode.cn id=2 lang=python3
#
# [2] 两数相加
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        num1, num2,N =0,0,1
        while l1:
            num1 += l1.val*N
            l1 = l1.next
            N = N*10
        N = 1 
        while l2:
            num1 += l2.val*N
            l2 = l2.next
            N = N*10
        num = num1+num2
        head = ListNode(num%10)
        num = num//10
        l3 = head
        while num:
            node = ListNode(num%10)
            l3.next = node
            l3 = node
            num = num//10
        return head

# @lc code=end

