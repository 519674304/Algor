package com.wkk.medium;

import org.junit.Test;

import java.util.*;

public class WordBreak {

    @Test
    public void test11(){
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }
    Map<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, wordDict, "");
    }

    public boolean helper(String target, List<String> wordDict, String prev) {
        if (!target.startsWith(prev)) {
            return false;
        }
        if (target.equals(prev)) {
            return true;
        }
        for (String word : wordDict) {
            if (Objects.nonNull(map.get(prev + word))) {
                if (map.get(prev + word)) {
                    return true;
                }else {
                    continue;
                }
            }
            boolean restResult = helper(target, wordDict, prev + word);
            map.put(prev + word, restResult);
            if (restResult) {
                return true;
            }
        }
        return false;
    }
}
