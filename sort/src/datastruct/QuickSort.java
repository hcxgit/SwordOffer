package datastruct;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 快排：1、确定基准值，
 * 2、小于它的移动到其左边，大于它的数移动到右边，
 * 3、左右两个子区间递归进行快速排序。
 */
public class QuickSort {

    public static void quickSort(int[] arrays, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = partition(arrays,left,right);
//        递归排序左区间和右区间

        quickSort(arrays, left, mid-1);
        quickSort(arrays, mid+1, right);
    }

    public static int partition(int[] arrays, int left, int right){
        // 1、基准值,放到最左边
        Swap.swap(arrays,left,(right + left) >> 1);
        int mid = arrays[left];

        // 2、双指针扫描，将数分散在基准值两边
        int leftIndex = left;
        int rightIndex = right;
        //双指针碰撞时，退出循环
        while (leftIndex < rightIndex) {
        // 左边找大于基准值，右边找小于基准值，交换(注意：升序从右边开始扫描，降序从左边开始扫描)
            while (leftIndex < rightIndex && arrays[rightIndex] >= mid) {
                rightIndex -= 1;
            }
            while (leftIndex < rightIndex && arrays[leftIndex] <= mid) {
                leftIndex += 1;
            }
            Swap.swap(arrays, leftIndex, rightIndex);
        }
        //基准值放中间
        Swap.swap(arrays,left,leftIndex);

        return leftIndex;
    }


    public static void main(String[] args) {
        int[] list = {5,2,3,1};
        quickSort(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
    }
}
