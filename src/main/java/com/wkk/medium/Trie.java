package com.wkk.medium;

import com.wkk.util.CharNode;

public class Trie {

    public CharNode node = new CharNode();

    public Trie() {

    }

    public void insert(String word) {
        CharNode loopNode = node;
        for (int i = 0; i < word.length(); i++) {
            CharNode charNode = loopNode.charNodes[word.charAt(i) - 'a'];
            if (charNode == null) {
                charNode = new CharNode();
                loopNode.charNodes[word.charAt(i) - 'a'] = charNode;
            }
            charNode.passCount++;
            if (i == word.length() - 1) {
                charNode.endCount++;
            }
            loopNode = charNode;
        }
    }

    public boolean search(String word) {
        CharNode loopNode = node;
        for (int i = 0; i < word.length(); i++) {
            CharNode nextNode = loopNode.charNodes[word.charAt(i) - 'a'];
            if (nextNode == null) {
                return false;
            }
            if (i == word.length() - 1) {
                return nextNode.endCount > 0;
            }
            loopNode = nextNode;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        CharNode loopNode = node;
        for (int i = 0; i < prefix.length(); i++) {
            CharNode nextNode = loopNode.charNodes[prefix.charAt(i) - 'a'];
            if (nextNode == null) {
                return false;
            }
            if (i == prefix.length() - 1) {
                return nextNode.passCount > 0;
            }
            loopNode = nextNode;
        }
        return false;
    }
}
