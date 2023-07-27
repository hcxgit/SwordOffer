package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2023/7/27
 * @description Leetcode 438: 找到字符串中所有字母异位词
 *
 * 1、滑动窗口
 */
public class Leetcode_138_findAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        /**
         1、滑动窗口
         */

        int len_p = p.length();
        int len_s = s.length();
        List<Integer> res = new ArrayList<>();
        if(len_s < len_p){
            return res;
        }

        int[] strs_p = new int[26];
        int[] strs_s = new int[26];

        // 初始化字符串p数组
        for(int i=0;i<len_p;i++){
            strs_p[p.charAt(i) - 'a']++;
        }

        int left = 0,right = len_p-1;
        while(right < len_s){
            if(left == 0){
                // 第一次时，初始化字符串s数组
                for(int i=0;i<len_p;i++){
                    strs_s[s.charAt(i) - 'a'] ++;
                }
            }else{
                // 更新字符串s数组
                strs_s[s.charAt(left-1) - 'a'] --; // 去掉上一次的left
                strs_s[s.charAt(right) - 'a'] ++;  // 加上这一次的right
            }

            // 判断s、p数组是否相等,加入结果
            if(Arrays.equals(strs_p,strs_s)){
                res.add(left);
            }

            // 更新窗口
            left ++;
            right ++;
        }

        return res;
    }
}
