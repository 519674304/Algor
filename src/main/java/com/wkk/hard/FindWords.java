package com.wkk.hard;

import com.wkk.medium.Trie;
import com.wkk.util.CharNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindWords {
    public static void main(String[] args) {
        //char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        //char[][] board = {{'a', 'b'}};
        FindWords findWords = new FindWords();
        String[] array = Arrays.asList("oath","pea","eat","rain","hklf", "hf").toArray(new String[0]);
        List<String> words = findWords.findWords(board, array);
        System.out.println(words);
    }
    private List<String> wordFindList = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (words.length == 0) {
            return new ArrayList<>();
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                findWord(board, visited, i, j, "", trie.node);
            }
        }
        return wordFindList;
    }

    public void findWord(char[][] board, boolean[][] visited, int i, int j, String sb, CharNode node) {
        if (node == null) {
            return;
        }
        if (i < 0) {
            return;
        }
        if (i >= board.length) {
            return;
        }
        if (j < 0) {
            return;
        }
        if (j >= board[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        int index = board[i][j] - 'a';
        CharNode next = node.charNodes[index];
        if (next == null) {
            return;
        }
        sb = sb + board[i][j];
        if (next.endCount > 0) {
            if (!wordFindList.contains(sb)) {
                wordFindList.add(sb);
            }
        }
        visited[i][j] = true;
        findWord(board, visited,i + 1, j, sb, next);
        findWord(board, visited,  i - 1, j, sb, next);
        findWord(board, visited,  i, j + 1, sb, next);
        findWord(board, visited,  i, j - 1, sb, next);
        visited[i][j] = false;
    }


}
