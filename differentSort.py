# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 13:26
'''

class sorts:
    def quick_sort(self, alist, left, right):
        if left <= right:
            return
        flag = left

        self.quick_sort(alist, left, flag-1)
        self.quick_sort(alist, flag+1, right)
    def merge_sort(self,alist):
        return
    def heap_sort(self,alist):
        return
if __name__ == '__main__':
    s = sorts
    s.quick_sort()