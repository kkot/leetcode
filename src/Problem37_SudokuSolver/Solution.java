package Problem37_SudokuSolver;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private Set<Integer> possibleNumbers(final int aX, final int aY, char[][] board) {
        Set<Integer> result = IntStream.rangeClosed(1, 9).mapToObj(i -> i).collect(Collectors.toSet());
        for (int i = 0; i < board.length; i++) {
            char row = board[aX][i];
            char col = board[i][aY];
            if (row != '.') {
                result.remove(Character.getNumericValue(row));
            }
            if (col != '.') {
                result.remove(Character.getNumericValue(col));
            }
            if (result.isEmpty()) {
                return result;
            }
        }

        int cellX = aX / 3;
        int cellY = aY / 3;

        for (int x = cellX * 3; x < (cellX + 1) * 3; x++) {
            for (int y = cellY * 3; y < (cellY + 1) * 3; y++) {
                char ch = board[x][y];
                if (ch != '.') {
                    result.remove(Character.getNumericValue(ch));
                }

            }
        }

        return result;
    }

    public boolean solveSudokuRec(char[][] board) {
        //printBoard(board);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                char element = board[x][y];
                if (element == '.') {
                    Set<Integer> numbers = possibleNumbers(x, y, board);
                    //System.out.println("possible number " + solutions);
                    if (numbers.isEmpty()) {
                        return false;
                    }
                    for (Integer solution : numbers) {
                        board[x][y] = (char)(solution + '0');
                        boolean solved = solveSudokuRec(board);
                        if (solved) {
                            return solved;
                        }
                        board[x][y] = '.';
                    }
                    return false;
                }

            }
        }
        return true;
    }

    private void printBoard(char[][] board) {
        System.out.println("");
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    public void solveSudoku(char[][] board) {
        boolean solved = solveSudokuRec(board);
        System.out.println("solved " + solved);
        printBoard(board);
    }

    public static void main(String[] args) {
        char[][] puzzle = {{'1', '2', '3'}, {'4', '.', '5'}, {'6', '7', '.'}};

        System.out.println(new Solution().possibleNumbers(1, 1, puzzle));

        new Solution().solveSudoku(new char[][]{"53..7....".toCharArray(),"6..195...".toCharArray(),".98....6.".toCharArray(),"8...6...3".toCharArray(),"4..8.3..1".toCharArray(),"7...2...6".toCharArray(),".6....28.".toCharArray(),"...419..5".toCharArray(),"....8..79".toCharArray()});
    }
}