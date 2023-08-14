package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/8/10
 * @description Leetcode 41：缺失的第一个正数
 *
 *    1、原地hash
 *      - 思路：【将num放在num-1的位置】，【第一个不满足要求的元素下标+1】就是结果。
 *      - 也就是1放在idx为0的位置,2放在idx为1的位置，最后从头遍历，找不符合要求的
 *      - 注意num在【0，lenl-1】的范围才放置
 *      - 第一次遍历：交换位置，【将num放在num-1的位置】
 *      - 第二次遍历：找【第一个不满足要求的元素下标+1】
 *      - 没找到就说明都有，返回len+1
 *
 */
public class Leetcode_41_firstMissingPositive {
    public int firstMissingPositive(int[] nums) {

        int len = nums.length;
        for(int i=0;i<len;i++){
            // 范围内，并且它的位置还没放
            while(nums[i] >0 && nums[i] < len && nums[nums[i] -1] != nums[i]){
                swap(nums,i,nums[i]-1);
            }
        }

        for(int i=0;i<len;i++){
            if(i != nums[i]-1){
                return i+1;
            }
        }

        return len+1;
    }

    public void swap(int[] nums,int n1,int n2){
        int temp = nums[n2];
        nums[n2] = nums[n1];
        nums[n1] = temp;
    }

}
