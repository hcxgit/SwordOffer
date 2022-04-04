package datastruct;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 冒泡排序：比较，每次选择最大放后面
 */
public class BubbleSort {


    public static void bubbleSort(int[] list) {
        int len = list.length;
        int temp;
        boolean flag;

        for (int i = 0; i < len; i++) {
            flag = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    Swap.swap(list, j, j + 1);
                    flag = true;
                }
            }

            //当前一轮没有交换，说明已经有序，提前退出
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] list = {1, 4, 2, 1, 3, 3, 9};
        bubbleSort(list);
        System.out.println(Arrays.toString(list));
    }
}
