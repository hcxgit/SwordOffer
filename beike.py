# def solve(eq,var='X'):
#     try:
#         eq =eq.split('=')
#         eq1 = eq[0]+'-('+eq[1]+')'
#         # eq1 = eq.replace("=","-(")+")"
#         c = eval(eq1,{var:1j})
#         if -c.real%c.imag ==0:
#             return -c.real//c.imag
#         else:
#             return -1
#     except:
#         return -1
# import sys
# s = sys.stdin.readline().strip()
# print(int(solve(s)))

# import sys
# res = []
# while True:
#     alist = list(map(int,sys.stdin.readline().strip().split()))
#     if not alist:
#         break
#     alist = sorted(alist)
#     if alist[0]<1 or alist[-1]>35:
#         res.append(0)
#     else:
#         flag = 1
#         for i in range(1,len(alist)):
#             temp = alist[i]-alist[i-1]
#             if temp != 1:
#                 if 10 not in [alist[i]-j for j in alist[:i]]:
#                     flag = 0
#                     break
#         res.append(flag)
# for i in res:
#     print(i)


import sys
res = []
N = int(sys.stdin.readline().strip())
s1 = sys.stdin.readline().strip().split()
s2 = sys.stdin.readline().strip().split()
dp = [[0 for i in range(N+1)] for j in range(N+1)]
for i in range(1,N+1):
    for j in range(1,N+1):
        if s1[i-1] == s2[j-1]:
            dp[i][j] = dp[i-1][j-1]+1
        else:
            dp[i][j] = max(dp[i-1][j],dp[i][j-1])
print(N-dp[-1][-1])
     

    