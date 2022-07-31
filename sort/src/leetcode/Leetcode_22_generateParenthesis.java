package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/7/31
 *
 * Leetcode 22: 括号生成
 *
 *  1、回溯(dfs)
 *      dfs递归搜索，
 *
 *     减枝：【任意前缀】【左括号数量一定 >= 右括号数量】
 */
public class Leetcode_22_generateParenthesis {
    public List<String> generateParenthesis(int n) {

        ArrayList<String> res = new ArrayList<>();
        String s = "";
        dfs(res,0,0,n,s);
        return res;
    }

    public void dfs(List<String> res,int left,int right,int n, String s) {
        //剪枝：越界，或者右括号>左括号
        if(left > n || right >left){
            return;
        }

        //左右括号都用了，加入res
        if(right == n){
            res.add(s);
            return;
        }


        //加入左括号
        dfs(res,left+1,right,n,s+"(");
        //加入右括号
        dfs(res,left,right+1,n,s+")");

    }


    public static void main(String[] args) {
        Leetcode_22_generateParenthesis test = new Leetcode_22_generateParenthesis();
        test.generateParenthesis(3);
    }
}
