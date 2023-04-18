package leetcode;

import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2022/3/8
 * Leetcode 39 ：组合总数
 */
public class Leetcode_39_combinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        dfs(0,path,res,candidates,target);
        return res;
    }

    public void dfs(int start, Deque<Integer> path, List<List<Integer>> res, int[] candidates, int target) {
        //1、终止条件
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        //2、剪枝: target为负数、剩余数太大
        if (target < 0 || candidates[start] > target) {
            return;
        }
        //3、选择
        for (int i = start; i < candidates.length; i++) {
            // 1)、处理当前节点
            path.addLast(candidates[i]);
            // 2)、递归
            dfs(i, path, res, candidates, target-candidates[i]);
            // 3)、回溯
            path.removeLast();
        }
    }
}
