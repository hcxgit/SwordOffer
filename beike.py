# coding=utf-8
import sys
#
# alist = list(map(int, sys.stdin.readline().strip().split()))
# if max(alist)==1:
#     frist,second = 0,0
#     for i in range(len(alist)):
#         if alist[i] == 1:
#             frist = i
#         if alist[i] == 0:
#             second = i
#     print(frist, second)
# else:
#     index = alist.index(max(alist))+1
#     for i in range(len(alist)):
#         if alist[i] == index+1:
#             frist = index*10+i
#     if frist//10==2:
#         res = []
#         for i in range(len(alist)):
#             if i ==0 and alist[0] != 2:
#                 res.append(i)
#             elif i ==1 and alist[1] != 13:
#                 res.append(i)
#             elif i == 2 and alist[2] != 10:
#                 res.append(i)
#             elif i not in {0,1,2} and alist[i] != 3:
#                 res.append(i)
#     print(frist,res[0]*10+res[1])
#     print(frist,res[1]*10+res[0])
#


flag = True
while True:
    res = []
    s = sys.stdin.readline().strip()
    s = s.split('"')
    def cou(s):
        n = 0
        for i in s:
            if i ==',':
                n +=1
        return n
    for i in s:
        c = cou(i)
        if c == 1:
            res.append(i+'"')
        elif c>1:
            i = i.split(',')
            for j in range(len(i)):
                if i[j]!='':
                    res.append(i[j])
                elif i[j]=='' and j!=len(i)-1:
                    res.append('--')
    if len(res) != 0:
        print(len(res))
        for i in res:
            print(i)
    else:
        print(-1)
        flag = False
