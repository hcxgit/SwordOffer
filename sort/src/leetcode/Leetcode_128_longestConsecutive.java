package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 三笠阿克曼
 * @date 2021/12/27
 * Leetcode: 128  最长连续序列
 * 问题：给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
 * 方案：哈希表
 */
public class Leetcode_128_longestConsecutive {

    public static int solution(int[] nums) {

        Set<Integer> num_set = new HashSet<>();
        int longestConsecutive = 0;
        for (int num : nums) {
            num_set.add(num);
        }

        //遍历查找以当前数为起点的连续序列的长度（如果有更小的，则表示该数不是起点，跳过该值）
        for (int num : num_set) {
            //找连续序列的最小值，还有更小的则跳过
            if (!num_set.contains(num - 1)) {
                int currentnum = 1;
                //查找比该值大的x+1,x+2.....最长序列
                while (num_set.contains(num + 1)) {
                    currentnum++;
                    num++;
                }

                longestConsecutive = Math.max(currentnum, longestConsecutive);
            }
        }
        return longestConsecutive;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(Leetcode_128_longestConsecutive.solution(nums2));
    }
}
