package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/30
 * @description Leetcode 162: 寻找峰值
 *   1、二分查找 + 爬坡
 *      如果 mid < mid+1，则峰值在右边：
 *          left = mid+1
 *      否则:
 *          right = mid  (注意，mid可能就是峰值，所以没用mid-1)
 */
public class Leetcode_162_findPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left < right){
            int mid = (left + right)/2;
            if(nums[mid] < nums[mid+1]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
