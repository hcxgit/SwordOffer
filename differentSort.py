# coding=utf-8

'''
Author: cxhao
Date: 2019/7/29 13:26
'''

class sorts:
    # -------------快排-------------
    def quick_sort(self, alist, left, right):
        if left >= right:
            return
        flag,start,end = left,left,right
        while start != end:
            while start < end and alist[end] > alist[flag]:
                end -= 1
            while start < end and alist[start] <= alist[flag]:
                start += 1
            if start != end:
                alist[start],alist[end] = alist[end],alist[start]
        alist[flag],alist[start] = alist[start],alist[flag]

        self.quick_sort(alist, left, start-1)
        self.quick_sort(alist, start+1, right)

    # --------------归并排序-------------
    def merge_sort(self,alist):
        # 如果长度大于1，划分两半
        if len(alist) > 1:
            mid = len(alist)//2
            left = alist[:mid]
            right = alist[mid:]

            # 递归排序左右两部分
            self.merge_sort(left)
            self.merge_sort(right)

            # 合并左右两部分的结果
            l,r,m = 0,0,0  # left.right,merge
            while l < len(left) and r < len(right):
                if left[l] < right[r]:
                    alist[m] = left[l]
                    l += 1
                else:
                    alist[m] = right[r]
                    r += 1
                m += 1
            while l < len(left) : # 右合并完了，左直接加上去
                alist[m] = left[l]
                m += 1
                l += 1
            while r < len(right) : # 左合并完了，右直接加上去
                alist[m] = right[r]
                m += 1
                r += 1

    # --------------堆排序---------------
    def heap_sort(self,alist):
        self.heapSize = len(alist)
        self.buildHeap(alist)

        # 从将根节点开始，取出第一位数与最后一位做对调，对前面的节点继续进行调整
        for i in range(self.heapSize-1,-1,-1): # 最后一个节点在往前提
            alist[i], alist[0] = alist[0], alist[i]
            self.adjustHeap(alist,0,i)

    def buildHeap(self,alist):   # 建堆

        for i in range((self.heapSize-2)//2,-1,-1):
            self.adjustHeap(alist,i,self.heapSize)

    def adjustHeap(self,alist,root,last): #在堆中做结构调整使得父节点的值大于子节点
        left = 2*root+1
        right = left+1
        larger = root
        if left < last and alist[left] > alist[larger]:
            larger = left
        if right < last and alist[right] > alist[larger]:
            larger = right
        if larger != root:
            alist[larger],alist[root] = alist[root],alist[larger]
            self.adjustHeap(alist,larger,last)  # 递归调整动过的子树

    # ----------全排列-------------------
    def permutations(self, arr, start, end):

        if start == end:
            # print(arr)
            pass
        else:
            for index in range(start, end):
                arr[index], arr[start] = arr[start], arr[index]
                print(index,start)
                self.permutations(arr, start + 1, end)
                arr[index], arr[start] = arr[start], arr[index]


if __name__ == '__main__':
    s = sorts()
    a = [4,6,2,1,7,89,13,5,4,6]
    s.heap_sort(a)
    print(a)
    # arr = list(range(1,4))
    # s.permutations(arr, 0, len(arr))
