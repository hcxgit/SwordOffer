package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/21
 *  Leetcode 123:买股票的最佳时机3（最多交易两次）
 *
 *  1、标准动态规划：
 *      dp[i][1][k] 表示第【i】天手里【有股票】【最多交易过j次】获得的最大利润
 *      dp[i][0][k] 表示第【i】天手里【没有股票】【最多交易过j次】获得的最大利润
 *      【卖】的时候算作一次【完整交易】
 *      1、dp[i][0][k] 有两种情况
 *          1、第【i-1】天【没有股票】  dp[i-1][0][k]
 *          2、第【i-1】天【有股票】，【i天卖了】  dp[i-1][1][k-1]+nums[i]
 *
 *      2、dp[i][1][k] 有两种情况
 *          1、第【i-1】天【没有股票】，第【i天买了】  dp[i-1][0][k]-nums[i]
 *          2、第【i-1】天【有股票】  dp[i-1][1][k]
 *
 *     综上，dp[i][0][k] = max(dp[i-1][0][k], dp[i-1][1][k-1]+nums[i])
 *          dp[i][1][k] = max(dp[i-1][1][k], dp[i-1][0][k]-nums[i])
 *
 *  2、动态规划优化
 *      由于最多可以完成【两笔】交易，因此在任意一天结束之后，我们会处于以下【五个状态】中的一种：
 *          1、无任何操作； 0
 *          2、【一次买】； buy1
 *          3、【一次买】+【一次卖】，即完成了一笔交易； sell1
 *          4、【一次完整买卖】+【第二次买】； buy2
 *          5、【两次完整交易】。 sell2
 *      前一天的状态buy11、sell11、buy22、sell22
 *
 *      综上，buy1 = max{buy11,-nums[i]}  前一天不动 / 第一次买
 *           sell1 = max{sell11，buy11 + nums[i]}  前一天不动 /第一次卖
 *           buy2 = max{buy22，sell11 - nums[i]} 前一天不动 / 第二次买
 *           sell2 = max{sell22, buy22 + numx[i]} 前一天不动 / 第二次卖
 */
public class Leetcode_123_maxProfit3 {
    //1、标准dp
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][][] dp = new int[length][2][3];

        //初值
        for (int i = 0; i < 3; i++) {
            dp[0][0][i] = 0; //第一天没持有股票
            dp[0][1][i] = -prices[0]; // 第一天持有股票
        }

        for (int i = 1; i < length; i++) {
            for (int k = 0; k < 3; k++) {
                if(k !=0){ //注意边界值
                    dp[i][0][k] = Math.max(dp[i-1][0][k], dp[i-1][1][k-1] + prices[i]);
                }else{
                    dp[i][0][k] = dp[i-1][0][k];
                }
                dp[i][1][k] = Math.max(dp[i-1][1][k], dp[i-1][0][k] - prices[i]);
            }
        }
        return dp[length-1][0][2];
    }

    //2、dp优化
    public int maxProfit2(int[] prices) {
        int length = prices.length;
        int buy1, buy11 = -prices[0];
        int sell1,sell11 = 0;
        int buy2, buy22 = -prices[0];
        int sell2 = 0,sell22 = 0;

        for (int i = 1; i < length; i++) {
            buy1 = Math.max(buy11,-prices[i]);
            sell1 = Math.max(sell11,buy11 + prices[i]);
            buy2 = Math.max(buy22,sell11 - prices[i]);
            sell2 = Math.max(sell22,buy22 + prices[i]);

            //更新前一天状态，下一次迭代用
            buy11 = buy1;
            buy22 = buy2;
            sell11 = sell1;
            sell22 = sell2;
        }
        return sell2;
    }
}
