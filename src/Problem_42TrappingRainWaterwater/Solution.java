package Problem_42TrappingRainWaterwater;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Integer> biggestAfter = new HashMap<>();

    public void buildBiggest(int[] heights) {
        int biggestIdx = -1;
        int biggestVal = -1;
        for (int i = heights.length - 1; i >= 0; i--) {
            int h = heights[i];
            if (biggestIdx == -1 || h > biggestVal) {
                biggestIdx = i;
                biggestVal = h;
            }
            biggestAfter.put(i - 1, biggestIdx);
        }
    }


    public int findBiggestAfter(int[] heights, int begIndex) {
        return biggestAfter.getOrDefault(begIndex, -1);
    }

    public int trap(int[] heights) {
        buildBiggest(heights);
        int[] levels = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int l = levels[i];
            if (h <= l) {
                continue;
            }
            int barrier = findBiggestAfter(heights, i + 1);
            if (barrier != -1 && barrier != i + 1) {
                for (int j = i + 1; j < barrier; j++) {
                    levels[j] = Math.min(h, heights[barrier]);
                }
            }
        }
        for (int i = 0; i < levels.length; i++) {
            System.out.print(levels[i] + " ");
        }
        int water = 0;
        for (int i = 0; i < heights.length; i++) {
            int diff = levels[i] - heights[i];
            if (diff > 0) {
                water += diff;
            }
        }
        return water;
    }
}