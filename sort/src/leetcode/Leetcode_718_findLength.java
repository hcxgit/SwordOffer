package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/19
 * @description Leetcode 718: 最长重复子数组
 *  1、dp
 *  2、滑动窗口
 */
public class Leetcode_718_findLength {
    public int findLength(int[] nums1, int[] nums2) {
        // 1、dp
        // return dp(nums1,nums2);
        // 2、滑动窗口
        return sideWindows(nums1,nums2);
    }

    /**
     1、dp
     - dp[i][j]: 以【num1[i]为结尾的子数组】和【num2[j]为结尾的子数组】的最长重复用子数组的【长度】      - 如果：nums1[i] == nums2[j],
     - 则dp[i][j] = dp[i-1][j-1] +1
     - 否则：dp[i][j] = 0
     */
    public int dp(int[] nums1, int[] nums2) {
        int len1 = nums1.length + 1;
        int len2 = nums2.length + 1;
        int[][] dp = new int[len1][len2];
        int res = 0;

        for(int i=1;i<len1;i++){
            for(int j=1;j<len2;j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1] +1;
                    res = Math.max(res,dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }

        return res;
    }

    /**
     2、滑动窗口
     - nums2在nums1下面滑动，公共的部分相当于【窗口】，求【每个窗口内的最长重复子数组的长度】
     */
    public int sideWindows(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int res = 0;
        // 1、nums2向右滑动，直到nums1和nums2【左对齐】
        for(int idx2=l2-1; idx2 >= 0; idx2--){
            int len = Math.min(l1,l2-idx2);
            res = Math.max(res,maxLen(nums1,0,nums2,idx2,len));
        }

        // 2、nums2继续向右滑动，直到nums2左和nums1右【对齐】
        for(int idx1=0; idx1 < l1; idx1++){
            int len = Math.min(l1-idx1,l2);
            res = Math.max(res,maxLen(nums1,idx1,nums2,0,len));
        }
        return res;
    }

    /**
     【每个窗口内,num1[i: i+len], num2[j: j+len]最长公共子数组的长度（连续）】
     */
    public int maxLen(int[] nums1,int idx1, int[] nums2,int idx2, int len){
        int res = 0;
        int count = 0;
        for(int i=0;i<len;i++){
            if(nums1[idx1 + i] == nums2[idx2 + i]){
                count ++;
            }else if(count > 0){
                res = Math.max(res,count);
                count = 0;
            }
        }
        return count > 0?Math.max(res,count):res;
    }
}
