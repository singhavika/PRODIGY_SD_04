import java.util.Scanner;

public class TASK_04 {
    public boolean isSafe(char[][] board, int row, int col, int number) {
        // row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false;
            }
        }
        // grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }
        int nrow = 0;
        int ncol = 0;
        if (col != board.length - 1) {
            nrow = row;
            ncol = col + 1;
        } else {
            nrow = row + 1;
            ncol = 0;
        }
        if (board[row][col] != '.') {
            if (helper(board, nrow, ncol)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');
                    if (helper(board, nrow, ncol)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] sudokuGrid = new char[9][9];

        System.out.println("Enter the Sudoku puzzle row by row (use . for empty cells):");

        for (int i = 0; i < 9; i++) {
            String row = scanner.next();
            while (row.length() != 9) {
                System.out.println("Each row must have exactly 9 characters. Please re-enter row " + (i + 1) + ":");
                row = scanner.next();
            }
            for (int j = 0; j < 9; j++) {
                sudokuGrid[i][j] = row.charAt(j);
            }
        }

        System.out.println("The entered Sudoku puzzle is:");
        printSudokuGrid(sudokuGrid);

        TASK_04 solver = new TASK_04();
        solver.solveSudoku(sudokuGrid);

        System.out.println("The solved Sudoku puzzle is:");
        printSudokuGrid(sudokuGrid);
    }

    private static void printSudokuGrid(char[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
