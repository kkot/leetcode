package Problem127_Word_Ladder;

import java.util.*;

public class Solution {
    Set<String> wordList;

    /**
     * Taken from other solution.
     * Its complexity depends on length of checkedWord * const (num of characters).
     * My naive approach has O(length of checkedWord * num of other words to check).
     */
    Set<String> possibleWords(String checkedWord) {
        Set<String> results = new HashSet<>();
        char[] chs = checkedWord.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                char old = chs[i];
                chs[i] = c;
                String target = String.valueOf(chs);
                chs[i] = old;

                if (wordList.contains(target)) {
                    results.add(target);
                }
            }
        }
        return results;
    }

    public int ladderLength(String beginWord, String endWord, List<String> aWordList) {
        this.wordList = new HashSet<>(aWordList);

        Queue<String> wordsQueue = new ArrayDeque<>();
        wordsQueue.add(beginWord);

        int depth = 1;
        int round = 1;
        while (!wordsQueue.isEmpty()) {
            String currentWord = wordsQueue.remove();
            if (currentWord.equals(endWord)) {
                return depth;
            }
            Set<String> possibleWords = possibleWords(currentWord);
            for (String possibleWord : possibleWords) {
                wordsQueue.add(possibleWord);
                wordList.remove(possibleWord);
            }
            round--;
            if (round == 0) {
                round = wordsQueue.size();
                depth++;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().ladderLength("hit", "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog")));

    }
}