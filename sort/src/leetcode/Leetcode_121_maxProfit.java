package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/21
 *  Leetcode 121:买股票的最佳时机1（只能买卖一次）
 *
 *      1、标准dp
 *          dp[i] 表示第i天卖出的最大利润
 *          第【i天】和【i-1天】的【股票的价格差】其实就是【利润差】
 *          dp[i] = max(0,dp[i-1]+nums[i]-nums[i-1])
 *
 *      2、优化版dp
 *          只用到前一天的利润，优化为一个变量dp0表示。
 *
 *
 *      3、一次遍历
 *          - 第i天卖出能获得的最大利润：当第(0,i-1)天的【最低点买入】。
 *          - 所以，遍历时，记录preMin值，则能知道【第i天卖出的最大利润】
 *          - 同时更新 res = max(res,prices[i] - preMin), preMin = min(preMin,nums[i])
 */
public class Leetcode_121_maxProfit {

    //1、标准dp
    public int maxProfit_1(int[] prices) {
        int length = prices.length,res = 0;
        int [] dp = new int[length];
        dp[0] = 0;

        for(int i=1; i<length; i++){
            dp[i] = Math.max(0,dp[i-1]+prices[i]-prices[i-1]);
            res = Math.max(dp[i],res);
        }
        return res;
    }

    //2、优化dp
    public int maxProfit_2(int[] prices) {

        int length = prices.length,res = 0;
        int dp0 = 0;

        for(int i=1; i<length; i++){
            dp0 = Math.max(0,dp0 + prices[i]-prices[i-1]);
            res = Math.max(dp0, res);
        }
        return res;
    }

    /**
     3、一次遍历
     */
    public int maxProfit(int[] prices) {
        int preMin = prices[0];
        int res = 0;
        for(int i=1; i<prices.length; i++){
            res = Math.max(res,prices[i] - preMin);
            preMin = Math.min(preMin,prices[i]);
        }

        return res;
    }
}
