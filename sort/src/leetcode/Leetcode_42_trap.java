package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/8/1
 *
 * Leetcode 42: 接雨水
 *
 *   1、面积法（双指针）
 *      水的面积 = 总面积 - 柱子的面积
 *
 *      总体积【分段计算】：
 *          双指针：left表示【左边柱子】，right表示【右边更高的柱子】或者【小于left的最高的柱子】
 *
 *          【分段计算】：
 *              对于每个柱子left，找后面【更高的柱子right】，找不到则找后面【矮子里最高的柱子】，【分段面积】= 【left,right)区间的面积
 *                  1、【找到更高】，即num[right] >= [left]，
 *                      【分段面积】 = num[left] * (right-left)
 *                  2、【找不到更高，找矮子里最高】，则num[right] < [left]，
 *                      【分段面积】 = num[left] + num[right] * (right-left-1)    注意右边界
 *
 *          【总面积】 = sum{【分段面积】} + num[len-1]    (left是左边柱子，只取到【倒数第二】)
 *
 *  2、动态规划
 *      1、【位置i雨水的量】取决于i【左右两侧最大值的最小值】，min(leftMax, rightMax)
 *  *          数量为：min(leftMax, rightMax) - height[i];
 *          1、正向遍历数组得到每个元素值leftMax[i]:
 *              leftMax[i]=max(leftMax[i−1],height[i])；
 *          2、反向遍历数组得到每个元素值rightMax[i]:
 *              rightMax[i]=max(rightMax[i+1],height[i])
 *  3、单调栈
 *
 *  4、双指针（对dp的优化）
 *      1、【位置i雨水的量】取决于i【左右两侧最大值的最小值】，min(leftMax, rightMax)
 *          数量为：min(leftMax, rightMax) - height[i];
 *      2、两个指针left和right，left从左—>右遍历，right从右—>左遍历：
 *          1. 对于left, 它【右侧】的【真实的最大值】 >= rightMax,
 *          2. 对于right, 它【左侧】的【真实的最大值】 >= leftMax.
 *      3、综合1、2,没必要【完全计算所有leftMax，rightMax】，只需要找到：
 *          1. 当出现leftMax < rightMax的时候, 【left的位置】能否接雨水就已经【确定了】,
 *          2. 当出现leftMax >= rightMax的时候, 【right的位置】能否接雨水就已经【确定了】
 *
 *
 */
public class Leetcode_42_trap {
    // 1、面积法
    public int trap(int[] height) {

        int len = height.length;
        if(len <= 2){
            return 0;
        }

        //sumALL总面积，sum柱子面积
        int sumALL = 0, sum= 0,left = 0;

        while(left < len-1){

            int right = left+1;
            for (int i = right; i < len; i++) {
                //1、找到更高的
                if(height[i] >= height[left]){
                    right = i;
                    break;
                }
                //2、找不到更高的，找最高的
                if(height[i] >= height[right]){
                    right = i;
                }
            }
            //1、找到更高的
            if(height[right] > height[left]){
                sumALL += height[left] * (right-left);
            }else{
                //2、找不到更高的
                sumALL += height[left] + height[right] * (right-left-1);
            }

            //更新left
            left = right;
        }

        //为了逻辑清晰，柱子单独求和
        for (int i = 0; i < len-1; i++) {
            sum += height[i];
        }

        return sumALL-sum;
    }

    // 2、动态规划
    public int trap2(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int res = 0;

        leftMax[0] = height[0];
        rightMax[len-1] = height[len-1];

        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }

        for (int i = len-2; i >=0 ; i--) {
            rightMax[i] = Math.max(rightMax[i+1],height[i]);
        }

        for (int i = 0; i < len; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return res;
    }
    // 4、双指针
    public int trap4(int[] height) {
        int len = height.length;
        int left = 0, right =len-1;
        int leftMax = 0, rightMax = 0;
        int res = 0;

        while (left < right){
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if(leftMax <= rightMax){
                // left位置确定雨量
                res += leftMax - height[left];
                left ++;
            }else{
                //right位置确定雨量
                res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }
}
