import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 归并排序：1、分数组 2、先排序左右子数组 3、再合并两个子数组
 */
public class MergeSort {

    public static void mergeSort(int[] arrays, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        //1、分数组
        int mid = (left + right) >> 1;

        //2、左右递归排序
        mergeSort(arrays, left, mid, temp);
        mergeSort(arrays, mid + 1, right, temp);

        //合并
        merge(arrays, left, mid, right, temp);
    }

    private static void merge(int[] arrays, int left, int mid, int right, int[] temp) {

        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (arrays[leftIndex] < arrays[rightIndex]) {
                temp[tempIndex++] = arrays[leftIndex++];
            } else {
                temp[tempIndex++] = arrays[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            temp[tempIndex++] = arrays[leftIndex++];
        }

        while (rightIndex <= right) {
            temp[tempIndex++] = arrays[rightIndex++];
        }

        for (int i = left; i <= right; i++) {
            arrays[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 4, 2, 1, 3, 3, 9};
        mergeSort(arrays, 0, arrays.length - 1, new int[arrays.length]);
        System.out.println(Arrays.toString(arrays));
    }
}
