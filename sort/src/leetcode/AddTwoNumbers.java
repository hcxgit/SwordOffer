package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/23
 *
 * Leetcode 2: 两数之和
 *
 *  思路一： 遍历
 *      注意加上上一次的进位值flag
 *      num = (n1+n2+flag)%10
 *      flag = (n1+n2+flag)/10
 *
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode tail = head;
        int n1 = 0;  //余数
        int n2 = 0;  //进位
        while(l1 != null && l2 != null){
            int num = l1.val + l2.val + n2; // 注意上一次的进位
            n1 = num%10;
            ListNode newNode = new ListNode(n1);
            tail.next = newNode;
            tail = newNode;
            l1 = l1.next;
            l2 = l2.next;
            //进位
            n2 = num/10;

        }

        while(l1 != null){
            int num = l1.val + n2;
            n1 = num%10;
            ListNode newNode = new ListNode(n1);
            tail.next = newNode;
            tail = newNode;
            l1 = l1.next;
            //进位
            n2 = num/10;
        }
        while(l2 != null){
            int num = l2.val + n2;
            n1 = num%10;
            ListNode newNode = new ListNode(n1);
            tail.next = newNode;
            tail = newNode;
            l2 = l2.next;
            //进位
            n2 = num/10;
        }
        //收尾
        if(n2 != 0){
            ListNode newNode = new ListNode(n2);
            tail.next = newNode;
        }

        return head.next;
    }
}
