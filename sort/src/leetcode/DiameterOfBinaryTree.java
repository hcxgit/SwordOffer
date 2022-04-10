package leetcode;

import datastruct.TreeNode;

/**
 * @author 三笠阿克曼
 * @date 2022/4/10
 * Leetcode 543:二叉树的直径：任意两个结点路径长度中的最大值
 * 思路：递归遍历：最长路径是 某个节点:左子树+右子树拼接
 *      路径经过的节点数num=某个节点：左子树高度+右子树高度+1
 *      最长路径=num-1
 *      递归求左右子树的高度时，更新路径长度
 */
public class DiameterOfBinaryTree {
    int num;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        num = 1;
        //递归求左右子树的高度时，更新路径节点个数
        hight(root);
        return num-1;
    }

    /**
     * 树的高度,并更新经过当前root的最长节点个数
     */
    public int hight(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = hight(root.left);
        int right = hight(root.right);
        num = Math.max(num,left+right+1);
        return Math.max(left,right)+1;
    }
}
