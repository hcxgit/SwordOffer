import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 选择排序：比较 每次选择最小的和前面交换
 */
public class SelectSort {

    public static void selectSort(int[] arrays) {

        int len = arrays.length;
        int temp;
        int minIndex;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i; j < len; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Swap.swap(arrays, i, minIndex);
            }
        }
    }


    public static void main(String[] args) {
        int[] list = {1, 4, 2, 1, 3, 3, 9};
        selectSort(list);
        System.out.println(Arrays.toString(list));
    }
}
