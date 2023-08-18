package leetcode;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2023/8/18
 * @description Leetcode 581: 最短无序连续子数组
 *
 *    1、先排序，再比较
 *         - 排序后，则数组可分为：【0,left】、【left,right】、【right,length】三部分
 *         - 【left,right】就是【无序数组】。
 *
 *    2、双指针
 *       - 目的：找【无序数组】：【left,right】
 *       - 从【左 —> 右遍历】，维护max，right(【无序数组的右边界】)：
 *          - 如果num[i] < max,则 right = i;
 *             - 因为从左—>右,如果num[i]不符合,就更新right,最后一个不符合的就是【无序数组最右边界】
 *          - 否则：更新max
 *       - 同理，从【右 —> 左遍历】，维护min，left(【无序数组的左边界】)：
 *          - 如果num[i] > min,则 left = i;
 *          - 否则：更新min
 *       - 最后，如果left == -1（初始化时的数），说明有序，返回0，否则返回right-left+1
 */
public class Leetcode_581_findUnsortedSubarray {
    /**
        1、排序
     */
    public int findUnsortedSubarray(int[] nums) {
        // 如果有序，直接返回
        if(isSorted(nums)){
            return 0;
        }

        // 拷贝数组，排序
        int len = nums.length;
        int[] numsSorted = new int[len];
        System.arraycopy(nums,0,numsSorted,0,len);
        Arrays.sort(numsSorted);

        // 比较
        int left=0,right=len-1;
        while(nums[left] == numsSorted[left]){
            left++;
        }

        while(nums[right] == numsSorted[right]){
            right--;
        }


        return right-left+1;
    }

    public boolean isSorted(int[] nums){
        for(int i=1;i<nums.length;i++){
            if(nums[i] < nums[i-1]){
                return false;
            }
        }

        return true;
    }

    /**
     2、双指针
     */
    public int findUnsortedSubarray_2(int[] nums) {
        int len = nums.length;
        int left = -1,right = -1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0;i<len;i++){
            // 从左——>右
            if(nums[i] < max){
                right = i;
            }else{
                max = nums[i];
            }

            // 从右向左
            if(nums[len-1-i] > min){
                left = len-1-i;
            }else{
                min = nums[len-1-i];
            }
        }

        // left还是-1，说明有序
        return left == -1 ?0:right-left+1;
    }
}
