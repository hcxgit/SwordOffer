package leetcode;

import datastruct.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author 三笠阿克曼
 * @date 2022/4/3
 * leetcode 236：二叉树的最近公共祖先。
 *  给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 思路一：遍历，结果{子：父}放map中，然后类似找链表的公共节点，找两个节点的祖先链的交点
 * 思路二：递归 + DFS。若 root 是 p,q的最近公共祖先则
 *          1、pq分别在左右子树中
 *          2、p=root,q在其子树中
 *          3、q=root，p在其子树中
 *     解决：所以，可以递归遍历左右子树，找pq的公共祖先。根据左右子树的返回值left,right分为四种情况：
 *       1、left right都是null,说明 root 的左/右子树中都不包含 p,q，返回 null；
 *       2、left right都不为null 说明 p,q 分别在左/右子树），因此root为最近公共祖先，返回root；
 *       3、left为空 ，right不为空 ：p,q 都不在 root 的左子树中，直接返回 right;
 *       4、left不为空， right为空 ：p,q 都不在 root 的右子树中，直接返回 left；
 */
public class LowestCommonAncestor {

    //1、递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q); //递归找左子树中pq的父节点
        TreeNode right = lowestCommonAncestor(root.right, p, q);//递归找右子树中pq的父节点
        if(left == null && right == null) {
            return null; // 1.左右子树均为null
        }
        if(left == null) {
            return right; // 3. 左子树为null，结果在右子树
        }
        if(right == null) {
            return left; // 4.右子树为null，结果在左子树
        }
        return root; // 2. 左右都不为null，说明pq位于左右两侧，当前节点就是最近公共祖先。
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p == root || q == root){
            return root;
        }

        HashMap<TreeNode,TreeNode> map =  new HashMap<>();
        boolean flagP = false;
        boolean flagQ = false;

        //1、遍历，存map中  {子：父}
        preOrder(root,p,q,map,flagP,flagQ);

        TreeNode currP = p;
        TreeNode currQ = q;
        HashSet<TreeNode> parentOfP =  new HashSet<>(); //p的父节点
        HashSet<TreeNode> parentOfQ =  new HashSet<>(); //q的父节点
        //从pq开始遍历查找父节点,当成找两条链表的公共节点
        while(currP != root || currQ != root){
            if(currP != root){
                currP = map.get(currP);
                if(currP == q){ //遍历p的父节点时，遇到q,则返回q
                    return q;
                }else if(parentOfQ.contains(currP)){ //遍历p的父节点时，遇到q的父节点包含该节点,则返回该节点
                    return currP;
                }else{
                    parentOfP.add(currP);
                }
            }

            if(currQ != root){
                currQ = map.get(currQ);
                if(currQ == p){ //遍历q的父节点时，遇到p,则返回p
                    return p;
                }else if(parentOfP.contains(currQ)){ //遍历q的父节点时，遇到p的父节点包含该节点,则返回该节点
                    return currQ;
                }else{
                    parentOfQ.add(currQ);
                }
            }
        }

        return root;
    }
    //遍历，存map中  {子：父}
    public static void preOrder(TreeNode root,TreeNode p, TreeNode q,HashMap map,boolean flagP,boolean flagQ){
        if(root == null){
            return;
        }

        if(flagP && flagQ){
            return;
        }

        if(root == p){
            flagP = true;
        }
        if(root == q){
            flagQ = true;
        }

        if(root.left != null){
            map.put(root.left,root);
            preOrder(root.left,p,q,map,flagP,flagQ);
        }
        if(root.right != null){
            map.put(root.right,root);
            preOrder(root.right,p,q,map,flagP,flagQ);
        }
    }

    public static void main(String[] args) {

        TreeNode node7 = new TreeNode(7);
        TreeNode q = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node2 = new TreeNode(node7,q,2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node1 = new TreeNode(node0,node8,1);
        TreeNode p = new TreeNode(node6,node2,5);
        TreeNode root = new TreeNode(p,node1,3);
        TreeNode res = LowestCommonAncestor.lowestCommonAncestor(root, p, q);
        System.out.println(res.data);
    }
}
