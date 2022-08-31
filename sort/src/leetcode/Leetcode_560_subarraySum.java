package leetcode;

import java.util.HashMap;

/**
 * @author 三笠阿克曼
 * @date 2022/8/31
 *
 *  1、暴力枚举
 *
 *  2、前缀和 + hash表
 *      前缀和：nums[i:j] = nums[:j] - nums[:i] = k
 *      类比：【两数之和】，为a+b=k，遍历数组，若a已加入哈希表，对于b，看k-b是不是在哈希表中；
 *      本题，问题转化为【两数之差】，为a-b=k，看【a-k】是否在hash表。
 *
 *      1、<k:v>   k:前缀和， v: 个数
 *      2、初始，{0:1}
 *      3、count += hash.get(preSum-k);
 */
public class Leetcode_560_subarraySum {
    // 1、暴力枚举
    public static int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if(sum == k){
                res ++;
            }
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k){
                    res ++;
                }
            }
        }
        return res;
    }

    // 2、前缀和 + hash表
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer,Integer> hash = new HashMap<>();
        hash.put(0,1); // 注意
        int res = 0;
        int preSum= 0;
        for (int i:nums) {
            preSum += i;
            if(hash.containsKey(preSum-k)){
                res += hash.get(preSum-k); // 
            }
            if(hash.containsKey(preSum)){
                hash.put(preSum,hash.get(preSum)+1);
            }else{
                hash.put(preSum,1);
            }
        }
        return res;
    }

}
