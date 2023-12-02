import java.util.Random;

public class Sudoku {
    private int dim;
    private int[][] matrix;

    public Sudoku(int dim) {
        this.dim = dim;
        matrix = new int[dim][dim];
    }

    public void setBoard(int[][] board) {
        this.matrix = board.clone();
    }

    public void print() {
        for (int i = 0; i < this.dim; i++) {
            if (i % 3 == 0)
                System.out.println("_".repeat(3 * dim - 2));
            for (int j = 0; j < this.dim; j++) {
                if (j % 3 == 0 || j == dim)
                    System.out.print("| ");
                System.out.print(matrix[i][j] + " ");
                if (j == dim - 1)
                    System.out.println("|");

            }
            if (i == dim - 1)
                System.out.println("_".repeat(3 * dim - 2));

        }

    }

    public boolean isValid(int n, int x, int y) {
        // Checks if n if already on the column
        for (int i = 0; i < dim; i++)
            if (matrix[i][y] == n)
                return false;
        // Checks if n is already on the row
        for (int j = 0; j < dim; j++)
            if (matrix[x][j] == n)
                return false;
        // Checks if n is inside a box
        for (int i = x - x % 3; i < x + 3 - x % 3; i++)
            for (int j = y - y % 3; j < y + 3 - y % 3; j++)
                if (matrix[i][j] == n)
                    return false;
        return true;
    }

    public boolean solve() {
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (matrix[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(num, i, j)) {
                            matrix[i][j] = num;
                            if (solve()) {
                                return true;
                            } else {
                                matrix[i][j] = 0;
                            }

                        }
                    }
                    return false;

                }
            }

        return true;
    }

    private void generatePermutation(int[] possibleNums) {
        Random rand = new Random(System.currentTimeMillis());
        // The PRNG seems to be biased towards values closer to 0
        // I will use a coin flip to force them in the interval [5,9]
        if (rand.nextInt(2) == 1)
            possibleNums[0] = rand.nextInt(5) + 1;
        else
            possibleNums[0] = rand.nextInt(5) + 5;
        for (int i = 1; i < 9; i++) {
            possibleNums[i] = rand.nextInt(9) + 1;
            while (possibleNums[i] == possibleNums[i - 1])
                possibleNums[i] = rand.nextInt(9) + 1;
        }
    }

    public boolean generateBoardSolver() {
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                if (matrix[i][j] == 0) {
                    int[] possibleNums = new int[9];
                    generatePermutation(possibleNums);

                    for (int num : possibleNums) {
                        if (isValid(num, i, j)) {
                            matrix[i][j] = num;
                            if (solve()) {
                                return true;
                            } else {
                                matrix[i][j] = 0;
                            }

                        }
                    }
                    return false;

                }
            }

        return true;
    }

    public void generateBoard() {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                matrix[i][j] = 0;
            }
        generateBoardSolver();
        int deleteCount = 64;
        while (deleteCount > 0) {
            int i = (int) (rand.nextInt(9));
            int j = (int) (rand.nextInt(9));
            if (matrix[i][j] != 0) {
                matrix[i][j] = 0;
                deleteCount--;
            }
        }

    }

}
