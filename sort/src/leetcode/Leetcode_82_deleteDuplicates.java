package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/9/1
 *
 * Leetcode 82: 删除排序链表中的重复元素 II
 *
 * 1、一次遍历
 *      - 1、如果 cur.next.val != cur.next.next：
 *              - 则更新cur = cur.next
 *          否则：
 *              - 记录该【相同值】：num = cur.next.val
 *              - 然后循环：cur.next = cur.next.next，直到【curr.next.val != num】
 *      - 2、注意：
 *            1) 利用【虚拟头结点】
 *            2) 用【变量】记录【相同值的节点】，避免逻辑混乱
 */
public class Leetcode_82_deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode preHead = new ListNode(0,head);
        ListNode curr = preHead;

        while(curr.next !=null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){
                //删除节点
                int num = curr.next.val;
                while(curr.next != null && curr.next.val == num){
                    curr.next = curr.next.next;
                }
            }else{
                curr = curr.next;
            }
        }
        return preHead.next;
    }
}
