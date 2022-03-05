package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/3
 * leetcode25:k个一组翻转链表
 * 遍历够k个数：1、找到一组，翻转后面的 2、断开分成两部分 3、翻转当前，反转后尾部指向后面的头部
 */
public class reverseKGroup {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return head;
        }
        int num = 1;
        ListNode curr = head;
        while(curr != null){
            if(num%k == 0){
                //翻转后面的链表
                ListNode nextHead = reverseKGroup(curr.next,k);
                //先断开，分成两块
                curr.next = null;
                //翻转当前节点
                ListNode newHead =  reverse(head);
                //尾部指向后面的头部
                head.next = nextHead;
                return newHead;
            }else{
                curr = curr.next;
                ++num;
            }
        }
        return head;
    }
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode nextHead =  reverse(head.next);
        head.next.next = head;
        head.next = null;

        return nextHead;
    }

    public ListNode createNode(){

        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        return node1;
    }

    public static void main(String[] args) {
        reverseKGroup r = new reverseKGroup();
        ListNode head = r.createNode();
//        while (head != null){
//            System.out.println(head.val);
//            head = head.next;
//        }
        ListNode listNode = r.reverseKGroup(head, 2);
        System.out.println(listNode.val);

    }
}
