package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/28
 * @description Leetcode 55: 跳跃游戏
 * 1、回溯法
 * 2、贪心法
 */
public class Leetcode_55_canJump {
    public boolean canJump(int[] nums) {
        //1、动态规划
        // return dp(nums);

        // 2、贪心法：遍历数组，对于nums[i],【其能到达的最远处 >= last下标，则就可以到达】
        int index = 0,far = 0;
        int len = nums.length;
        while(index <= far){
            if(index + nums[index] >= len-1){
                return true;
            }else{
                far = Math.max(index + nums[index],far);
            }
            index ++;
        }
        return false;
    }

    public boolean dp(int[] nums){
        // 1、动态规划：
        // dp[i]: 表示能否到达下标i
        // dp[i] = dp[j] && (nums[j] >= i-j)
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(dp[j] && (nums[j] >= i-j)){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length-1];
    }
}
