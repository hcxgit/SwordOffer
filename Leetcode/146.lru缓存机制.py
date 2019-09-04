#
# @lc app=leetcode.cn id=146 lang=python3
#
# [146] LRU缓存机制
#
from collections import OrderedDict

class ListNode:
    def __init__(self,key,value):
        self.key = key
        self.value = value
        self.pre = None
        self.next = None

class LRUCache:

    # """
    # 方法一：有序字典
    # """
    # def __init__(self, capacity: int):
    #     self.capacity = capacity
    #     self.lru = OrderedDict()

    # def get(self, key: int) -> int:
    #     if key not in self.lru:
    #         return -1
    #     # 说明在缓存中,重新移动字典的尾部
    #     self.lru.move_to_end(key)
    #     return self.lru[key] 

    # def put(self, key: int, value: int) -> None:
    #     # 如果存在,移到尾部,重新赋值
    #     if key in self.lru:
    #         self.lru.move_to_end(key)
    #     self.lru[key] = value
    #     # 容量不够，弹出头部
    #     if len(self.lru) > self.capacity:
    #         self.lru.popitem(last=False)
    
    """
    方法二：哈希+双向链表  哈希表存链表中的节点
    """
    def __init__(self,capacity):
        self.capacity = capacity
        self.haspmap = {}
        # 新建两个头尾节点
        self.head = ListNode(0,0)
        self.tail = ListNode(0,0)

        # 初始化链表为 头<=>尾
        self.head.next =  self.tail
        self.tail.pre = self.head
    def move_node_to_tail(self,key):
        node = self.haspmap[key]
        node.pre.next = node.next
        node.next.pre = node.pre

        node.next = self.tail
        node.pre = self.tail.pre
        self.tail.pre.next = node
        self.tail.pre = node
    def get(self, key: int) -> int:
         # 如果已经在链表中了久把它移到末尾
        if key in self.haspmap:
            self.move_node_to_tail(key)
            return self.haspmap[key].value
        else:
            return -1
    def put(self, key: int, value: int) -> None:
        if key in self.haspmap:
            self.haspmap[key].value = value
            self.move_node_to_tail(key)
        else:
            # 满了，删除表头的节点（最久没访问）
            if len(self.haspmap) == self.capacity:
                self.haspmap.pop(self.head.next.key) # hasp中删除该节点
                self.head.next = self.head.next.next
                self.head.next.pre = self.head
            node = ListNode(key,value)
            self.haspmap[key] = node

            node.next = self.tail
            node.pre = self.tail.pre
            self.tail.pre.next = node
            self.tail.pre = node

