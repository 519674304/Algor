package com.wkk.medium.graph;

import org.junit.Test;

import java.util.*;

public class FindOrder {

    @Test
    public void test11(){
        int[] order = findOrder(2, new int[][]{});
        System.out.println(Arrays.toString(order));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = personalSolve(numCourses, prerequisites);
        return order;
    }

    private int[] personalSolve(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edgeList = new ArrayList<>();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            edgeList.get(prerequisite[1]).add(prerequisite[0]);
        }
        List<Integer> findList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (findRight(edgeList, i, visited, findList)) {
                    int[] res = new int[numCourses];
                    if (findList.size() == numCourses) {
                        for (int j = 0; j < findList.size(); j++) {
                            res[numCourses - j - 1] = findList.get(j);
                        }
                        return res;
                    }
                }
            }
        }
        return new int[]{};
    }

    public boolean findRight(List<List<Integer>> edgeList, int start, int[] visited, List<Integer> findList) {
        visited[start] = 1;
        for (Integer loop : edgeList.get(start)) {
            if (visited[loop] == 1) {
                return false;
            }
            if (visited[loop] == 0) {
                findRight(edgeList, loop, visited, findList);
            }
        }
        findList.add(start);
        visited[start] = 2;
        return true;
    }
}

class FindBFSOrderSolution {
    // 存储有向图
    List<List<Integer>> edges;
    // 存储每个节点的入度
    int[] indeg;
    // 存储答案
    int[] result;
    // 答案下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        index = 0;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for (int v: edges.get(u)) {
                --indeg[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }
}

class FindOrderDFSSolution {
    // 存储有向图
    List<List<Integer>> edges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result;
    // 判断有向图中是否有环
    boolean valid = true;
    // 栈下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v : edges.get(u)) {
            // 如果「未搜索」那么搜索相邻节点
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 如果「搜索中」说明找到了环
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        // 将节点入栈
        result[index--] = u;
    }
}
