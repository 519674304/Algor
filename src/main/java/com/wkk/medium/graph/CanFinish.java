package com.wkk.medium.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanFinish {
    @Test
    public void test8(){
        int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2}};
        System.out.println(canFinish(5, prerequisites));
    }

    private Map<Integer, Integer> findMap = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < prerequisites.length; i++){
            map.computeIfAbsent(prerequisites[i][1], k -> new ArrayList<>()).add(prerequisites[i][0]);
        }
        for(Map.Entry<Integer, List<Integer>> e : map.entrySet()){
            if (findMap.getOrDefault(e.getKey(), 0) > 0) {
                continue;
            }
            Integer loopKey = e.getKey();
            Map<Integer, Integer> currFindMap = new HashMap<>();
            if (!findLoop(map, loopKey, currFindMap)) {
                return false;
            }
        }
        return true;
    }

    public boolean findLoop(Map<Integer, List<Integer>> edgeMap, int start, Map<Integer, Integer> currFindMap) {
        currFindMap.put(start, 1);
        findMap.put(start, 1);
        for (Integer loop : edgeMap.getOrDefault(start, new ArrayList<>())) {
            if (currFindMap.getOrDefault(loop, 0) == 1) {
                return false;
            }
            if (currFindMap.getOrDefault(loop, 0) == 0) {
                if (!findLoop(edgeMap, loop, currFindMap)) {
                    return false;
                }
            }
        }
        currFindMap.put(start, 2);
        return true;
    }
}

class CanFinishSolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, edges, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int u, List<List<Integer>> edges, int[] visited) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 1) {
                return false;
            }
            if (visited[v] == 0) {
                boolean dfs = dfs(v, edges, visited);
                if (!dfs) {
                    return false;
                }
            }
        }
        visited[u] = 2;
        return true;
    }
}
