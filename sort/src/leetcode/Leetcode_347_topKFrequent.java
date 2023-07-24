package leetcode;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author 三笠阿克曼
 * @date 2023/7/21
 * @description Leetcode 347: 前k个高频元素
 *
 *  1、优先级队列
 *  2、小根堆
 *  3、快排
 */
public class Leetcode_347_topKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 1){
            return nums;
        }

        // 统计频次
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }

        return topKFrequent_3(nums,k,map);
    }

    /**
     1、优先级队列 + map
     */
    public int[] topKFrequent_1(int[] nums, int k,Map<Integer,Integer> map) {

        // 优先级队列，存放前k个高频元素
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(k,(a,b) -> {
            return a.getValue()-b.getValue();
        });
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Optional.ofNullable().orElseThrow()
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){

            // 1、队列还没满，直接入队
            if(queue.size() < k){
                queue.add(entry);
                continue;
            }

            // 2、队列满了，则与队首元素比较，决定是否要【替换】
            if(queue.peek().getValue() < entry.getValue()){
                queue.remove();
                queue.add(entry);
            }
        }

        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = queue.remove().getKey();
        }

        return res;
    }

    /**
     2、小根堆  + map
     */
    public int[] topKFrequent_2(int[] nums, int k, Map<Integer,Integer> map) {

        int[] heap = new int[k];
        int size = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){

            // 1、堆还没满，直接入队
            if(size <k){
                heap[k-1-size] = entry.getKey();
                heapify(heap,k-1-size,k-1,map);   // 记得调整堆
                size ++;
                continue;
            }
            // 2、堆满了，则与堆root元素比较，决定是否要【替换】
            if(map.get(heap[0]) < entry.getValue()){
                heap[0] = entry.getKey();
                heapify(heap,0,k-1,map);
            }
        }

        return heap;
    }

    public void heapify(int[] heap,int root,int end,Map<Integer,Integer> map){
        // 调整到最后一个非叶子结点
        if(root > (end-1)/2){
            return;
        }

        int left = root *2 + 1;
        int right = root * 2 + 2;
        int smallest = root;

        if(left <= end && map.get(heap[left]) < map.get(heap[smallest])){
            smallest = left;
        }

        if(right <= end && map.get(heap[right]) < map.get(heap[smallest])){
            smallest = right;
        }

        // 最小值变了，需要交换，然后调整子节点
        if(smallest != root){
            int temp = heap[smallest];
            heap[smallest] = heap[root];
            heap[root] = temp;
            heapify(heap,smallest,end,map);
        }
    }

    /**
     3、快排  + map
     */
    public int[] topKFrequent_3(int[] nums, int k, Map<Integer,Integer> map) {
        List<Integer> list = new ArrayList<>(map.keySet());
        quickSort(list,0,list.size()-1,k,map);

        int [] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = list.get(list.size()-1-i);
        }

        return res;
    }

    public void quickSort(List<Integer> list,int left,int right,int k,Map<Integer,Integer> map){
        if(left >= right){
            return;
        }

        // 分两段
        int mid = partition(list,left,right,map);

        // 右边的够了，直接返回
        if((list.size() - mid) == k){
            return;
        }else if((list.size() - mid) > k){
            // 右边多了，还在右边找
            quickSort(list,mid+1,right,k,map);
        }else{
            // 右边不够，在左边找
            quickSort(list,left,mid-1,k,map);
        }
    }

    public int partition(List<Integer> list,int left,int right,Map<Integer,Integer> map){
        // 基准值放left
        swap(list,left,(left + right)/2);
        int mid = map.get(list.get(left));

        int leftIndex = left;
        int rightIndex = right;
        while(leftIndex < rightIndex){
            while(leftIndex < rightIndex && map.get(list.get(rightIndex)) >= mid){
                rightIndex --;
            }

            while(leftIndex < rightIndex && map.get(list.get(leftIndex)) <= mid){
                leftIndex ++;
            }

            swap(list,leftIndex,rightIndex);
        }

        //基准值放中间
        swap(list,left,leftIndex);
        return leftIndex;
    }
    public void swap(List<Integer> list,int num1,int num2){
        int temp = list.get(num1);
        list.set(num1,list.get(num2));
        list.set(num2,temp);
    }

    public static void main(String[] args) {
        Leetcode_347_topKFrequent demo = new Leetcode_347_topKFrequent();
        int [] res = demo.topKFrequent(new int[]{-1,-1},1);

        System.out.println(Arrays.toString(res));
    }
}
