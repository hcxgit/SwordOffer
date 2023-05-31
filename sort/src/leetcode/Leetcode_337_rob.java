package leetcode;


/**
 * @author 三笠阿克曼
 * @date 2023/5/31
 * @description Leetcode 337: 打家劫舍 Ⅲ
 */
public class Leetcode_337_rob {
    public int rob(TreeNode root) {
        /**动态规划 + dfs
         * dp[i][j]: 【以i为root节点，能盗取的最高金额】，j表示【i节点是否盗取】：0没盗、1盗了
         * dp[i][0] = max(dp[i.left]) + max(dp[i.right])
         * dp[i][1] = dp[i.left][0] + dp[i.right][0] + i.val
         */
        int[] dp = dfs(root); // root节点【没盗|盗】得到的最高金额
        return Math.max(dp[0],dp[1]);

    }
    public int[] dfs(TreeNode root){
        // 到底了
        if(root == null){
            return new int[]{0,0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];

        // 1、root没用，则left和right都【可用 || 可不用】
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0],right[1]);

        // 2、用了root, left和right都不能用了
        res[1] = left[0] + right[0] + root.val;

        return res;
    }
}
