package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2023/6/8
 * @description Leetcode 114：二叉树展开为链表
 *
 *    1、先序遍历 +重新遍历更新指针
 *
 *    2、【栈】
 */
public class Leetcode_114_flatten {
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }

        // 1、【先序遍历，得到结果】，再重新遍历一次更改指针
        // flattenByPreOrder(root);

        // 2、【栈】
        flattenByStack(root);
    }

    /**
     1、【先序遍历，得到结果】，再重新遍历一次更改指针
     */
    public void flattenByPreOrder(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        // 1、先序遍历
        preOrder(root,list);

        TreeNode pre;
        TreeNode curr;
        // 2、更新指针
        for(int i=1;i<list.size();i++){
            pre = list.get(i-1);
            curr = list.get(i);
            pre.right = curr;
            pre.left = null;
        }
    }

    public void preOrder(TreeNode root, List<TreeNode> list) {
        if(root != null){
            list.add(root);
            preOrder(root.left,list);
            preOrder(root.right,list);
        }
    }

    /**
     2、利用栈，因为是先序遍历，则入栈顺序：【先右子树入栈，再左子树入栈】
     */
    public void flattenByStack(TreeNode root) {

        TreeNode tail = new TreeNode();
        TreeNode curr;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while(!stack.isEmpty()){
            // 1、出栈
            curr = stack.removeFirst();
            // 2、右子树、左子树入栈
            if(curr.right != null){
                stack.addFirst(curr.right);
            }
            if(curr.left != null){
                stack.addFirst(curr.left);
            }
            // 3、更新head, 记得清空left、right
            curr.left = null;
            tail.right = curr;
            tail = curr;
        }
    }
}
