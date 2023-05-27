package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 三笠阿克曼
 * @date 2023/5/27
 * @description Leetcode 139: 单词拆分
 */
public class Leetcode_139_wordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 1、动态规划
        // dp[i]: 以str[i]为结尾的字符能否拼接出s
        // dp[i] = dp[j] && check(str[j:i])

        boolean[] dp = new boolean[s.length()+1];
        Set<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j] && check(set,s.substring(j,i))){
                    dp[i] = true;
                    break; // 当前已经true，跳出内循环
                }
            }
        }
        return dp[s.length()];
    }

    public boolean check(Set<String> set, String str){
        return set.contains(str);
    }
}
