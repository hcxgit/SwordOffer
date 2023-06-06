package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/6
 * @description Leetcode 516 : 最长回文子序列
 *  dp
 */
public class Leetcode_516_longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        /**
         dp[i][j]: [i,j] 范围内的最长的回文子序列长度

         如果 s[i] == s[j]:
         dp[i][j] = dp[i+1][j-1] +2
         如果 s[i] != s[j]:
         dp[i][j] = max(dp[i+1][j], dp[i][j-1])
         */

        int len = s.length();
        int[][] dp = new int[len][len];
        dp[len-1][len-1] = 1;
        for(int i=len-2;i>=0;i--){
            for(int j=i+1;j<len;j++){
                // 初始化 dp[i][i] = 1
                dp[i][i] = 1;
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] +2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
