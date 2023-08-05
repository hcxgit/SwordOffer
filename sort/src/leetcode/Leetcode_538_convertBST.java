package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/8/5
 * @description Leetcode 538: 把二叉搜索树转换为累加树
 *
 *      1、反序中序遍历
 *        - 按照right、root、left的顺序遍历，【记录累加和】更新值就行
 *
 */
public class Leetcode_538_convertBST {

    public TreeNode convertBST(TreeNode root) {
        midOrder(root,0);
        return root;
    }

    // 1、返回【当前子树的最大累加值】，并更新root
    public int midOrder(TreeNode root,int currSum) {
        if(root == null){
            return currSum;
        }

        // 递归右子树
        int right = midOrder(root.right,currSum);
        // 更新root
        root.val = right + root.val;
        // 递归左子树
        return midOrder(root.left,root.val);
    }
}
