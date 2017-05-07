import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem282_ExpressionAddOperators {

    public class Solution {
        public List<String> genSolutions(String solution, String remNum) {
            if (remNum.length() == 0) {
                return Arrays.asList(solution);
            }
            String newRem = remNum.substring(1);
            String firstLetter = remNum.substring(0, 1);
            if (solution.length() == 0) {
                return genSolutions(firstLetter, newRem);
            }

            List<String> solutions = new ArrayList<>();
            List<String> operators = Arrays.asList("*", "-", "+", "");
            for (String operator : operators) {
                if (operator.equals("")) {
                    if (solution.equals("0")
                            || solution.endsWith("+0")
                            || solution.endsWith("-0")
                            || solution.endsWith("*0")) {
                        continue;
                    }
                }
                solutions.addAll(genSolutions(solution + operator + firstLetter, newRem));
            }
            return solutions;
        }

        public List<String> genSolutions(String num) {
            return genSolutions("", num);
        }

        public long evalExpr(String expr) {
            int plusPos = expr.lastIndexOf("+");
            int minusPos = expr.lastIndexOf("-");
            int mulPos = expr.lastIndexOf("*");

            int pos;
            if (plusPos != -1) {
                pos = plusPos;
            } else if (minusPos != -1) {
                pos = minusPos;
            } else if (mulPos != -1) {
                pos = mulPos;
            } else {
                return Long.valueOf(expr);
            }

            String expr1 = expr.substring(0, pos);
            String expr2 = expr.substring(pos + 1);

            char oper = expr.charAt(pos);
            if (oper == '*') {
                return evalExpr(expr2) * evalExpr(expr1);
            } else if (oper == '+') {
                return evalExpr(expr2) + evalExpr(expr1);
            } else {
                return evalExpr(expr1) - evalExpr(expr2);
            }
        }

        public List<String> addOperators(String num, int target) {
            try {
                System.out.println("before solution");
                List<String> solutions = genSolutions(num);
                System.out.println("after solution " + solutions.size());
                return solutions.stream()
                        .filter(sol -> evalExpr(sol) == target).collect(Collectors.toList());
            } catch (Exception e) {
                System.out.println("error on " + num);
                return Arrays.asList();
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Problem282_ExpressionAddOperators().new Solution();
        //System.out.println("x "+ s.evalExpr("1*2*3*4*5-6-78+9"));
        List<String> solutions = s.addOperators("3456237490", 9191);
        for (String solution : solutions) {
            System.out.println(solution);
        }
    }
}
