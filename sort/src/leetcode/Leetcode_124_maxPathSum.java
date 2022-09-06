package leetcode;

import datastruct.TreeNode;

/**
 * @author 三笠阿克曼
 * @date 2022/9/6
 *
 * Leetcode 124: 二叉树中的最大路径和
 *
 *  1、递归
 *      - 【最大路径】一定是【以某个节点为root节点，附带其左右子树】
 *      - 对于一个二叉树，`a`是根节点（递归中的root），`bc`是其左右子树，最大的路径分【3种情况】：
 *          - 1、` b + a +c`。也就是`bac`就是【最大路径】，`a`是root节点。
 *          - 2、`b + a + a的父节点`。也就是【a的左子树】在最大路径中。
 *          - 3、`c + a + a的父节点`。也就是【a的右子树】在最大路径中。
 *
 *     -过程
 *      - 1、对于情况2、3，可以通过【递归】，每次递归计算【左右子树的单边分支最大值】(因为【左右子树】只能用到一个，所以是【单边】)
 *          - `leftMax = Math.max(0, dfs(root.left))`
 *           - `rightMax = Math.max(0, dfs(root.right))`
 *
 *      - 2、对于【情况1】，结果【可能是最大路径和】，`left->root->right` 作为路径与已经计算过历史最大值做【比较】。
 *          - `max = Math.max(max, root.val + leftMax + rightMax)`
 *
 *      - 3、返回【经过root的单边最大分支*给当前root的父节点计算使用
 *          - `return root.val + Math.max(leftMax, rightMax)`
 *
 * - 注意：节点可能有【负数】，max(0,x)，【初始MAX】= `Integer.MIN_VALUE`
 */
public class Leetcode_124_maxPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    /**
     * 返回单边最大值，即：max(root,root + left, roo + right)
     */
    public int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftMax = Math.max(0,dfs(root.left));
        int rightMax = Math.max(0,dfs(root.right));

        // left->root->right 可能是最大路劲和
        max = Math.max(leftMax + root.data + rightMax,max);

        // 只用左子树或者右子树
        return root.data + Math.max(leftMax,rightMax);
    }
}
