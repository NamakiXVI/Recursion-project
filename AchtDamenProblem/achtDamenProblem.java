import javax.swing.*;

public class EightQueens {
    boolean[][] board = new boolean[8][8];

    int targetQueens = 8;
    int currentQueens = 0;

    JFrame frame = new JFrame();

    

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


    public void setUserInterface(){
        frame.setSize(800, 800);
        frame.setVisible(true);
        
        
    }

    public boolean solveBoard(int row) {
        if (currentQueens >= targetQueens) {
            return true;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(row, col)) {
                board[row][col] = true;
                currentQueens++;
                if(solveBoard(row + 1)) {
                    return true;
                }
                board[row][col] = false;
                currentQueens--;
            }
        }

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
