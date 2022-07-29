package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/7/29
 *
 * Leetcode 54: 螺旋矩阵
 *
 *  1、模拟
 *
 *  2、按层模拟
 *      将矩阵看成【若干层】，首先输出【最外层】的元素，其次输出【次外层】的元素。。。。
 *      注意：【上下左右边界】，可分别用变量表示。
 *
 */
public class Leetcode_54_spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int line = matrix[0].length;
        List<Integer> res = new ArrayList<>();

        //上下左右边界
        int top = 0,bottom = row-1,left = 0,right = line-1;
        while( top <= bottom && left <=right){

            //左——>右
            for (int l = left; l <= right; l++) {
                res.add(matrix[top][l]);
            }
            //右上——>右下
            for (int r = top+1; r <= bottom; r++) {
                res.add(matrix[r][right]);
            }
            //防止最后一层剩下的长方形（一行/一列），重复遍历
            if(left < right && top < bottom){
                //右下——>左下
                for (int l = right-1; l >= left+1; l--) {
                    res.add(matrix[bottom][l]);
                }
                //左下——>左上
                for (int r = bottom; r >= top+1; r--) {
                    res.add(matrix[r][left]);
                }
            }

            //更新
            top++;
            bottom--;
            left++;
            right--;
        }
        return res;
    }

    public static void main(String[] args) {
        Leetcode_54_spiralOrder test = new Leetcode_54_spiralOrder();
        int [][] matrix = new int [2][1];
        matrix[0] = new int[]{3};
        matrix[1] = new int[]{2};
        List<Integer> res = test.spiralOrder(matrix);
        System.out.println(res);
    }
}
