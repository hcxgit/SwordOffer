package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2023/4/30
 * @description Leetcode 784 字母大小写全排列
 * 1、回溯法
 */
public class Leetcode_784_letterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs(0,"",res,s);
        return res;
    }

    public void dfs(int start,String currStr,List<String> res, String s){
        //1、终止条件：字符全遍历
        if(start >= s.length()){
            res.add(currStr);
            return;
        }
        //2、剪枝
        //3、选择: 转或者不转
        //1)、处理当前节点
        //2)、递归
        //3)、回溯
        char c = s.charAt(start);
        if(c >= '0'&& c <= '9'){  //数字直接拼接
            dfs(start+1,currStr + c,res,s);
        }else{
            dfs(start+1,currStr + trans(c),res,s);
            dfs(start+1,currStr + c,res,s);
        }
    }

    /**
     * 字符转换
     */
    public char trans(char c){
        if(c >='A' && c <='Z'){
            return Character.toLowerCase(c);
        }
        return Character.toUpperCase(c);
    }
}
