package datastruct;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 插入排序：每次从未排序数组的头部选定元素，插入到已排序输入对应的位置
 */
public class InsertSort {

    public static void insertSort(int[] arrays) {

        int len = arrays.length;
        int temp;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arrays[j] < arrays[j - 1]) {
                    Swap.swap(arrays, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] list = {1, 4, 2, 1, 3, 3, 9};
        insertSort(list);
        System.out.println(Arrays.toString(list));
    }
}
