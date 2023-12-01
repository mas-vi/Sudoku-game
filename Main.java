public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(9);
        // int[][] board={
        // {7,0,2,0,5,0,6,0,0},
        // {0,0,0,0,0,3,0,0,0},
        // {1,0,0,0,0,9,5,0,0},
        // {8,0,0,0,0,0,0,9,0},
        // {0,4,3,0,0,0,7,5,0},
        // {0,9,0,0,0,0,0,0,8},
        // {0,0,9,7,0,0,0,0,5},
        // {0,0,0,2,0,0,0,0,0},
        // {0,0,7,0,4,0,2,0,3}
        // };
        // sudoku.setBoard(board);
        long start = System.currentTimeMillis();
        sudoku.generateBoard();
        long end = System.currentTimeMillis();
        sudoku.print();
        if (sudoku.solve())
            sudoku.print();
        else
            System.out.println("Could not solve");
        System.out.println("The generation time was " + (end - start));
    }
}