package leetcode;

import java.util.HashSet;

/**
 * @author 三笠阿克曼
 * @date 2022/8/22
 * Leetcode 142: 环形链表2
 *
 *  1、hash表
 *      hashSet
 *
 *  2、快慢指针
 *      - 链环前的长度：a, 链环长度：b
 *      - fast每轮走 2 步，slow每轮走 1 步；
 *          1）fast走到【链尾】，说明【无环】；
 *          2）当fast = slow时，【有环】，【第一次相遇】，此时, fast所走长度：f，slow所走长度：s
 *              1. f = 2s
 *              2. f = s + nb  (fast比slow多走n个环)
 *              3. 结合1、2 ——> s = nb, f = 2nb
 *              4. 走到环口需要的步数：a + nb
 *              5. 结合3、4 ——> 【第一次相遇后】，slow只需要【再走a步】，就能【到达环口】
 *              6. 所以，【第一次相遇后】，【slow和head同时走】，【相遇时，就是环口】
*      综上：fast以速度2，slow以速度1，同时开始走，【第一次相遇后】，
 *          将fast = head，然后fast、slow【相同速度】同时走，【第二次相遇时，就是环口】
 */
public class Leetcode_142_detectCycle {
    // 1、hash表
    public ListNode detectCycle1(ListNode head) {

        HashSet<ListNode> hashSet = new HashSet<>();
        if(head == null || head.next == null){
            return null;
        }

        while(head != null){
            if(hashSet.contains(head)){
                return head;
            }else{
                hashSet.add(head);
            }
            head = head.next;
        }

        return null;
    }

    //2、快慢指针
    public ListNode detectCycle(ListNode head) {

        if(head == null || head.next == null){
            return null;
        }

        ListNode slow = head, fast = head;
        while(true){

            if(fast.next == null || fast.next.next == null) {
                return null;
            }else {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 第一次相遇
            if(fast == slow){
                break;
            }
        }

        fast = head;
        // 第二次相遇
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}
