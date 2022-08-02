package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/8/2
 *
 * Leetcode 72: 编辑距离
 *
 *  1、动态规划
 *      1.状态定义: dp[i][j]代表word1[0,i-1]转换到word2[0,j-1]所需的【最小操作步数】
 *      2.状态转移: dp[i][j]由word1[i-1]与word2[j-1]决定转移方向
 *           2.1 word1[i-1]==word2[j-1] 不用操作
 *                  即：dp[i][j] = dp[i-1][j-1]
 *           2.2 word1[i-1]!=word2[j-1] 对word1有3种操作方式->插入|删除|修改
 *               2.2.1  删除。dp[i][j] = dp[i-1][j]+1
 *                  例如：word1：abde ——> word2：abd
 *               2.2.2 插入。dp[i][j] = dp[i][j-1]+1
 *                  例如：word1：ab ——> word2：abd
 *               2.2.3 修改。dp[i][j] = dp[i-1][j-1]+1
 *           三者中取最小值转移->min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+1)
 *       3.初始化:dp[i][0]=i,dp[0][j]=j  空串
 *       4.遍历顺序:正序
 *       5.返回形式:返回dp[len1][len2]
 */
public class Leetcode_72_minDistance {
    //1、动态规划
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 防止越界，len+1
        int[][]dp = new int[len1+1][len2+1];
        //删到空串的次数
        for (int i = 0; i < len1+1; i++) {
            dp[i][0] = i;
        }
        //空串开始插入的次数
        for (int j = 0; j < len2+1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }

        return dp[len1][len2];
    }
}
