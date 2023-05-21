package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/21
 * @description Leetcode 221: 最大正方形
 *  1、 动态规划
 */
public class Leetcode_221_maximalSquare {
    public int maximalSquare(char[][] matrix) {

        // dp[i][j]: 以(i,j)为右下角的正方形最大边长
        int row = matrix.length + 1;
        int line = matrix[0].length + 1;
        int[][] dp = new int[row][line];
        int res = 0;

        //初始化
        for(int j=0;j<line;j++){
            dp[0][j] = 0;
        }

        for(int i=0;i<row;i++){
            dp[i][0] = 0;
        }

        for(int i=1; i<row; i++){
            for(int j=1; j<line; j++){
                if(matrix[i-1][j-1] == '0'){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                }
                res = Math.max(res,dp[i][j]);
            }
        }
        return res * res;
    }
}
