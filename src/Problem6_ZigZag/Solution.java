package Problem6_ZigZag;

public class Solution {
    public String convert(String s, int numRows) {
        String result = "";
        for (int row = 0; row < numRows; row++) {
            int skip1 = 2 * numRows - 2;
            int skip2 = 2 * (numRows - row) - 2;

            int beg = row;
            while (true) {
                if (beg < s.length()) {
                    result += s.substring(beg, beg + 1);
                } else {
                    break;
                }
                if (skip2 != skip1 && skip2 != 0) {
                    if (beg + skip2 < s.length()) {
                        result += s.substring(beg + skip2, beg + skip2 + 1);
                    }
                }
                beg += skip1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }
}