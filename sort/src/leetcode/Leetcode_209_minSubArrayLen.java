package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/29
 * @description Leetcode 209: 长度最小的子数组
 * 1、前缀和 + 二分查找
 * 2、滑动窗口
 */
public class Leetcode_209_minSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        // return sideWindow(target,nums);
        return preSumAndbinarySearch(target,nums);
    }

    /**
     1、前缀和 + 二分查找
     - sums[i]: nums【前i元素的和】
     - 目的：找sums[k]-sums[j] >= s，此时，【k-j 就是满足的连续子数组】  (找最小的)
     - 等价于找：sums[j]+s <= sums[k],因为sums是递增的，所以可以用【二分查找】
     */
    public int preSumAndbinarySearch(int target,int[] nums){
        int[] sums = new int[nums.length + 1]; // 方便计算sums[0] = 0
        int res = Integer.MAX_VALUE;

        // 初始化sums
        for(int i=1;i<=nums.length;i++){
            sums[i] = sums[i-1] + nums[i-1];
        }

        // 二分查找
        for(int k=nums.length;k>0;k--){
            int sums_k = sums[k];
            if(sums_k >= target){ // sums[k] >= target
                int idx_j = binarySearch(sums,sums_k-target,k);
                res = Math.min(res,k-idx_j);
            }
        }
        return res == Integer.MAX_VALUE?0:res;
    }

    public int binarySearch(int[] sums,int target,int right){
        int left = 0;
        int mid;

        while(left <= right){
            mid = (left + right)/2;

            if(sums[mid] == target){
                return mid;
            }else if(sums[mid] > target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }

        return right;
    }

    /**
     2、滑动窗口
     - 计算sum;
     - while(【当前窗口的和】 >= target):
     -【更新结果，左窗口右移】;
     - 【右窗口右移】
     */
    public int sideWindow(int target,int[] nums){
        int left = 0, right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while(right < nums.length){
            sum += nums[right];
            while(sum >= target){
                // 1、更新结果，左窗口右移
                res = Math.min(res,right - left + 1);

                sum -= nums[left];
                left ++;
            }
            // 2、右窗口右移
            right ++;
        }
        return res == Integer.MAX_VALUE?0:res;
    }
}
