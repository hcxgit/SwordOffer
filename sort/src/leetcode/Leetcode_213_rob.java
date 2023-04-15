package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/5
 * LeetCode 213: 打家劫舍II
 *
 * 问题转化为，两个dp:
 *          dp1: 用nums[0],不用nums[-1],
 *          dp2: 不用nums[0],用nums[-1]
 */
public class Leetcode_213_rob {
    public int rob(int[] nums) {
        if(nums == null || nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int[] dp= new int[nums.length+1];  // f(x)用了nums[0]
        int[] dp2= new int[nums.length+1]; // f_2(x)没用nums[0]
        // 初始化
        dp[0] = 0;
        dp2[0] = 0;
        dp[1] = nums[0];
        dp2[1] = 0;
        for(int i=2;i<=nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i-1]);
            dp2[i] = Math.max(dp2[i-1],dp2[i-2]+nums[i-1]);
        }

        // f(x)使用了nums[0], 所以不能用最后一个，所以，最大值为max{f(x-1),f_2(x)}
        return Math.max(dp[nums.length-1],dp2[nums.length]);
    }
}
