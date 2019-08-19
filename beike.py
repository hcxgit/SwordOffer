# # coding=utf-8
# import sys
#
# N = int(sys.stdin.readline().strip())
# alist = list(map(int, sys.stdin.readline().strip().split()))
#
# if N == 1:
#     print(alist[0],1)
# else:
#     dp = [i for i in alist]
#     flag = [1 if i!=0 else 0 for i in alist ]
#
#     for i in range(2, N):
#         if dp[i]==0:
#             continue
#         maxx = i-2
#         for j in range(i-2,-1,-1):
#             if dp[j]>dp[maxx]:
#                 maxx = j
#         dp[i] += dp[maxx]
#         flag[i] += flag[maxx]
#
#     max_index = [i for i in range(len(dp)) if dp[i] == max(dp)]
#     print(max(dp),min([flag[i] for i in max_index]))

import sys

[N,T,M] = list(map(int,sys.stdin.readline().strip().split()))
alist = list(map(int, sys.stdin.readline().strip().split()))
def iskill(alist, T, M, X):
    for i in alist:
        t = i//X
        if i%X ==1:
            T -= (t+1)
            M -= t
        elif i%X ==0:
            T -= t
            M -= t
        else:
            T -= (t+1)
            M -= (t+1)
        if T < 0:
            return False
    return True
res = -1
for i in range(1,8):
    if iskill(alist,T,M,i):
        res = i
        break
print(res)