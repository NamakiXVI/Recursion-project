import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Image;
import java.util.concurrent.TimeUnit;

public class EightQueens {

    int boardSize = 8; // Größe des Feldes

    int delay = 10; // verzögerung der einzelnen Schritte in Millisekunden

    boolean[][] board = new boolean[boardSize][boardSize];

    int targetQueens = boardSize;
    int currentQueens = 0;

    int fieldSize = 70;

    ImageIcon queen = new ImageIcon("queen.png");

    JFrame frame = new JFrame();

    Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

    JLabel[][] boardUI = new JLabel[boardSize][boardSize];

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
        Image image = queen.getImage().getScaledInstance(fieldSize, fieldSize, java.awt.Image.SCALE_SMOOTH);
        queen = new ImageIcon(image);
        frame.setSize(boardSize*fieldSize + 100, boardSize*fieldSize + 100);
        frame.setVisible(true);
        frame.setTitle("EightQueens");
        frame.setLayout(null);
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int x = 0; x < boardUI.length; x++) {

            for (int y = 0; y < boardUI.length; y++) {
                boardUI[x][y] = new JLabel();
                frame.add(boardUI[x][y]);
                boardUI[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                boardUI[x][y].setIcon(board[x][y] ? queen : null);
                // boardUI[x][y].setSize(40, 40);
                boardUI[x][y].setBounds(fieldSize * x + 50, fieldSize * y + 50, fieldSize, fieldSize);
                boardUI[x][y].setBorder(labelBorder);
                boardUI[x][y].setVisible(true);
            }

        }
    }

    public void updateUI() {
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < boardUI.length; x++) {

            for (int y = 0; y < boardUI.length; y++) {
                boardUI[x][y].setIcon(board[x][y] ? queen : null);
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
                updateUI();
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

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize - 1; y++) {
                if (board[x][y]) {
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
            }
            if (board[x][boardSize - 1]) {
                System.out.println("x");
            } else {
                System.out.println("o");
            }

        }

        System.out.println("________________________");

    }

}
