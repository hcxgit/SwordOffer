import numpy as np

# ---------------趋势科技第3题--------------
"""
            梯度下降法求解线性回归
"""
def gradient_descent(X, y, lr=0.01, threshold=1e-3):
    params = np.array([0., 0., 0.])
    th = 1
    while True:
        params2 = params.copy()
        for x_,y_ in zip(X,y):
            y2 = params[0] * x_[0] + params[1] * x_[1] + params[2]
            loss = y2-y_
            params[0] -= lr * loss*x_[0]
            params[1] -= lr * loss*x_[1]
            params[2] -= lr * loss
        th = params-params2
        if abs(th[0] < threshold) and abs(th[1] < threshold) and abs(th[2] < threshold):
            break
    return params

#
# Please don't modify any code below.
#
# if __name__ == "__main__":
    # # Code to generate the data/test case
    # n_samples = 10000
    # x_1, x_2 = np.random.random(n_samples), np.random.random(n_samples)
    # theta_1, theta_2, b = map(float, input().split())
    # # print (theta_1, theta_2, b)
    # X, y = np.vstack([x_1, x_2]).T, theta_1 * x_1 + theta_2 * x_2 + b
    # # Solution
    # params = gradient_descent(X, y)
    # result = []
    # for param in params:
    #     result.append('{:.02f}'.format(param))
    # print(' '.join(result))

alist2 = list(map(int,sys.stdin.readline().strip().split()))
import sys
s = sys.stdin.readline().strip()
stack = []
for i in s:
    if i != ']':
        stack.append(i)
    else:
        temp = ''
        while True:
            p = stack.pop()
            if p != '[':
                temp = p + temp
            else:
                temp = temp.split('|')
                temp = temp[1] * int(temp[0])
                for j in temp:
                    stack.append(j)
                break
print(''.join(stack))
        

# import sys
# [N,L] = list(map(int,sys.stdin.readline().strip().split()))
# dp = [[0 for i in range(L+1)] for j in range(L+1)]
# for i in range(N):
#     [x1, x2] = list(map(int,sys.stdin.readline().strip().split()))
#     for i in range(x1,min(L+1,x2+1)):
#         dp[x1][i] = 1

# for j in range(1,L+1):
#     for k in range(1,j):
#         if dp[0][k] != 0 and dp[k][j] != 0:
#             if dp[0][j] != 0:
#                 dp[0][j] = min(dp[0][k]+dp[k][j],dp[0][j])
#             else:
#                 dp[0][j] = dp[0][k]+dp[k][j]

# if dp[0][L] == 0:
#     print(-1)
# else:
#     print(dp[0][L])
            
import sys
N = int(sys.stdin.readline().strip())
work = list(map(int,sys.stdin.readline().strip().split()))
sport = list(map(int,sys.stdin.readline().strip().split()))
dp  = [0 for i in range(N)] # 第i天已经休息的天数
for i in range(N):
