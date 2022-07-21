package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/21
 *  Leetcode 121:买股票的最佳时机1（只能买卖一次）
 *
 *  动态规划：
 *    f(i+1) 表示第i+1天买入时的最大利润,
 *    f(i+1)和f(i)的差距就是那两天价格的差距，所以，f(i) = max(0,nums[i+1]-nums[i]+f(i+1))   最小为0
 *   由于只用到后一天的利润，可以优化只用一个变量表示。
 *
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {

        //后一天买入的最大利润
        int nextMaxProfit = 0;
        int res = 0;

        for(int i=prices.length-2; i>=0; i--){
            //后一天和当天价格的差值  就是利润的差值，利润最小为0
            int gap = prices[i+1]-prices[i];
            nextMaxProfit = Math.max(0,nextMaxProfit + gap);
            res = Math.max(nextMaxProfit,res);
        }
        return res;
    }
}
