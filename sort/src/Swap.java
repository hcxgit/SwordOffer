/**
 * @author 三笠阿克曼
 * @date 2021/10/21
 */
public class Swap {
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
