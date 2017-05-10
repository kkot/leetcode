package Solution_310_Minimum_Height_Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// no passing all tests

public class Solution {
    int go(int currentNode, int fromEdge, int[][] edges, Map<String, Integer> cache, int shortest) {
        int maxLen = 0;
        for (int nextEdge = 0; nextEdge < edges.length; nextEdge++) {
            if (nextEdge == fromEdge) {
                continue;
            }
            int[] edge = edges[nextEdge];
            if (edge[0] != currentNode
                    && edge[1] != currentNode) {
                continue;
            }
            int nextNode = edge[0] == currentNode ? edge[1] : edge[0];

            String key = nextNode + "|" + nextEdge;
            Integer currLength;
            if (cache.containsKey(key)) {
                currLength = 1 + cache.get(key);
            } else {
                int nextLength = go(nextNode, nextEdge, edges, cache, shortest);
                cache.put(key, nextLength);
                currLength = 1 + nextLength;
            }
            if (currLength > shortest) {
                cache.put(currentNode + "|" + fromEdge, currLength);
                return currLength;
            }
            if (currLength > maxLen) {
                maxLen = currLength;
            }
        }
        cache.put(currentNode + "|" + fromEdge, maxLen);
        return maxLen;

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int shortest = Integer.MAX_VALUE;
        List<Integer> shortestNums = new ArrayList<>();
        Map<String, Integer> cache = new HashMap<>();
        for (int nodeNum = n - 1; nodeNum >= 0; nodeNum--) {
            int path = go(nodeNum, -1, edges, cache, shortest);
            //System.out.println("node " + i + " path " + path);
            if (path < shortest) {
                shortest = path;
                shortestNums.clear();
                shortestNums.add(nodeNum);
            } else if (path == shortest) {
                shortestNums.add(nodeNum);
            }
        }
        return shortestNums;
    }
}