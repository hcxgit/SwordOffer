package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/8/5
 *
 * Leetcode 148: 排序链表
 *  1、list中排序
 *      1、遍历链表放list中
 *      2、list排序
 *      3、链表按照list，改val
 *
 *
 *  2、归并（递归）
 *      1、找到中间节点（快慢指针）,将链表断开
 *      2、分开递归排序
 *      3、合并
 *
 *  3、归并（非递归）
 *      1、用 subLength 表示每次需要排序的【子链表的长度】，初始时 `subLength=1`。
 *      2、每次将【链表拆分】成多个长度为 `subLength` 的【子链表】（最后一个子链表的长度可以小于subLength），
 *          按照【每两个子链表一组进行合并】，合并后即可得到若干个长度为subLength×2的【有序子链表】（最后一个子链表的长度可以小于 subLength×2）。
 * -    3、将subLength*2，【重复第2步】，对更长的有序子链表进行【合并】操作，直到【有序子链表subLength>= length】，
 *          整个链表【排序完毕】。
 *
 *  4、快排（递归）
 *      1、确定【基准值】p（比如，第一个节点）
 *      2、遍历
 *          1、< p，放在【临时链表】，并【原链表中删除】
 *          2、>=p, 继续遍历
 *      3、遍历完，【临时链表】放【基准值前面】
 *      4、递归排序【基准值的】【左右链表】
 *
 *      注意：善于利用【伪头结点】
 */
public class Leetcode_148_sortList {
    // 1、list中排序
    public ListNode sortList1(ListNode head) {
        if(head ==null || head.next == null){
            return head;
        }
        List<Integer> list = new ArrayList<>();
        ListNode oldHead = head;

        // 1、遍历链表放list中
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        // 2、list排序
        list.sort(null);
        ListNode pre = oldHead;

        // 3、链表按照list，改val
        for (Integer l : list) {
            oldHead.val = l;
            oldHead = oldHead.next;
        }
        return pre;
    }

    // 2、归并（递归）
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        // 1、找到中间节点（快慢指针）
        ListNode slow = head;
        ListNode fast =head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //2、断开分两段
        ListNode head2 = slow.next;
        slow.next = null;

        //3、分
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(head2);

        //4、合并
        return merge(h1,h2);
    }

    // 合并
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode preHead = new ListNode();
        ListNode node = preHead;

        while(head1 != null && head2 != null){
            if(head1.val <= head2.val){
                node.next = head1;
                head1 = head1.next;
            }else{
                node.next = head2;
                head2 = head2.next;
            }
            node = node.next;
        }

        if(head1 != null){
            node.next = head1;
        }

        if(head2 != null){
            node.next = head2;
        }

        return preHead.next;
    }

    // 3、归并（非递归）
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        // 1、求链表长度
        int len= 0;
        ListNode node = head;
        while(node != null){
            len ++;
            node = node.next;
        }

        //2、非递归求解,每一轮合并后，subLength*2
        ListNode preHead = new ListNode(0,head);
        for (int subLength = 1; subLength < len ; subLength *= 2) {
            // 每一轮从head开始合并，pre用来【连接下一对合并的子链表】
            ListNode pre = preHead, curr = pre.next;
            //每一轮直到最后一个被合并结束
            while(curr != null ){
                //1、找一对要合并的子链表 L1、L2
                //找 L1
                ListNode l1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                //找L2
                ListNode l2 = curr.next;
                curr.next = null;  // 断开链表
                curr = l2;
                // 这里多了个curr != null，上面curr没判断null
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                //2、标记下一轮开始节点，然后断开链表。  注意null判断
                if(curr != null){
                    ListNode next = curr.next;
                    curr.next = null;
                    curr = next;
                }

                //3、合并L1、L2
                pre.next = merge(l1, l2);
                //4、pre走到合并链表的尾节点，用来【连接下一轮对合并的子链表】
                while(pre.next != null){
                    pre = pre.next;
                }
            }
        }
        return preHead.next;
    }
}
