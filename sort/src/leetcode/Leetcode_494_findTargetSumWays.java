package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/28
 * @description Leetcode 494: 目标和
 */
public class Leetcode_494_findTargetSumWays {
    int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        // 1、回溯法
        // dfs(0,nums,target);
        // return res;

        /** 2、动态规划：
         *    dp[i]: 从[0, i]这个子区间挑选一些数，使得其和等于j，有多少种方法
         *
         * 		        dp[i−1][j] + dp[i−1][j−nums[i]],   nums[i] <= j
         *    dp[i][j] =
         *               dp[i−1][j],    					nums[i] > j
         */
        int sum = 0;
        for(int num:nums){
            sum +=num;
        }

        if((sum-target)<0 || (sum-target)%2 !=0){
            return 0;
        }

        target = (sum-target)/2;
        int[][] dp = new int[nums.length][target+1];
        // 初始化
        if(nums[0] == 0){ // 注意这里要区分nums[0]是不是0
            dp[0][0] = 2;
        }else{
            dp[0][0] = 1;
            if(nums[0] <= target){
                dp[0][nums[0]] = 1;
            }
        }

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<target+1;j++){
                if(nums[i] > j){
                    dp[i][j] =  dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][target];
    }

    public void dfs(int index, int[] nums, int target){
        //1、结束条件: 数字全用&&target满足
        if(index >= nums.length){
            if(target == 0){
                res ++;
                return;
            }else{
                return;
            }
        }

        //2、剪枝
        //3、选择
        dfs(index+1,nums,target-nums[index]); // 用 +
        dfs(index+1,nums,target+nums[index]); // 用 -
    }
}
