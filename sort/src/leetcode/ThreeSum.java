package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/3/6
 * LeetCode 15：三数之和
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length <3){
            return res;
        }
        //排序
        Arrays.sort(nums);

        for(int first=0;first<nums.length-2;first++){
            //如果 nums[i]大于 0，则三数之和必然无法等于 0
            if(nums[first] > 0){
                break;
            }
            //去除第一个数循环
            if(first>0 && nums[first] == nums[first-1]){
                continue;
            }

            int left = first+1;
            int right = nums.length-1;

            while(left < right){
                int sum = nums[first]+ nums[left] + nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(nums[first],nums[left],nums[right]));
                    //去除第二个数循环
                    while(left < right && nums[left] == nums[left+1]){
                        ++left;
                    }
                    //去除第三个数循环
                    while(left < right && nums[right] == nums[right-1]){
                        --right;
                    }
                    ++left;
                    --right;
                }else if(sum < 0){
                    ++left;
                }else{
                    --right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = ThreeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
