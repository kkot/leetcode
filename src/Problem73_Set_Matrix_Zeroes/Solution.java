package Problem73_Set_Matrix_Zeroes;

/**
 * Without additional memory allocation.
 */
public class Solution {
    int emptyX = -1;
    int emptyY = -1;

    int[][] matrix;

    int maxX;
    int maxY;

    boolean debug = false;

    void findEmptyXY() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (matrix[x][y] == 0) {
                    emptyX = x;
                    emptyY = y;
                    return;
                }
            }
        }
    }

    void printMatrix() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void cleanEmpty() {
        for (int x = 0; x < maxX; x++) {
            matrix[x][emptyY] = 0;
        }
        for (int y = 0; y < maxY; y++) {
            matrix[emptyX][y] = 0;
        }
    }



    public void setZeroes(int[][] matrix) {
        this.matrix = matrix;
        this.maxX = matrix.length;
        this.maxY = matrix[0].length;

        if (debug) {
            System.out.println("original");
            printMatrix();
        }

        findEmptyXY();

        if (emptyX == -1) {
            return;
        }

        if (debug) {
            System.out.println("x " + emptyX + ", y " + emptyY);
        }

        for (int x = 0; x < maxX; x++) {
            if (x == emptyX) {
                continue;
            }
            for (int y = 0; y < maxY; y++) {
                if (y == emptyY) {
                    continue;
                }
                if (this.matrix[x][y] == 0) {
                    this.matrix[emptyX][y] = 0;
                    this.matrix[x][emptyY] = 0;
                }
            }
        }

        if (debug) {
            System.out.println("empty row, column set");
            printMatrix();
        }

        for (int x = 0; x < maxX; x++) {
            if (x == emptyX) {
                continue;
            }
            for (int y = 0; y < maxY; y++) {
                if (y == emptyY) {
                    continue;
                }
                if (this.matrix[emptyX][y] == 0
                        || this.matrix[x][emptyY] == 0) {
                    this.matrix[x][y] = 0;
                }
            }
        }

        cleanEmpty();
        if (debug) {
            System.out.println("solution");
            printMatrix();
        }

    }

    public static void main(String[] args) {
        new Solution().setZeroes(new int[][] {
                {1,1,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}
        });
    }
}