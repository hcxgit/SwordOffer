package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2023/7/11
 * @description Leetcode 105: 从先序遍历和中序遍历中构建二叉树
 *
 * 1、递归
 *
 */
public class Leetcode_105_buildTree {
    Map<Integer,Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }

        return buildTree(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    /**
     1、递归
     */
    public TreeNode buildTree(int[] preorder,int left_preorder,int right_preorder, int[] inorder, int left_inorder,int right_inorder) {
        if(left_preorder >= preorder.length || left_inorder >= inorder.length || left_preorder > right_preorder || left_inorder > right_inorder){
            return null;
        }

        //1、找到root
        TreeNode root = new TreeNode(preorder[left_preorder]);

        //2、找到inorder中的root(提前用hash表存下标)
        int idx_root = map.get(root.val);

        //3、划分左右子树，递归创建左右子树
        int num_left = idx_root-left_inorder;
        int num_right = right_inorder - idx_root;

        //中序遍历的root前都是左子树，root后都是右子树
        TreeNode left_root = buildTree(preorder,left_preorder+1,left_preorder+num_left,inorder,left_inorder,idx_root-1);
        TreeNode right_root = buildTree(preorder,left_preorder+num_left+1,right_preorder,inorder,idx_root+1,right_inorder);

        //4、合并左右子树
        root.left = left_root;
        root.right = right_root;
        return root;
    }
}
