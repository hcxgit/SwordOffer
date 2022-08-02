package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/26
 *
 *
 * Leetcode 33：搜索旋转排序数组
 *
 *  1、二分查找
 *
 *      二分后，【一半有序】，【一半部分有序】。
 *
 *      注意：边界值
 */
public class Leetcode_33_searchRotationSortArray {
    public int search(int[] nums, int target) {

        int len = nums.length;

        if(len == 0){
            return -1;
        }
        if(len==1 && target == nums[0]){
            return target == nums[0]? 0:-1;
        }

        int left = 0, right = len-1, mid;
        while (left <= right){
            mid = (left + right)/2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[mid] < target){
                // mid在右递增数组 && target > right, 左边找
                if(nums[mid] < nums[left] && target > nums[right]){
                    right = mid - 1;
                }else{
                    // 其余右边找
                    left = mid + 1;
                }
            }else{
                // mid在左递增数组 && target < left, 右边找
                if(nums[mid] >= nums[left] && target < nums[left]){
                    // 右边找
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
