package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/18
 * @description Leetcode 322 : 零钱兑换
 *  1、动态规划
 */
public class Leetcode_322_coinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount==0){
            return 0;
        }
        /**
         ** 1、dp:凑成需要的最小个数
         **   dp[i] = Math.min(dp[i], dp[i -coin[j]] + 1)
         */
        int[] dp = new int[amount+1];
        // dp初始化
        dp[0] = 0;
        for(int i=1;i< amount+1;i++){
            dp[i] = amount+1;
        }
        for(int i=1;i<dp.length;i++){
            for(int coin:coins){
                if(i >= coin){
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] != (amount+1)? dp[amount]:-1;
    }

    public static void main(String[] args) {
        Leetcode_322_coinChange coinChange = new Leetcode_322_coinChange();
        int res = coinChange.coinChange(new int[]{186,419,83,408},6249);
        System.out.println(res);
    }
}
