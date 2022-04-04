package leetcode;

import datastruct.TreeNode;
import datastruct.TreeTraveral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 三笠阿克曼
 * @date 2022/4/3
 */
public class CreateTree {
    public static TreeNode listToTree(ArrayList<Integer> list){
        TreeNode root = new TreeNode(list.get(0));
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        for (int i = 1; i <list.size();) {
            TreeNode currNode = queue.remove();

            //设置左子树
            if(list.get(i) != null){
                TreeNode leftNode = new TreeNode(list.get(i));
                currNode.left = leftNode;
                queue.add(leftNode);
            }else{
                currNode.left = null;
                queue.add(null);
            }
            //设置右子树
            if(i+1 <list.size() && list.get(i+1) != null){
                TreeNode rightNode = new TreeNode(list.get(i+1));
                currNode.right = rightNode;
                queue.add(rightNode);
            }else{
                currNode.right = null;
                queue.add(null);
            }
            i = i+2;
        }
        return root;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList(Arrays.asList(3,5,1,6,2,0,8,null,null,7,4));
        TreeNode root = CreateTree.listToTree(list);
        System.out.println(list);
        ArrayList arrayList = new ArrayList();
        TreeTraveral.levelOrder(root,arrayList);
        System.out.println(arrayList);

    }
}
