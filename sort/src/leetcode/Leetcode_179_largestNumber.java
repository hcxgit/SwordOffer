package leetcode;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2023/6/27
 * @description Leetcode 179：最大数
 */
public class Leetcode_179_largestNumber {
    public String largestNumber(int[] nums) {
        /**
         1、排序：
         - "a"+"b" > "b"+"a", 则a>b
         - Arrays.sort(str_nums,(a,b)->{return (a+b).compareTo((b+a))})
         */

        String[] strs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i] = String.valueOf(nums[i]);
        }

        // 倒叙
        Arrays.sort(strs,(a, b) -> {
            return (b+a).compareTo((a+b));
        });

        // 最大数是0，则返回0
        if("0".equals(strs[0])){
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        for(String s:strs){
            sb.append(s);
        }
        return sb.toString();
    }
}
