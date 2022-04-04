package datastruct;

import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 二分查找
 */
public class BinarySearch {


    //递归
    public static int binarySearch(int[] arrays, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (arrays[mid] == target) {
            return mid;
        } else if (arrays[mid] > target) {
            return binarySearch(arrays, target, left, mid - 1);
        } else {
            return binarySearch(arrays, target, mid + 1, right);
        }
    }

    //非递归
    public static int binarySearch(int[] arrays, int target) {
        int left = 0;
        int right = arrays.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arrays[mid] == target) {
                return mid;
            } else if (arrays[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] list = {1, 4, 2, 1, 3, 3, 9};
        QuickSort.quickSort(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
        int res = binarySearch(list, 4, 0, list.length - 1);
        int res2 = binarySearch(list, 4);
        System.out.println(res);
        System.out.println(res2);
    }
}
