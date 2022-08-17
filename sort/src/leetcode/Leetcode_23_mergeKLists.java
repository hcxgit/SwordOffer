package leetcode;

import java.util.PriorityQueue;

/**
 * @author 三笠阿克曼
 * @date 2022/8/16
 *
 * Leetcode 23: 合并K个升序链表
 *
 *  1、顺序合并
 *      【两两合并】，问题就变成【合并两个升序链表】
 *
 *  2、归并
 *      1. 将 k个链表【配对】，并将同一对中的链表【合并】
 *      2. 第一轮合并以后， k个链表被合并成了k/2个链表，然后是k/4个链表....
 *      3. 重复这一过程，直到得到了最终的有序链表
 *
 *  3、使用【优先队列】
 *      - 维护一个优先级队列，每次【选取最小元素】，将【next再入队】
 *
 *  4、【小根堆】
 *      - 维护一个【小根堆】，每次【选取root节点】。
 *      1、【初始化堆】，从最后一个【非叶子节点】开始调整
 *      2、建完堆后，【root】节点，也就是【list[0]】就是【最小值】,【root链表遍历完】则【和end交换，调整堆】
 *          2.1 最小值加入
 *          2.2 更新list[0]：
 *              1）如果当前【链表没遍历完】，则更新为【next】
 *              2）如果当前【链表用完了】，则将【最后一个非null链表】移过来,【end --】
 *           2.3 重新调整堆
 *
 */
public class Leetcode_23_mergeKLists {
    // 1、顺序合并
    public ListNode mergeKLists1(ListNode[] lists) {

        ListNode res = null;
        for (ListNode node: lists) {
            res = mergeTwoListNode(res,node);
        }

        return res;
    }

    public ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode();
        ListNode tail = preHead;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null ? l2:l1;

        return preHead.next;
    }

    //2、归并
    public ListNode mergeKLists2(ListNode[] lists) {

        return mergeLists(lists,0,lists.length-1);
    }

    public ListNode mergeLists(ListNode[] lists,int left,int right) {
        if(left == right){
            return lists[left];
        }
        if(left > right){
            return null;
        }

        //分
        int mid = (left + right)/2;
        ListNode l = mergeLists(lists, left, mid);
        ListNode r = mergeLists(lists, mid + 1, right);

        // 合
        return mergeTwoListNode(l,r);
    }

    //3、优先级队列
    public ListNode mergeKLists3(ListNode[] lists) {

        ListNode preHead = new ListNode();
        ListNode tail = preHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((l1,l2)->l1.val-l2.val);

        for (ListNode l : lists) {
            if(l != null){
                queue.add(l);
            }
        }

        while(!queue.isEmpty()) {
            //弹出最小的
            ListNode node = queue.remove();
            tail.next = node;
            tail = tail.next;

            //next加入队列
            if(node.next != null){
                queue.add(node.next);
            }
        }

        return preHead.next;
    }

    // 4、小根堆
    public ListNode mergeKLists(ListNode[] lists) {

        //1、初始化【堆】，从最后一个【非叶子节点】开始调整
        for (int root = lists.length/2 - 1; root >= 0; root --) {
            heapify(lists,root,lists.length - 1);
        }

        ListNode preHead = new ListNode();
        ListNode tail = preHead;

        //2、建完堆后，root节点，也就是list[0]就是最小值,【root遍历完】则【和end交换，调整堆】
        int end = lists.length-1;
        while(end >=0 && lists[0] != null){
            //2.1 最小值加入
            ListNode node = lists[0];
            tail.next = node;
            tail = tail.next;

            //2.2 更新list[0]
            if(node.next != null){
                //当前链表没用完，则更新为next
                lists[0] = node.next;
            }else{
                //如果当前链表用完了，则将【最后一个非null链表】移过来,更新end
                lists[0] = lists[end];
                end--;
            }

            // 2.3 重新调整堆
            heapify(lists,0,end);
        }

        return preHead.next;
    }

    //调整为堆
    public void heapify (ListNode[] lists, int root, int end) {
        if(root >= end){
            return;
        }

        //1、初始化最小值的节点下标是根节点下标
        int smallest = root;
        int left = root * 2 + 1;
        int right = root * 2 + 2;

        //2、找到root、左右子树的【最小值】
        // 注意【越界】以及【null】,可能存在null节点
        if(left <= end && (lists[smallest] == null || lists[left] != null && lists[left].val < lists[smallest].val)){
            smallest = left;
        }

        if(right <= end && (lists[smallest] == null || lists[right] != null && lists[right].val < lists[smallest].val)){
            smallest = right;
        }

        //3、最小值变了，要【交换】，【调整】
        if(smallest != root){
            //交换
            ListNode temp = lists[root];
            lists[root] = lists[smallest];
            lists[smallest] = temp;

            //调整
            heapify(lists,smallest,end);
        }
    }

}
