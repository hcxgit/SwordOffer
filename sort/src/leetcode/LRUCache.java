package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三笠阿克曼
 * @date 2022/2/23
 */
//双向链表+hash表
public class LRUCache {
    class DoubleNode {
        int key;
        int value;
        DoubleNode pre;
        DoubleNode next;

        DoubleNode() {
        }

        ;

        DoubleNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }

    int capacity, length;
    DoubleNode head, tail;
    Map<Integer, DoubleNode> hashCache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.head = new DoubleNode();
        this.tail = new DoubleNode();
        this.hashCache = new HashMap();

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (hashCache.containsKey(key)) {
            DoubleNode node = hashCache.get(key);
            //删除节点
            deleteNode(node);
            //节点放到链表尾部
            moveTotail(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (hashCache.containsKey(key)) {
            //存在则修改value并调整顺序
            DoubleNode node = hashCache.get(key);
            node.value = value;
            deleteNode(node);
            moveTotail(node);
        } else {
            //不存在且满了，链表删除首个节点
            if (length == capacity) {
                DoubleNode oldKey = head.next;
                deleteNode(oldKey);
                hashCache.remove(oldKey.key);
                length--;
            }
            //加入新节点，放到链表尾
            DoubleNode node = new DoubleNode(key, value);
            moveTotail(node);
            hashCache.put(key, node);
            length++;
        }
    }

    /**
     * 节点移动到链表尾部
     */
    public void moveTotail(DoubleNode node) {
        //节点放尾部
        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;
    }

    /**
     * 删除节点
     */
    public void deleteNode(DoubleNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(2, 1);
        obj.put(1, 1);
        obj.put(2, 3);
        obj.put(4, 1);
        int param_1 = obj.get(1);
        int param_2 = obj.get(2);
        System.out.println(param_1);
        System.out.println(param_2);
    }
}
