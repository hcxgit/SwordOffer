package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/8/13
 *
 * Leetcode 240: 搜索二维矩阵2
 *
 *  1、二分法
 *      从【右上角开始遍历】：
 *          1、num[i][j] < target, 则往下走，i++
 *          2、num[i][j] > target, 则往左走，i++
 */
public class Leetcode_240_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0, j=matrix[0].length-1;
        while(i < matrix.length && j >=0){
            if(matrix[i][j] == target){
                return true;
            }

            if(matrix[i][j] < target){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }
}
