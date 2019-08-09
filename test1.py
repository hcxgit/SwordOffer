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
    # Code to generate the data/test case
    n_samples = 10000
    x_1, x_2 = np.random.random(n_samples), np.random.random(n_samples)
    theta_1, theta_2, b = map(float, input().split())
    # print (theta_1, theta_2, b)
    X, y = np.vstack([x_1, x_2]).T, theta_1 * x_1 + theta_2 * x_2 + b
    # Solution
    params = gradient_descent(X, y)
    result = []
    for param in params:
        result.append('{:.02f}'.format(param))
    print(' '.join(result))