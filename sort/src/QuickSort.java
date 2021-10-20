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

//        1、基准值
        int mid = arrays[(right + left) >> 1];

//        2、双指针扫描，将数分散在基准值两边
        int leftIndex = left;
        int rightIndex = right;
        while (leftIndex <= rightIndex) {
//            左边找大于基准值，右边找小于基准值，交换
            while (leftIndex <= rightIndex && arrays[leftIndex] < mid) {
                leftIndex += 1;
            }

            while (leftIndex <= rightIndex && arrays[rightIndex] > mid) {
                rightIndex -= 1;
            }

            if (leftIndex <= rightIndex) {
                Swap.swap(arrays, leftIndex, rightIndex);

                leftIndex += 1;
                rightIndex -= 1;
            }
        }

//        递归排序左区间和右区间

        quickSort(arrays, left, rightIndex);
        quickSort(arrays, leftIndex, right);
    }


    public static void main(String[] args) {
        int[] list = {1, 4, 2, 1, 3, 3, 9};
        quickSort(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
    }
}
