# coding=utf-8

"""
'1'->A     ...  ‘26’——>Z
求有几种方式
"""
import sys
s = sys.stdin.readline().strip()
dp = [0 for i in range(len(s))]
dp[-1] = 0 if s[-1] == '0' else 1
for i in range(len(s)-2, -1, -1):
    if s[i] == '0':
        dp[i] = 0
    else:
        dp[i] = dp[i+1]
        if int(s[i])*10+int(s[i+1]) <= 26:
            if i+2 < len(s):
                dp[i] += dp[i+2]
            else:
                dp[i] += 1
print(dp[0])




import sys
[h, w] = map(int, sys.stdin.readline().strip().split())
P = [[0 for i in range(h)] for l in range(h)]
for i in range(h):
    alist = list(map(int, sys.stdin.readline().strip().split()))
    P[i] = alist
m = int(sys.stdin.readline().strip())
K = [[0 for i in range(m)] for k in range(m)]
for i in range(m):
    alist = list(map(float, sys.stdin.readline().strip().split()))
    K[i] = alist
def O(i,j,m, P, K):
    res = 0
    for x in range(m):
        for y in range(m):
            res += P[i+x][j+y]*K[x][y]
    return str(min(int(res),255))
O_ = [[0 for i in range(w-m+1)] for z in range(h-m+1)]
for i in range(h-m+1):
    for j in range(w-m+1):
        O_[i][j] = O(i,j,m,P,K)
for i in O_:
    print(' '.join(i))