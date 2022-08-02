package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/25
 * leetcode 88:合并两个非递减有序数组 都放到nums1
 * 思路：倒着遍历，倒着放，只要第二个数组放完了就结束了。
 */
public class Leetcode_88_mergeTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int index1 = m-1;
        int index2 = n-1;
        int index = m+n-1;
        //从后往前遍历
        //由于数是放第一个数组，只要第二个数组放完了就结束了。
        while(index2 >=0){
            //第一个遍历完或者第二个数大，都用第二个
            if(index1 <0 || nums2[index2] > nums1[index1]){
                nums1[index--] = nums2[index2--];
            }else{
                nums1[index--] = nums1[index1--];
            }
        }
    }
}
