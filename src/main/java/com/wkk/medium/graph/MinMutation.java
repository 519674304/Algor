package com.wkk.medium.graph;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class MinMutation {

    @Test
    public void test11(){
        System.out.println(minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        List<String> geneList = Arrays.asList("A", "C", "G", "T");
        Map<String, Boolean> flagMap = new HashMap<>();
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<Pair<String, Integer>> geneQue = new LinkedList<>();
        geneQue.add(new Pair<>(startGene, 0));
        flagMap.put(startGene, true);
        while (!geneQue.isEmpty()) {
            Pair<String, Integer> currGene = geneQue.poll();
            String str = currGene.getKey();
            for (int i = 0; i < str.length(); i++) {
                String currIndexGene = str.substring(i, i + 1);
                for (String gene : geneList) {
                    if (!currIndexGene.equals(gene)) {
                        String mutationStr = str.substring(0, i) + gene + str.substring(i + 1);
                        if (bankSet.contains(mutationStr)) {
                            if (mutationStr.equals(endGene)) {
                                return currGene.getValue() + 1;
                            }
                            if (!flagMap.getOrDefault(mutationStr, false)) {
                                flagMap.put(mutationStr, true);
                                geneQue.add(new Pair<>(mutationStr, currGene.getValue() + 1));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test28(){
        System.out.println("ssss".substring(4));
    }
}

class MinMutationSolution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        Set<String> cnt = new HashSet<>(Arrays.asList(bank));
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
