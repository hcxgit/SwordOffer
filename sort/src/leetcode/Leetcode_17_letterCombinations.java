package leetcode;

import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2022/3/8
 * Leetcode 17 ：电话号码的字符组合
 */
public class Leetcode_17_letterCombinations {

    public List<String> letterCombinations(String s) {

        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        if(Objects.equals(s, "")){
            return res;
        }

        Map<Character,String> map = new HashMap<>(8);
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        dfs(0,path,res,s,map);
        return res;

    }

    public void dfs(int start,StringBuilder path, List<String> res, String s, Map<Character,String> map){
        // 1、结束条件：数字都已经使用过了
        if(start >= s.length()){
            res.add(path.toString());
            return;
        }

        //2、剪枝
        //3、选择
        String currStr = map.get(s.charAt(start));
        for(int i=0; i< currStr.length();i++){
            // 1)、处理当前节点
            path.append(currStr.charAt(i));
            // 2)、递归
            dfs(start +1,path,res,s,map);
            // 3)、回溯
            path.deleteCharAt(path.length()-1);
        }
    }


    public static void main(String[] args) {
        Leetcode_17_letterCombinations case1 = new Leetcode_17_letterCombinations();
        List<String> ipList = case1.letterCombinations("23");
        System.out.println(ipList);
    }
}
