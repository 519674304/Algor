package com.wkk.medium.graph;

import com.wkk.util.Node;

import java.util.*;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Node, Node> nodeMap = new HashMap<>();
        Stack<Node> nodeStack = new Stack<>();
        if (node != null) {
            nodeStack.push(node);
        }

        while (!nodeStack.isEmpty()) {
            Node pop = nodeStack.pop();
            Node mirrorNode = nodeMap.computeIfAbsent(pop, k -> new Node(pop.val));
            for (Node neighbor : pop.neighbors) {
                if (!nodeMap.containsKey(neighbor)) {
                    nodeStack.push(neighbor);
                }
                Node mirrorNeighbor = nodeMap.computeIfAbsent(neighbor, k -> new Node(neighbor.val));
                mirrorNode.neighbors.add(mirrorNeighbor);
            }
        }
        return nodeMap.get(node);
    }
}

class CloneGraphDFSSolution {
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val);
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}

class CloneBFSSolution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap<>();

        // 将题目给定的节点添加到队列
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node n = queue.remove();
            // 遍历该节点的邻居
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}
