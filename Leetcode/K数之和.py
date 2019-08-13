"""
K数之和
给定 n 个不同的正整数，整数 k（k <= n）以及一个目标数字 target。　
在这 n 个数里面找出 k 个数，使得这 k 个数的和等于目标数字，求问有多少种方案？
eg: 给出 [1,2,3,4]，k=2， target=5，[1,4] 和 [2,3] 是 2 个符合要求的方案，返回 2。
"""
def kSum(A, k, target):

    dp = [[0 for i in range(target + 1)] for _ in range(k + 1)]
    dp[0][0] = 1

    for a in A:
        for ki in range(k, 0, -1):
            for t in range(target,a-1,-1):
                dp[ki][t] += dp[ki - 1][t - a]
    return dp[k][target]

if __name__ == "__main__":
    A = [1,2,3,4,5]
    k = 1
    target = 4
    s = kSum(A, k, target)
    print(s)