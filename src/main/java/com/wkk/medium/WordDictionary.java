package com.wkk.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WordDictionary {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a");
        WordDictionary wordDictionary = new WordDictionary();
        for (String s : list) {
            wordDictionary.addWord(s);
        }
        System.out.println(wordDictionary.search(".a"));
    }
    
    CharNode node;

    public WordDictionary() {
        node = new CharNode();
    }

    public void addWord(String word) {
        CharNode loop = node;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            CharNode next = loop.charNodes[index];
            if (next == null) {
                next = new CharNode();
                loop.charNodes[index] = next;
            }
            next.passCount++;
            if (i == word.length() - 1) {
                next.endCount++;
            }
            loop = next;
        }
    }

    public boolean search(String word) {
        return searchHelper(word, 0, node);
    }

    public boolean searchHelper(String word, int i, CharNode currNode) {
        for (int j = i; j < word.length(); j++) {
            if (word.charAt(j) == '.') {
                for (CharNode charNode : currNode.charNodes) {
                    if (charNode != null) {
                        if (j == word.length() - 1 && charNode.endCount > 0) {
                            return true;
                        }
                        if (searchHelper(word, j + 1, charNode)) {
                            return true;
                        }
                    }
                }
                return false;
            }else {
                int index = word.charAt(j) - 'a';
                CharNode charNode = currNode.charNodes[index];
                if (charNode == null) {
                    return false;
                }
                if (charNode.passCount < 0) {
                    return false;
                }
                if (j == word.length() - 1) {
                    return charNode.endCount > 0;
                }
                currNode = charNode;
            }
        }
        return false;
    }

    class CharNode{
        int endCount = 0;
        int passCount = 0;
        CharNode[] charNodes = new CharNode[26];
    }
}
