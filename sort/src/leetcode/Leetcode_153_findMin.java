package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/29
 * @description Leetcode 153: 寻找旋转排序数组中的最小值
 *  1、二分查找
 *    - nums[0] < nums[-1]:
 *        数组旋转后是【一段升序数组】，则直接返回nums[0]
 *    - 否则：
 *       【二段升序数组】,二分查找【第二段的起始值】
 */
public class Leetcode_153_findMin {
    public int findMin(int[] nums) {
        //1、【一段升序数组】
        if(nums[0] < nums[nums.length-1]){
            return nums[0];
        }

        //2、【2段升序数组】
        return binarySearch(nums);
    }

    public int binarySearch(int[] nums){
        int left = 0,right = nums.length-1;
        while(left < right){
            int mid = (left + right)/2;

            // 1、mid > right,则第二段在右边
            if(nums[mid] > nums[right]){
                left = mid+1;
            }else{
                // 2、mid <= right,则第二段最小值在左边 || mid就是最小值
                right = mid; // 注意这里不是mid-1,防止走过头
            }
        }
        return nums[left];
    }
}
