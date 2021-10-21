import java.util.Arrays;

/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 * 堆排序：1、建堆 2、根节点和尾节点交换，调整堆
 */
public class HeapSort {

    public static void heapSort(int[] arrays) {

        //初始化建堆
        for (int root = arrays.length / 2 - 1; root >= 0; root--) {
            heapify(arrays, root, arrays.length - 1);
        }

        //排序：交换,调整
        for (int heapEnd = arrays.length - 1; heapEnd >= 0; heapEnd--) {
            Swap.swap(arrays, heapEnd, 0);
            heapify(arrays, 0, heapEnd);
        }
    }

    //堆化
    public static void heapify(int[] arrays, int root, int end) {
        if (root >= end) {
            return;
        }

        //初始化最大值的节点下标是根节点下标
        int largest = root;
        int left = root * 2 + 1;
        int right = root * 2 + 2;

        //找根节点和左右子节点最大值
        if (left < end && arrays[left] > arrays[largest]) {
            largest = left;
        }
        if (right < end && arrays[right] > arrays[largest]) {
            largest = right;
        }

        if (largest != root) {
            Swap.swap(arrays, largest, root);
            heapify(arrays, largest, end);
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 4, 2, 1, 3, 3, 9};
        heapSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
