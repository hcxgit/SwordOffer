# 快排
def quick(alist,left,right):
    if left >= right:
        return
    l,h,flag = left,right,left
    while l < h:
        while l < h and  alist[h] > alist[flag]:
            h -= 1
        while l < h and alist[l] <= alist[flag]:
            l += 1
        alist[l],alist[h] = alist[h],alist[l]
    alist[flag],alist[l] = alist[l],alist[flag]
    quick(alist,left,l-1)
    quick(alist,l+1,right)
#  堆排序
def heap(alist):
    length = len(alist)
    buildHeap(alist)
    for i in range(length-1,-1,-1):
        alist[0],alist[i] = alist[i],alist[0]
        adjustHeap(alist,0,i)
def buildHeap(alist):
    for i in range(len(alist)//2-1,-1,-1):
        adjustHeap(alist,i,len(alist))
def adjustHeap(alist,root,lenght):
    left = 2*root+1
    right = left + 1
    if right > lenght-1:
        return
    m = root
    if alist[left] > alist[m]:
        m = left
    if alist[right] > alist[m]:
        m = right
    if root != m:
        alist[root],alist[m] = alist[m],alist[root]
        adjustHeap(alist,m,lenght)
# 归并排序
def merge(alist):
    if len(alist) <= 1:
        return alist
    mid = len(alist)//2
    left = merge(alist[:mid])
    right = merge(alist[mid:])

    l,r,k = 0,0,0
    while l < len(left) and r < len(right):
        if left[l] <= right[r]:
            alist[k] = left[l]
            l += 1
        else:
            alist[k] = right[r]
            r += 1
        k += 1
    if l < len(left):
        alist[k:] = left[l:]
    else:
        alist[k:] = right[r:]
    return alist
    
def bubble(alist):
    length = len(alist)
    for i in range(length-1):
        for j in range(length-1-i):
            if alist[j] > alist[j+1]:
                alist[j],alist[j+1] = alist[j+1],alist[j]
 
def select(alist):
    length = len(alist)
    for i in range(length-1):
        mim = i
        for j in range(i+1,length):
            if alist[j] < alist[mim]:
                mim = j
        alist[i],alist[mim] = alist[mim],alist[i]

def insert(alist):
    length = len(alist)
    for i in range(1,length):
        for j in range(i,0,-1):
            if alist[j] >= alist[j-1]:
                break
            else:
                alist[j],alist[j-1] = alist[j-1],alist[j]

def pailie(alist,res,res_i):
    if len(alist) == 0:
        res.append(res_i)
        return res
    for i in range(len(alist)):
        res = pailie(alist[:i]+alist[i+1:],res,res_i+[alist[i]])
    return res
alist = [3,5,2,9,4,1,7,6]
# heap(alist)
# alist = merge(alist)
insert(alist)
# alist = pailie(['a','b','c'],[],[])
print(alist)
print(hex(int('110',base=2)))
