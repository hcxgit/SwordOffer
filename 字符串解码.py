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
