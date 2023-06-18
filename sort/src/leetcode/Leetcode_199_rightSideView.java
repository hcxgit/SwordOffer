package leetcode;

import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2023/6/18
 * @description Leetcode 199: 二叉树的右视图
 *
 *  层次遍历： 两队列
 */
public class Leetcode_199_rightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        /**
         层次遍历：
            map<hight:value> ： 【树层级：最右节点的值】（每一层只能看到一个节点）
         两个队列：
            dq1：【树高】  dq2：【节点】
            按照left、right顺序入队，map.put()刚好【最右边的节点会覆盖前面的值】
         */
        Deque<TreeNode> nodes = new LinkedList<>();
        Deque<Integer> hights = new LinkedList<>();
        Map<Integer,Integer> rightValueAtHight = new HashMap<>();
        int hight = -1;

        nodes.add(root);
        hights.add(0);
        while(!nodes.isEmpty()){
            TreeNode currNode = nodes.remove();
            int currHight = hights.remove();

            if(currNode != null){
                rightValueAtHight.put(currHight,currNode.val);

                // 更新树高
                hight = Math.max(hight,currHight);

                nodes.add(currNode.left);
                nodes.add(currNode.right);
                hights.add(currHight + 1);
                hights.add(currHight + 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        // 每层取对应的value
        for(int i=0;i <= hight;i++){
            list.add(rightValueAtHight.get(i));
        }
        return list;
    }
}