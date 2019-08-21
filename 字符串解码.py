# coding=utf-8

"""
'1'->A     ...  ‘26’——>Z
求有几种方式
dp[i] = dp[i-1]+dp[i-2](if ...)
0只能和前面的组数，所以从后往前遍历
dp[i] :从i开始的字符串的解码方式个数
dp[i] = dp[i+1] +dp[i+2] if ...else +1
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
            else: # 没有dp[i+2]
                dp[i] += 1
print(dp[0])
