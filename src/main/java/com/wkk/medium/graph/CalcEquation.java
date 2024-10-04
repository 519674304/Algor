package com.wkk.medium.graph;

import com.wkk.util.CommonUtil;
import com.wkk.util.Node;
import org.junit.Test;

import java.util.*;

public class CalcEquation {
    @Test
    public void test12(){
        String[][] equationArray = {{"x1","x2"},{"x2","x3"},{"x1","x4"},{"x2","x5"}};
        double[] values = {3.0, 0.5, 3.4, 5.6};
        String[][] queriesArray = {{"x2","x4"},{"x1","x5"},{"x1","x3"}};
        double[] doubles = calcEquation(CommonUtil.arrayToList(equationArray), values, CommonUtil.arrayToList(queriesArray));
        System.out.println(doubles);
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap<>(equations.size() * 2);
        Union union = new Union(equations.size() * 2);
        int l = 0;
        for (int i = 0; i < equations.size(); i++) {
            String xKey = equations.get(i).get(0);
            String yKey = equations.get(i).get(1);
            Integer x = map.get(xKey);
            Integer y = map.get(yKey);
            if (x == null) {
                x = l++;
                map.put(xKey, x);
            }
            if (y == null) {
                y = l++;
                map.put(yKey, y);
            }
            union.put(x, y, values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String xKey = queries.get(i).get(0);
            String yKey = queries.get(i).get(1);
            Integer x = map.get(xKey);
            Integer y = map.get(yKey);
            if (x == null || y == null) {
                res[i] = -1;
                continue;
            }
            res[i] = union.relative(x, y);
        }
        return res;
    }

    class Union{
        int[] parentArray;
        double[] weightArray;

        public Union(int size) {
            parentArray = new int[size];
            weightArray = new double[size];
            for (int i = 0; i < size; i++) {
                parentArray[i] = i;
                weightArray[i] = 1.0;
            }
        }

        public void put(int x, int y, double value) {
            int rootY = findParent(y);
            int rootX = findParent(x);
            if (rootY == rootX) {
                return;
            }
            parentArray[rootX] = rootY;
            weightArray[rootX] *= (weightArray[y] * value / weightArray[x]);
        }

        private int findParent(int c) {
            int loopC = c;
            List<Integer> changeList = new ArrayList<>();
            while (loopC != parentArray[loopC]) {
                changeList.add(loopC);
                loopC = parentArray[loopC];
            }
            for (int i = changeList.size() - 1; i >= 0; i--) {
                Integer currI = changeList.get(i);
                weightArray[currI] *= weightArray[parentArray[currI]];
                parentArray[currI] = loopC;
            }
            return loopC;
        }

        public double relative(int x, int y) {
            int rootX = findParent(x);
            int rootY = findParent(y);
            if (rootX != rootY) {
                return -1;
            }
            return weightArray[x] / weightArray[y];
        }
    }

}




class GraphSolution{
    public static double[] graphSolve(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> strings = equations.get(i);
            Node nodeA = nodeMap.computeIfAbsent(strings.get(0), k -> new Node());
            Node nodeB = nodeMap.computeIfAbsent(strings.get(1), k -> new Node());
            nodeA.edgeMap.put(nodeB, values[i]);
            nodeB.edgeMap.put(nodeA, 1 / values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            Node start = nodeMap.get(query.get(0));
            Node end = nodeMap.get(query.get(1));
            if (end == null || start == null) {
                result[i] = -1;
                continue;
            }
            result[i] = findEnd(start, end, new HashMap<>());
        }
        return result;
    }

    private static double findEnd(Node node, Node findNode, Map<Node, Boolean> findMap) {
        if (findMap.getOrDefault(node, false)) {
            return -1;
        }
        findMap.put(node, true);
        if (Objects.equals(node, findNode)) {
            return 1;
        }
        for (Map.Entry<Node, Double> nodeDoubleEntry : node.edgeMap.entrySet()) {
            Node key = nodeDoubleEntry.getKey();
            double end = findEnd(key, findNode, findMap);
            if (end != -1) {
                return end * nodeDoubleEntry.getValue();
            }
        }
        return -1;
    }
}

class CalcSolution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
