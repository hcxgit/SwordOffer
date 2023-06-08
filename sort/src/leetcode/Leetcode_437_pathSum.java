package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/8
 * @description Leetcode 437：路径总和 Ⅲ
 */
public class Leetcode_437_pathSum {
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        /**
         4种情况：
         1、不用root + 左子树
         2、不用root + 右子树
         3、用root + 左子树
         4、用root + 右子树
         */
        if(root == null){
            return 0;
        }

        if(targetSum == root.val){
            res +=1;
        }
        dfs(root.left,targetSum,false);
        dfs(root.right,targetSum,false);
        dfs(root.left,targetSum - root.val,true);
        dfs(root.right,targetSum - root.val,true);

        return res;
    }

    /**
     preUsed: 表示其父节点有没有使用
     注意：targetSum用long，防止溢出
     */
    public void dfs(TreeNode root, long targetSum, boolean preUsed) {
        if(root == null){
            return;
        }
        // 找到了
        if(root.val == targetSum){
            res +=1;
        }

        // 注意：【父节点没用，子节点才可以不用；否则必须用】
        if(!preUsed){
            // 1、不用root + 左子树
            dfs(root.left,targetSum,false);
            // 2、不用root + 右子树
            dfs(root.right,targetSum,false);
        }

        // 3、用root + 左子树
        dfs(root.left,targetSum - root.val,true);
        // 4、用root + 右子树
        dfs(root.right,targetSum - root.val,true);
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(1000000000,null,null);
        TreeNode node4 = new TreeNode(1000000000,node5,null);
        TreeNode node3 = new TreeNode(1000000000,node4,null);
        TreeNode node2 = new TreeNode(294967296,node3,null);
        TreeNode node1 = new TreeNode(1000000000,node2,null);
        TreeNode root = new TreeNode(1000000000,node1,null);

        Leetcode_437_pathSum demo = new Leetcode_437_pathSum();
        int num = demo.pathSum(root, 0);
        System.out.println(num);
    }
}
