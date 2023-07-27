package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/7/27
 * @description Leetcode 287: 寻找重复的数
 *
 *      1、二分查找
 *         - 对于mid，如果nums中 <= mid的数有count个:
 *         - 如果count > mid,说明重复的数在【left,mid】
 *         - 否则，重复的数在【mid+1,right】
 */
public class Leetcode_287_findDuplicate {
    public int findDuplicate(int[] nums) {

        int right = nums.length-1;
        int left = 1;

        while(left < right){
            int mid = (left + right)/2;
            int count = 0;

            for(int num:nums){
                if(num <= mid){
                    count ++;
                }
            }

            if(count > mid){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        return left;
    }
}
