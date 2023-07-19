package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/7/11
 * @description Leetcode 152：乘积最大的子数组
 *
 * 1、dp
 */
public class Leetcode_152_maxProduct {
    public int maxProduct(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }

        /**
         * dp[i]: nums[i] 为结尾的最大乘积
         * 考虑到负数,定义另一个dp2[i]: nums[i] 为结尾的最小乘积
         *      dp[i] = max(dp[i-1] * nums[i], dp2[i-1] * nums[i], nums[i])
         *      dp2[i] = min(dp[i-1] * nums[i], dp2[i-1] * nums[i], nums[i])
         */
        int[] dp = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp[0] = nums[0];
        dp2[0] = nums[0];
        int res = dp[0];
        for(int i=1;i<nums.length;i++){
            dp2[i] = Math.min(Math.min(dp[i-1]*nums[i],dp2[i-1]*nums[i]), nums[i]);
            dp[i] = Math.max(Math.max(dp[i-1]*nums[i],dp2[i-1]*nums[i]), nums[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }


}
