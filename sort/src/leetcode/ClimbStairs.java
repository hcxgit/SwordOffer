package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/5/8
 * Leetcode 70:爬楼梯
 * 思路：动态规划。f(x)=f(x−1)+f(x−2)
 * 优化：自底向上。f(x-1)只和 f(x-1)与 f(x-2)有关，用三个变量表示。
 */
public class ClimbStairs {
    //标准动态规划
    public int climbStairs(int n){
        //1、定义数组
        int[] dp = new int[n+1];
        //2、数组初始化
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    //动态规划数组优化
    public static  int climbStairs2(int n){
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
