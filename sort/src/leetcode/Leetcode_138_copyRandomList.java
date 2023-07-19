package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2023/7/19
 * @description Leetcode 138: 复制带随机指针的链表
 *
 *     1、递归 + HashMap
 *     2、迭代（2次遍历） + HashMap
 *     3、迭代（复制节点） + 拆分链表
 */
public class Leetcode_138_copyRandomList {

    // <node: newNode>
    Map<Node,Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        return copyRandomList_1(head);
    }

    /**
     1、递归 + HashMap
     */
    public Node copyRandomList_1(Node head) {
        if(head == null){
            return null;
        }

        if(!map.containsKey(head)){
            Node curr = new Node(head.val);
            map.put(head,curr);

            curr.next = copyRandomList(head.next);
            curr.random = copyRandomList(head.random);

            return curr;
        }else{
            return map.get(head);
        }
    }


    /**
     2、迭代（2次遍历） + HashMap
     - 第一次遍历，创建节点，放map
     - 第二次遍历，改next、random
     */
    public Node copyRandomList_2(Node head) {
        Node curr = head;

        // 第一次遍历
        while(curr != null){
            map.put(curr,new Node(curr.val));
            curr = curr.next;
        }

        // 第二次遍历
        curr = head;
        while(curr != null){
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    /**
     3、迭代（复制节点） + 拆分链表
     - 1、复制节点：1 -> 1' -> 2 -> 2' -> 3 -> 3'
     - 2、改random
     - 3、拆分链表
     */
    public Node copyRandomList_3(Node head) {
        Node curr = head;

        // 1、复制节点，放在原节点的后面
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;

            curr = curr.next.next;
        }

        // 2、改random
        curr = head;
        while(curr != null){
            curr.next.random = curr.random;
            curr = curr.next.next;
        }

        //3、拆分链表
        curr = head.next;
        Node newHead = curr;
        while(curr != null && curr.next != null){
            curr.next = curr.next.next;
            curr = curr.next;
        }

        return newHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
