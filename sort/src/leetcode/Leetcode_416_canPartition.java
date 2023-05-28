package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/28
 * @description Leetcode 416: 分割等和子集
 *  1、动态规划
 * dp[i][j]：表示能否从[0, i]这个子区间内挑选一些正整数，使得其和等于j。
 *
 * 			     true,         						 nums[i] == j
 *   dp[i][j] =  dp[i−1][j] || dp[i−1][j−nums[i]],   nums[i] < j
 *	             dp[i−1][j],   					     nums[i] > j
*/
public class Leetcode_416_canPartition {
    public boolean canPartition(int[] nums) {

        int total = 0;
        for(int num:nums){
            total += num;
        }

        if(total%2 != 0){
            return false;
        }

        int target = total/2;
        boolean[][] dp = new boolean[nums.length][target+1];
        // 初始化
        for(int i=0;i<nums.length;i++){
            dp[i][0] = true;  // target 为0，为true
        }

        for(int i=1;i<nums.length;i++){
            for(int j=1;j<target+1;j++){
                if(nums[i] == j){
                    dp[i][j] = true;
                }else if(nums[i] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][target];
    }
}
