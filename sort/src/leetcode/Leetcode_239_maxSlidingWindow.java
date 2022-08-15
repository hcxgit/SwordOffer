package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 三笠阿克曼
 * @date 2022/8/14
 *
 * Leetcode 239: 滑动窗口最大值
 *
 *  1、单调队列（双端队列）
 *    思路：
 *      - 只在【队列】中保留【可能成为窗口最大元素的元素】，【队首】放【当前最大值】。
 *      - 如果要进来的是个【值大的元素】，那【前面早进去】的【值小的元素】【没用了，直接出队】。
 *      - 所以，队列里其实维护的一个【单调递减的单调队列】。
 *    过程：
 *      - 窗口左右边界: left、right，窗口大小:k
 *      1、【遍历】元素，如果【队列不为空】且【当前元素 >= 队尾元素】:
 *          则将【队尾元素移除】，【直到】，【队列为空】或【当前元素 < 新的队尾元素】；
 *      2、元素入【队尾】
 *      3、计算窗口【左边界】
 *      4、当【队首元素的下标】< 【left】时，将其从【队首移除】。
 *      5、由于数组下标从0开始，因此当【right+1 >=k】时，意味着【窗口形成】。此时，【队首元素】就是该窗口内的【最大值】。
 *
 *      注意：实际【队列中存放元素下标】，方便【越界判断】
 */
public class Leetcode_239_maxSlidingWindow {
    // 1、单调队列（双端队列）
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] res = new int[nums.length-k+1];
        Deque<Integer> deque = new LinkedList<>();

        for(int right = 0; right < nums.length; right++){
            // 1、>= 队尾，队尾出队
            while(!deque.isEmpty() && nums[right] >= nums[deque.peekLast()]){
                deque.removeLast();
            }

            // 2、入队尾
            deque.addLast(right);

            // 3、计算窗口左侧边界
            int left = right - k +1;

            // 4、队首越界，则移除
            if(deque.peekFirst() < left){
                deque.removeFirst();
            }
            // 5、 窗口形成，队首加入res
            if(right >= k-1){
                res[left] = nums[deque.peekFirst()];
            }
        }

        return res;
    }
}
