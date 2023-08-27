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
 *
 *      - 思想 : 让递增子序列【增得慢】，因此希望【每次】递增子序列【最后加的数尽可能小】
 *      - d[i] : 【长度为i】的最长递增子序列的【末尾元素】
 *      - len : 【目前】最长上升子序列的【长度】
 *          - 对于每个num:
 *              - 如果num > d[len],则更新d[++len] = num
 *              - 否则，【二分查找】，找到【第一个比num大的数，并替换】：d[j] = num
 */
public class Leetcode_300_lengthOfLIS {
    public int lengthOfLIS_1(int[] nums) {
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

    /**
     2、贪心法 + 二分查找
     */
    public int lengthOfLIS(int[] nums) {
        int d[] = new int[nums.length +1];
        d[0] = Integer.MIN_VALUE;
        int len = 0;

        for(int num:nums){
            if(num > d[len]){
                // 直接添加
                d[++len] = num;
            }else{
                // 二分查找，替换
                int l=1,r=len;
                while(l < r){
                    int mid = l + (r-l)/2;
                    if(d[mid] < num){
                        l = mid+1;
                    }else{
                        r = mid;
                    }
                }
                d[l] = num;
            }
        }
        return len;
    }
}
