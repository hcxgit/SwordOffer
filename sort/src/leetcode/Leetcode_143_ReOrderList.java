package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/5/15
 * @description Leetcode 143：链表重排序
 *  一、list + 双指针
 *  二、寻找链表中点 + 链表逆序 + 合并链表
 */
public class Leetcode_143_ReOrderList {
    public void reorderList(ListNode head) {
        // 1、寻找中间节点，  快慢指针
        // 2、后半段链表反转，分成两段链表（记得中间节点断开）
        // 3、合并链表

        if(head == null){
            return;
        }

        // 1、寻找中间节点(偏左边),分成两个链表
        ListNode mid = midNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null; // 断开

        // 2、反转链表
        l2 = reverseList(l2);

        // 3、合并链表
        mergeList(l1,l2);
    }

    /**
     * 寻找中间节点
     */
    public ListNode midNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode nextNode = head.next;
            head.next = pre;
            pre = head;
            head = nextNode;
        }
        return pre;
    }

    /**
     * 合并链表
     */
    public void mergeList(ListNode l1,ListNode l2){
        ListNode l1_next;
        ListNode l2_next;
        while(l1 != null && l2 != null){
            l1_next = l1.next;
            l2_next = l2.next;

            l1.next = l2;
            l1 = l1_next;

            l2.next = l1;
            l2 = l2_next;
        }
    }
}
