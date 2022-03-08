package leetcode;

import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2022/3/7
 * Leetcode 203：二叉树的锯齿形层次遍历
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null ){
            return res;
        }
//        doubleStack(root, res);
        dqueue(root,res);
        return res;
    }

    /**
     * 双端队列解法
     */
    public void dqueue(TreeNode root,List<List<Integer>> res){
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);
        //是否从左往右遍历的标志位
        boolean isOrderLeft = true;
        TreeNode curr;
        while (!deque.isEmpty()){
            List<Integer> levelList = new ArrayList<>();
            //遍历当前层元素的个数
            int size = deque.size();
            for (int i = 0;i< size;i++){
                //需要从左->右遍历的节点,节点从右->左队尾进，队尾出
                //需要从右->左遍历的节点,节点从左->右队首进，队首出
                if (isOrderLeft){
                    //队尾出
                    curr = deque.pollLast();
                    //子节点右->左遍历，则左->右入队首
                    if(curr.left != null){
                        deque.addFirst(curr.left);
                    }
                    if(curr.right != null){
                        deque.addFirst(curr.right);
                    }
                }else{
                    //队首出
                    curr = deque.pollFirst();
                    //子节点左->右遍历，则右->左入队尾
                    if(curr.right != null){
                        deque.addLast(curr.right);
                    }
                    if(curr.left != null){
                        deque.addLast(curr.left);
                    }
                }
                levelList.add(curr.val);
            }
            res.add(levelList);
            isOrderLeft = !isOrderLeft;
        }
    }
    /**
     * 双栈解法
     */
    public void doubleStack(TreeNode root,List<List<Integer>> res){
        //stackLeft从左往右遍历，stackRight从右往左遍历
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();

        List<Integer> levelList;
        TreeNode currNode;
        stackLeft.push(root);
        while (stackLeft.size()!= 0 || stackRight.size()!= 0){
            levelList = new ArrayList<>();
            while (stackLeft.size()!= 0){
                //出栈
                currNode = stackLeft.pop();
                levelList.add(currNode.val);
                //入栈right,先左后右
                if(currNode.left != null){
                    stackRight.push(currNode.left);
                }
                if (currNode.right != null){
                    stackRight.push(currNode.right);
                }
            }
            res.add(levelList);
            levelList = new ArrayList<>();
            while (stackRight.size() != 0){
                //出栈
                currNode = stackRight.pop();
                levelList.add(currNode.val);
                //入栈right,先右后左
                if(currNode.right != null){
                    stackLeft.push(currNode.right);
                }
                if (currNode.left != null){
                    stackLeft.push(currNode.left);
                }
            }
            //多一个判断，防止空list
            if(levelList.size() != 0){
                res.add(levelList);
            }
        }
    }

    public static void main(String[] args) {
        ZigzagLevelOrder case1 = new ZigzagLevelOrder();
        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode20 = new TreeNode(20,treeNode15,treeNode7);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(3,treeNode9,treeNode20);

        List<List<Integer>> lists = case1.levelOrder(treeNode3);
        System.out.println(lists);
    }
}
