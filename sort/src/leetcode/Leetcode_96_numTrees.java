package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/5
 * @description Leetcode 96: 不同的二叉搜索树
 */
public class Leetcode_96_numTrees {
    public int numTrees(int n) {
        /**
         * dp
         * dp(n): 【n个节点】的二叉搜索树的个数，f(i) 为【以 i 为根】的二叉搜索树的个数,则：
         *   dp(n) = f(1) + f(2) + f(3) + ... + f(n)
         * 当 i 为根节点时，其【左子树】节点个数为 i-1 个，【右子树】节点为 n-i，则:
         *   f(i) = dp(i-1) * dp(n-i)
         * 综上：
         dp(n) = sum{dp(i-1) * dp(n-i)}  i ∈ [1,n]
         */
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<n+1;i++){
            for(int j=1;j<=i;j++){
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}
