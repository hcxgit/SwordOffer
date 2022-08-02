package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/24
 *
 * Leetcode 300：最长递增子序列
 *
 *  1、 动态规划
 *     dp[i]：表示以num[i]结尾的最长递增子序列LIS的长度
 *
 *     每一轮计算新的dp[i]时，要把num[i]接在后面，所以要找:
 *          1、满足num[j] < num[i]
 *          2、找 max(dp[j]) +1
 *
 *     综上：dp[i] = max(dp[j])+1, 其中 0≤j<i 且 num[j]<num[i]
 *
 *  2、贪心法 + 二分查找
 */
public class Leetcode_300_lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1; // 初始化1
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            //dp中最大的就是结果
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
