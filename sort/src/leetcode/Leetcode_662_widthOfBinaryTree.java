package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2023/6/25
 * @description Leetcode 662: 二叉树最大宽度
 *  1、bfs + 节点编号
 *  2、dfs + 节点编号
 */
public class Leetcode_662_widthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        // 1、bfs
        // return bfs(root);

        // 2、dfs
        Map<Integer,Integer> map = new HashMap<>(); // level：最左节点编号
        return dfs(root,0,0,map);
    }

    /**
     1、bfs + 节点编号
     - 按照完全二叉树对每个节点【编号】： left = 2*root, right = 2*root+1;
     - 每一层宽度 = 最右节点编号 - 最左节点编号 + 1
     */
    public int bfs(TreeNode root){
        Map<TreeNode,Integer> map = new HashMap<>(); // 节点：节点编号
        Deque<TreeNode> dq = new LinkedList<>();
        int res = 1;

        map.put(root,0);
        dq.add(root);

        while(!dq.isEmpty()){
            int left = map.get(dq.peek()); // 当前层最左节点编号
            int levelNum = dq.size();               // 当前层的节点数

            // 遍历当前层
            TreeNode curr;
            int currNum = left;
            for(int i=0;i<levelNum;i++){
                curr = dq.remove();
                currNum = map.get(curr);

                // 左子树编号
                if(curr.left != null){
                    dq.add(curr.left);
                    map.put(curr.left, 2 * currNum);
                }

                // 右子树编号
                if(curr.right != null){
                    dq.add(curr.right);
                    map.put(curr.right, 2 * currNum + 1);
                }
            }
            res = Math.max(res,currNum - left + 1);
        }

        return res;
    }

    /**
     1、dfs + 节点编号
     - 按照完全二叉树对每个节点【编号】： left = 2*root, right = 2*root+1;
     - map<level: leftNum>
     - 每一层宽度 = max(currNum) - leftNum + 1
     */
    public int dfs(TreeNode root,int level,int num,Map<Integer,Integer> map){
        if(root == null){
            return 0;
        }

        map.putIfAbsent(level,num); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        int currLevel = num - map.get(level) + 1;
        int nextLevel = Math.max(dfs(root.left,level + 1, 2 * num,map), dfs(root.right,level + 1, 2 * num + 1,map));
        return Math.max(currLevel,nextLevel);
    }
}
