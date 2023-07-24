package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2023/7/24
 * @description Leetcode 49: 字母异位词分组
 *
 * 1、词频统计拼字符串 + map
 */
public class Leetcode_49_groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        /**
         1、词频统计拼字符串 + map
         */
        Map<String, List<String>> map = new HashMap<>();
            for(String s:strs){
            //存字符的次数，然后拼接成字符串: a2b1c1
            int[] strArray = new int[26];
            for(int i=0;i<s.length();i++){
                strArray[s.charAt(i) - 'a'] ++;
            }

            StringBuffer sb = new StringBuffer();
            for(int i=0;i<26;i++){
                sb.append((char)(i+'a'));
                sb.append(strArray[i]);
            }
            List<String> list = map.getOrDefault(sb.toString(),new ArrayList<>());
            list.add(s);
            map.put(sb.toString(),list);
        }

            return new ArrayList<>(map.values());
    }
}
