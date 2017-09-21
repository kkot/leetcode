package Problem_306_Additive_Number;

public class Solution {
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                long num1 = Long.valueOf(num.substring(0, i));
                long num2 = Long.valueOf(num.substring(i, j));
                if (num.substring(0).startsWith("0") && i != 1) {
                    continue;
                }
                if (num.substring(i).startsWith("0") && i != j - 1) {
                    continue;
                }
                long exp = num1 + num2;
                String remNum = num.substring(j);
                while (remNum.startsWith(String.valueOf(exp))) {
                    remNum = remNum.substring(String.valueOf(exp).length());
                    long prevExp = exp;
                    exp = num2 + exp;
                    num2 = prevExp;
                }
                if (remNum.length() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}