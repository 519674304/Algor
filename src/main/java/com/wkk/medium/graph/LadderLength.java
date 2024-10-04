package com.wkk.medium.graph;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class LadderLength {
    @Test
    public void test9(){
        int i = ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(i);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, List<String>> map = new HashMap<>();
        Set<String> findFlagSet = new HashSet<>();
        for (String word : wordList) {
            for (String word2 : wordList) {
                int diffCount = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != word2.charAt(i)) {
                        diffCount++;
                    }
                }
                if (diffCount == 1) {
                    map.computeIfAbsent(word, k -> new ArrayList<>()).add(word2);
                }
            }
        }
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> currWord = queue.poll();
            if (findFlagSet.contains(currWord.getKey())) {
                continue;
            }
            List<String> edgeValList = map.getOrDefault(currWord.getKey(), new ArrayList<>());
            if (edgeValList.contains(endWord)) {
                return currWord.getValue() + 1;
            }
            findFlagSet.add(currWord.getKey());
            for (String val : edgeValList) {
                queue.add(new Pair<>(val, currWord.getValue() + 1));
            }
        }
        return 0;
    }
}

class Solution {
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
}
