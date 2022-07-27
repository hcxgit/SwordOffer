package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/7/27
 *
 *  Leetcode 46：全排列
 *
 *      1、回溯法
 *         全排列可以理解成【空格填数】：[1,2,3]
 *         1、先填【1】开头的排列，也就是 1 + [2,3]的全排列  （递归，深度遍历）
 *         2、再填【2】开头。。。
 *         3、【3】开头。。。
 *
 *      过程：
 *          1、遍历，【选一个数】，【递归】填剩下的【没用过的数】。（记录【状态】是否用过）
 *          2、【回溯】，【去掉选的数，改状态】，【选别的数】，【递归】。
 *          3、【递归终止条件】是：【一个排列中的数选够了】。
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        // res：返回结果，path：当前排列，flag：是否用过
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] flag = new boolean[len];

        if(len == 0){
            return res;
        }

        dfs(nums,len,0,res,flag,path);
        return res;
    }

    // 该填第index位置
    public void dfs(int[] nums,int len, int index, List<List<Integer>> res,boolean[] flag, List<Integer> path) {
        //递归终止
        if(index == len){
            res.add(new ArrayList<Integer>(path)); //path用的同一个引用，需要拷贝一下数据
        }

        for (int i =0;i<len;i++){
            // 没用过
            if(!flag[i]){
                //填数
                path.add(nums[i]);
                flag[i] = true;
                //递归填下面的
                dfs(nums,len,index+1,res,flag, path);
                //回溯
                path.remove(index);
                flag[i] = false;
            }
        }
    }
}
