package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/8/12
 *
 * Leetcode 56: 合并区间
 *
 *  1、排序
 *      1、区间数组按照【左端点排序】，排完序的列表中，【可以合并的区间一定是连续的】
 *      2、遍历【每个区间】
 *          1、下一个区间【重叠】，即intervals[i+1][0] <= right：
 *              则更新 right = Math.max(right,intervals[i+1][1])，【继续循环遍历】，直到【不满足重叠】
 *
 *          2、当前区间[left,right]放入res
 *          3、更新下一个区间   i++
 *
 */
public class Leetcode_56_merge {
    public int[][] merge(int[][] intervals) {
        // 1、排序
        Arrays.sort(intervals,(a,b) -> a[0]-b[0]);

        //2、遍历
        int i = 0;
        List<int[]> res = new ArrayList<>();

        while(i < intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];

            // 1、下一个区间重叠,则继续遍历
             while(i < intervals.length-1 && intervals[i+1][0] <= right){
                 right = Math.max(right,intervals[i+1][1]);
                 i++;
             }

             //2、当前区间放入res
             res.add(new int[]{left,right});

             //3、更新下一个区间
            i++;
        }

        return res.toArray(new int[0][]);
    }
}
