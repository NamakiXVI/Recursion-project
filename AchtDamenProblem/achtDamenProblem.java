import javax.swing.*;
import javax.swing.border.Border;


import java.awt.Color;

public class EightQueens {
    boolean[][] board = new boolean[8][8];

    int targetQueens = 8;
    int currentQueens = 0;

    JFrame frame = new JFrame();

    Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

    JLabel[][] boardUI = new JLabel[8][8];

    public static void main(String[] args) {

        EightQueens eq = new EightQueens();
        eq.setUserInterface();
        if (eq.solveBoard(0)) {
            System.out.println("Solved");
            eq.printBoard();
        } else {
            System.out.println("No solution found");
        }

    }

    public void setUserInterface() {
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setTitle("EightQueens");
        frame.setLayout(null);

        for (int x = 0; x < boardUI.length; x++) {

            for (int y = 0; y < boardUI.length; y++) {
                boardUI[x][y] = new JLabel();
                frame.add(boardUI[x][y]);
                boardUI[x][y].setText(board[x][y] ? "x" : "");
                // boardUI[x][y].setSize(40, 40);
                boardUI[x][y].setBounds(40 * x + 100, 40 * y + 100, 40, 40);
                boardUI[x][y].setBorder(labelBorder);
                boardUI[x][y].setVisible(true);
            }

        }

    }

    public void updateUI() {
        for (int x = 0; x < boardUI.length; x++) {

            for (int y = 0; y < boardUI.length; y++) {
                boardUI[x][y].setText(board[x][y] ? "x" : "");
            }

        }

    }

    public boolean solveBoard(int row) {
        if (currentQueens >= targetQueens) {
            return true;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(row, col)) {
                board[row][col] = true;
                currentQueens++;
                if (solveBoard(row + 1)) {
                    updateUI();
                    return true;
                }
                board[row][col] = false;
                currentQueens--;
            }
        }

        updateUI();
        return false;

    }

    public boolean isSafe(int row, int col) {

        // Überprüfung der Spalte
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Überprüfung der linken oberen Diagonale
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // Überprüfung der rechten oberen Diagonale
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        return true;
    }

    public void printBoard() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 7; y++) {
                if (board[x][y]) {
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
            }
            if (board[x][7]) {
                System.out.println("x");
            } else {
                System.out.println("o");
            }

        }

        System.out.println("________________________");

    }

}
