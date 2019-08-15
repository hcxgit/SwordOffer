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
import sys
import copy
alist = list(map(int,sys.stdin.readline().strip().split()))
n,m = alist[0],alist[1]
alist2 = list(map(int,sys.stdin.readline().strip().split()))
alist3 = list(map(int,sys.stdin.readline().strip().split()))

def permutations(arr, start, end, res):
    if start == end:
        arr1 = copy.copy(arr)
        res.append(arr1)
        pass
    else:
        for index in range(start, end):
            arr[index], arr[start] = arr[start], arr[index]
            # print(index,start)
            permutations(arr, start + 1, end,res)
            arr[index], arr[start] = arr[start], arr[index]
l2 = []
permutations(alist2,0,len(alist2),l2)
l3 = []
permutations(alist3,0,len(alist3),l3)
r = ' '
for i in l2:
    for j in l3:
        temp = list(map(str,[(i[k]+j[k])%m for k in range(n)]))
        temp = ''.join(temp)
        if temp > r:
            r = temp
for i in r:
    print(i,end=' ')

