package com.wkk.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRUCache {
    private DeNode firstNode = new DeNode(0, -1);
    private DeNode lastNode = firstNode;
    private int capacity;
    private int size;
    private Map<Integer, DeNode> keyMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (keyMap.containsKey(key)) {
            moveLast(keyMap.get(key));
        }
        return keyMap.getOrDefault(key, new DeNode(0, -1)).val;
    }

    public void put(int key, int value) {
        DeNode currNode = new DeNode(key, value);
        size++;
        if (keyMap.containsKey(key)) {
            size--;
            currNode = keyMap.get(key);
            currNode.val = value;
        }
        keyMap.put(key, currNode);
        moveLast(currNode);
        if (size > capacity) {
            keyMap.remove(firstNode.next.key);
            DeNode next = firstNode.next.next;
            firstNode.next = next;
            next.prev = firstNode;
            size--;
        }
    }

    private void moveLast(DeNode currNode) {
        if (lastNode == currNode) {
            return;
        }
        if (Objects.nonNull(currNode.prev)) {
            currNode.prev.next = currNode.next;
        }
        if (Objects.nonNull(currNode.next)) {
            currNode.next.prev = currNode.prev;
        }
        lastNode.next = currNode;
        currNode.prev = lastNode;
        lastNode = currNode;
    }


    class DeNode{
        private int val;
        private int key;
        private DeNode prev;
        private DeNode next;

        public DeNode(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }
}

class LRUCacheOffice {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCacheOffice(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
