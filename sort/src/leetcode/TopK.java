package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/5
 * LeetCode 215: 数组中第k个最大元素
 */
public class TopK {
    public int findKthLargest(int[] nums, int k) {
//         heapSort(nums,k);
//         return nums[nums.length-k];
        int res = quickSort(nums, 0, nums.length - 1, nums.length - k);
        return nums[res];
    }
    /**
     * 快排选择
     */
    public int quickSort(int[] nums,int left,int right,int k){
        if(left > right){
            return -1;
        }

        int mid = partition(nums,left,right);

        //判断确定的枢纽值的位置在k左还是右
        if(mid == k){
            return mid;
        }else if(mid < k){
            return quickSort(nums,mid+1,right,k);
        }else{
            return quickSort(nums,left,mid-1,k);
        }
    }

    public int partition(int[] nums, int left, int right){
        int leftIndex  = left;
        int rightIndex = right;
        int mid = nums[(left+right)/2];
        swap(nums,left,(left+right)/2);

        while(leftIndex < rightIndex){
            while(leftIndex < rightIndex && nums[rightIndex] >= mid){
                --rightIndex;
            }
            while(leftIndex < rightIndex && nums[leftIndex] <= mid){
                ++leftIndex;
            }

            swap(nums,leftIndex,rightIndex);
        }
        swap(nums,left,leftIndex);
        return leftIndex;
    }

    /**
     * 堆排序
     */
    public void heapSort(int[] nums,int k){
        //建堆
        for(int i=nums.length/2-1; i>=0;i--){
            heapAdjust(nums,i,nums.length-1);
        }

        //调换排序
        for(int end = nums.length-1;end >= nums.length-k;end--){
            swap(nums,0,end);
            heapAdjust(nums,0,end-1);
        }
    }

    public void heapAdjust(int[] nums, int root,int end){
        if(root >= end){
            return;
        }

        int largest = root;
        int left = root*2+1;
        int right = root*2+2;

        if(left <=end && nums[left] > nums[largest]){
            largest = left;
        }
        if(right <= end && nums[right] > nums[largest]){
            largest = right;
        }
        if(largest != root){
            swap(nums,largest,root);
            heapAdjust(nums, largest,end);
        }
    }
    public void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    public static void main(String[] args) {
        TopK case1 = new TopK();
        int kthLargest = case1.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(kthLargest);
    }
}
