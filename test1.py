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
if __name__ == "__main__":
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

    #
    # import sys
    # alist = list(map(int,sys.stdin.readline().strip().split()))
    # N, K = alist[0],alist[1]
    # s = sys.stdin.readline().strip()
    # s2 = s[0]
    # for i in range(1,len(s)):
    #     if i < K-1:
    #         temp = s[i]
    #         for j in s2[:i]:
    #             if temp==j:
    #                 temp = '0'
    #             else:
    #                 temp = '1'
    #         s2 = s2+temp
    #     else:
    #         temp = s[i]
    #         for j in s2[-(K-1):]:
    #             if temp==j:
    #                 temp = '0'
    #             else:
    #                 temp = '1'
    #         s2 = s2 + temp
    # print(s2[:N])


    # 城市地图（字典的字典）
    # 字典的第1个键为起点城市，第2个键为目标城市其键值为两个城市间的直接距离
    # 将不相连点设为INF,方便更新两点之间的最小值
    import sys
    N = int(sys.stdin.readline().strip())
    G = [[0xfffffff for i in range(N)] for j in range(N)]
    for i in range(N):
        G[i][i] = 0
    for i in range(N-1):
        alist = list(map(int,sys.stdin.readline().strip().split()))
        G[alist[0]-1][alist[0]-1]  = 1
        G[alist[1]-1][alist[1]-1]  = 1


    # 算法思想：
    # 每个顶点都有可能使得两个顶点之间的距离变短
    # 当两点之间不允许有第三个点时，这些城市之间的最短路径就是初始路径

    # Floyd-Warshall算法核心语句
    # 分别在只允许经过某个点k的情况下，更新点和点之间的最短路径
    for k in range(N):  # 不断试图往两点i,j之间添加新的点k，更新最短距离
        for i in range(N):
            for j in range(N):
                if G[i][j] > G[i][k] + G[k][j]:
                    G[i][j] = G[i][k] + G[k][j]

    for i in range(N):
        print(G[i])