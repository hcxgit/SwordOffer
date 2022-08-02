package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 三笠阿克曼
 * @date 2022/7/26
 *
 * Leetcode 31. 下一个排列
 *
 *   1、两遍扫描
 *      1、【从后向前】查找第一个【相邻升序】的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 *      2、在 [j,end) 【从后向前】查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 *      3、将 A[i] 与 A[k] 交换
 *      4、可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 *      5、如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 */
public class Leetcode_31_nextPermutation {
    public void nextPermutation(int[] nums) {

        int len = nums.length;
        if(len <= 1){
            return;
        }

        int minIndex = len-2;
        //找要交换的【小数】
        while(minIndex >=0 && nums[minIndex] >= nums[minIndex+1]){
            minIndex --;
        }
        //没找到就直接逆置
        if(minIndex < 0){
            reverse(nums,0);
        }else{
            //找到【小数】，再找【最小大数】
            int maxIndex = len-1;
            while(nums[maxIndex] <= nums[minIndex]){ //注意，相等的要继续找大的
                maxIndex--;
            }
            //交换，然后逆置后面的
            swap(nums,maxIndex,minIndex);
            reverse(nums,minIndex+1);
        }

    }
    //逆置
    public void reverse(int[] nums,int start) {
        int left = start,right = nums.length-1;
        while(left < right){
            swap(nums,left,right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums,int n1,int n2) {
        int temp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2};
        Leetcode_31_nextPermutation nextPermutation = new Leetcode_31_nextPermutation();
        nextPermutation.nextPermutation(nums);
        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(System.out::print);
    }
}
