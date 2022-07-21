package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/21
 *  Leetcode 122:买股票的最佳时机2（无交易次数限制）
 *
 *  1、标准动态规划：
 *      dp[i][0] 表示第i天手里【没有股票】获得的最大利润
 *      dp[i][1] 表示第i天手里【有股票】获得的最大利润
 *
 *      dp[i][0]两种情况：
 *          1、第i-1天手里【没有股票】。dp[i-1][0]
 *          2、第i-1天手里【有股票】，第i天卖了。dp[i-1][1]+ nums[i]
 *          所以，dp[i][0] = max(dp[i-1][0], dp[i-1][1]+ nums[i])
 *
 *      dp[i][1]两种情况：
 *          1、第i-1天手里【有股票】。dp[i-1][1]
 *          2、第i-1天手里【没有股票】，第i天买了。dp[i-1][0]-nums[i]
 *          所以，dp[i][1] = max(dp[i-1][1], dp[i-1][0]-nums[i])
 *
 *      综上：dp[i][0] = max(dp[i-1][0], dp[i-1][1] + nums[i])
 *           dp[i][1] = max(dp[i-1][1], dp[i-1][0] - nums[i])
 *
 *  2、动态规划优化
 *      只用到dp[i-1][0]和dp[i-1][1]，没必要用数组，用两个变量表示就行dp0、dp1
 *
 *      dp0 = max(dp0, dp1+ nums[i])
 *      dp1 = max(dp1, dp0-nums[i])
 *
 *  3、贪心法
 *      可以当天买当天卖，交易次数不限，所以，只要比前一天贵就卖，【有利可图就卖】
 *      【累加】就是最大利润
 */
public class MaxProfit2 {
    //标准dp
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[length-1][0];
    }

    //dp优化
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        int dp0 = 0, dp1 = -prices[0];

        for (int i = 1; i < length; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }
        return dp0;
    }

    //3、贪心法
    public int maxProfit3(int[] prices) {
        int length = prices.length;
        int res = 0;

        for (int i = 1; i < length; i++) {
            if(prices[i] > prices[i - 1]){
                res += (prices[i]-prices[i-1]);
            }
        }
        return res;
    }
}
