package com.wkk.medium;

import java.util.*;

public class LetterCombinations {
    private List<String> collect = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        iterTools(digits, 0, map, "");
        return collect;
    }

    public void iterTools(String digits, int index, Map<Character, List<Character>> map, String prev) {
        if (index == digits.length()) {
            collect.add(prev);
            return;
        }
        for (Character c : map.get(digits.charAt(index))) {
            iterTools(digits, index + 1, map, prev + c);
        }
    }
}
