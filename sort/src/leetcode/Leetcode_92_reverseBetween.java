package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/8
 * Leetcode 92 ：反转链表II，反转从位置left到位置right的链表
 */
public class Leetcode_92_reverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right){
            return head;
        }

        return reverseBetween02(head,left,right);
        //return reverseBetween01(head,left,right);
    }
    /**
     * 双指针+头插法+虚拟头节点
     */
    public ListNode reverseBetween02(ListNode head, int left, int right){

        //虚拟头节点
        ListNode preHead = new ListNode(-1,head);

        //初始化指针
        ListNode preLeftNode = preHead;
        ListNode leftNode = preHead.next;

        //找到双指针位置
        for(int i=0; i< left-1;i++){
            preLeftNode = preLeftNode.next;
            leftNode = leftNode.next;
        }

        //头插法
        for(int j=left;j<right;j++){
            //left节点的下个节点插入到preLeft的后面
            ListNode nextLeftNode = leftNode.next;
            leftNode.next = nextLeftNode.next;

            nextLeftNode.next = preLeftNode.next;
            preLeftNode.next = nextLeftNode;
        }
        return preHead.next;
    }

    /**
     *正常找子链表、断开、反转、连接
     */
    public ListNode reverseBetween01(ListNode head, int left, int right){

        ListNode leftTail = head; //左边不用反转的子链表尾
        ListNode midTail = head; //中间要反转的子链表尾节点
        ListNode rightHead;     //右边要反转的子链表的头节点
        for(int i=1;i<right;i++){
            if(i<left-1){
                leftTail = leftTail.next;
            }
            midTail = midTail.next;
        }
        //断开
        rightHead = midTail.next;
        midTail.next = null;

        //left=1说明从头节点开始反转
        if(left == 1){
            ListNode midHead = reverse(leftTail);
            leftTail.next = rightHead;
            return midHead;
        }else{
            ListNode midHead = reverse(leftTail.next);
            leftTail.next.next = rightHead;
            leftTail.next = midHead;
            return head;
        }
    }
    public ListNode reverse(ListNode head){
        if(head.next == null){
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
