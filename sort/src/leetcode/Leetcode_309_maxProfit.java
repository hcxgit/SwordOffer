package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/29
 * @description Leetcode 309: 最佳买卖股票时期含冷冻期
 *  1、动态规划
 */
public class Leetcode_309_maxProfit {
    public int maxProfit(int[] prices) {
        // 1、动态规划
        // dp[i][j]: 第i天结束后的最大利润，
        //【注意状态定义】：j表示第i天结束后的状态：0:有股票； 1:没股票，当天卖了; 2:没股票，且当天没卖
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for(int i=1;i<prices.length;i++){
            // 1、有股票： i-1天就有 || i天刚买
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][2] - prices[i]);
            // 2、没股票, 当天卖了
            dp[i][1] = dp[i-1][0] + prices[i];
            // 3、没股票，且当天没卖： i-1天也没有(没卖 || 卖了)
            dp[i][2] = Math.max(dp[i-1][1],dp[i-1][2]);

        }
        return Math.max(dp[prices.length-1][1], dp[prices.length-1][2]);
    }
}
