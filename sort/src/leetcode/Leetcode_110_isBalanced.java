package leetcode;

import datastruct.TreeNode;

/**
 * @author 三笠阿克曼
 * @date 2022/8/6
 *
 * Leetcode 110: 是否是平衡二叉树
 *
 *  1、自顶向下的递归（类似先序遍历）
 *      1、先计算【当前节点】isBalanced
 *          也就是，计算【左右子树的高度】（递归，左右子树的【最大值】+1）
 *      2、【当前节点】左右子树【高度差绝对值】 > 1, 返回false
 *      3、否则，【递归计算左右子树】 isBalanced
 *
 *  2、自底向上（类似后序遍历）
 *      1、先【递归】计算【左右子树】isBalanced
 *          1、【平衡】，得到【左右子树高度】，计算【当前节点】isBalanced
 *          2、【不平衡】，返回 【-1】
*
 */
public class Leetcode_110_isBalanced {
    // 1、自顶向下的递归
    public boolean isBalanced1(TreeNode root) {
        if(root == null){
            return true;
        }

        // 递归计算
        return (Math.abs(height(root.left)-height(root.right)) <= 1) && isBalanced1(root.left) && isBalanced1(root.right);

    }

    // 计算树的高度 = 左右子树的【最大值】+1
    public int height(TreeNode root) {
        if(root == null){
            return 0;
        }

        return Math.max(height(root.left), height(root.right))+1;
    }

    // 2、自底向上
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }

        return height2(root) != -1;
    }

    // 【平衡】则返回【树的高度】，【不平衡】返回【-1】
    public int height2(TreeNode root) {

        if(root == null){
            return 0;
        }

        //【左子树】是否平衡
        int left = height2(root.left);
        if(left == -1){
            return -1;
        }

        //【右子树】是否平衡
        int right = height2(root.right);
        if(right == -1){
            return -1;
        }

        //【当前节点】是否平衡
        return (Math.abs(right-left) >1)? -1: Math.max(left,right)+1;
    }
}
