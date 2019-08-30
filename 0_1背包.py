"""
price: 商品的价值[1,3,6,7]
weight: 商品的重量 [3,23,6,8]
bag: 背包容量
"""
def bag_01(price,weight,bag):
    length = len(price)
    # dp[i][j]: 前i个商品在背包容量为j的最大价值
    dp = [[0 for i in range(bag+1)] for j in range(length)]
    if weight[0] <=bag:
        dp[0][weight[0]] = price[0]
    for i in range(1,length):
        for j in range(1,bag+1):
            if j < weight[i]: # 放不下
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = max(dp[i-1][j-weight[i]]+price[i],dp[i-1][j])
    return dp[-1][-1]
if __name__ == "__main__":
    print(bag_01([20,18,25,30],[30,20,35,40],50))