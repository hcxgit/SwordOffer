package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/24
 *
 * Leetcode 1143： 最长公共子序列
 *
 * 思路一：动态规划
 *
 *      f[i][j]：表示text1的[1:i]和text2的[1:j]的最长公共子序列长度，【下标从1开始,不用做数组越界判断】。
 *
 *       1、当text1[i] == text2[j]，【最长公共子序列长度+1】
 *              f[i][j] = f[i-1][j-1] + 1
 *       2、当text1[i] != text2[j]，【两字符暂时最多只有一个能在子序列中】，取最大值
 *              f[i][j] = max(f[i - 1][j],f[i][j - 1])
 */
public class Leetcode_1143_longestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        //下标从1开始,不用做数组越界判断
        int [][] dp = new int[len1+1][len2+1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) { // 注意下标
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
