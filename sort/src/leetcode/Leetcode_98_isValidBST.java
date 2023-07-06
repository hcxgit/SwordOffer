package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2023/7/6
 * @description Leetcode 98: 验证二叉搜索树
 *
 *  1、先中序遍历(递归/非递归)，然后再比较
 *
 *  2、递归比较(low,right)，左子树的值要在范围 (low,root)，右子树的值要在(root，high)
 */
public class Leetcode_98_isValidBST {
    List<Integer> list = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        // return isValidBST_1(root);
        return isValidBST_2(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    /**
     1、中序遍历(递归)，然后再比较
     */
    public boolean isValidBST_1(TreeNode root) {
        midOrder_2(root);
        for(int i=1;i<list.size();i++){
            if(list.get(i) <= list.get(i-1)){
                return false;
            }
        }

        return true;
    }

    public void midOrder(TreeNode root) {
        if(root == null){
            return;
        }

        midOrder(root.left);
        list.add(root.val);
        midOrder(root.right);
    }

    //中序遍历：非递归
    public void midOrder_2(TreeNode root) {
        if(root == null){
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            // 左子树入栈
            while(root != null){
                stack.push(root);
                root = root.left;
            }

            if(!stack.isEmpty()){
                // root出栈
                root = stack.pop();
                list.add(root.val);

                //更新root为右子树
                root = root.right;
            }
        }
    }

    /**
     2、递归比较(low,right)，左子树的值要在范围 (low,root)，右子树的值要在(root，high)
     */
    public boolean isValidBST_2(TreeNode root,long low,long high) {
        if(root == null){
            return true;
        }

        if(root.val <= low || root.val >= high){
            return false;
        }

        return isValidBST_2(root.left,low,root.val) && isValidBST_2(root.right,root.val,high);
    }
}
