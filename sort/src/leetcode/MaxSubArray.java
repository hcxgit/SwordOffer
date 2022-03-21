package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/11
 * Leetcode 53: 最大子数组和
 * 思路一：动态规划
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {

        //以后一个数为起点的最大和
        int next = nums[nums.length-1];
        int res = next;

        //倒叙
        for(int i=nums.length-2;i>=0;i--){
            next = Math.max(next+nums[i],nums[i]);
            res = Math.max(res,next);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSubArray case1 = new MaxSubArray();
        int res = case1.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(res);
    }
}
