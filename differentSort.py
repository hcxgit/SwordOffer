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
    # s = sorts()
    # # s.quick_sort()
    # arr = list(range(1,4))
    # s.permutations(arr, 0, len(arr))

    import sys
    res = []
    while True:
        n_num = sys.stdin.readline().strip()
        if not n_num:
            break
        n_num = list(map(int,n_num.split()))
        n_list = list(map(int,sys.stdin.readline().strip().split()))

        allnum = sum(n_list)
        if allnum%n_num[1] == 0:
            need = allnum//n_num[1]
        else:
            need = (allnum//n_num[1])+1
        if n_num[2]*60 > need and need<=480:
            res.append(need)
        elif allnum-n_num[2] <=480:
            res.append(allnum-n_num[2])
        else:
            res.append(0)
    for i in res:
        print(i)
