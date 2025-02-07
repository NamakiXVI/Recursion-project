
//Libraries werden importiert
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Image;
import java.util.concurrent.TimeUnit;

public class EightQueens {

    int boardSize = 8; // die Größe des Feldes wird festgelegt

    int delay = 10; // verzögerung der einzelnen Schritte in Millisekunden

    boolean[][] board = new boolean[boardSize][boardSize];

    int targetQueens = boardSize;

    int currentQueens = 0;

    int fieldSize = 70; // Kantenlänge für ein Feld wird festgelegt

    ImageIcon queen = new ImageIcon("queen.png");

    JFrame frame = new JFrame();

    Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 2); // Kontur für JLabel wird mit BorderFactory
                                                                         // gemacht

    JLabel[][] boardUI = new JLabel[boardSize][boardSize]; // JLabel Array für das Feld wird definiert

    int screenSize = (fieldSize + 4) * boardSize + 100; // Größe des Fensters wird basierend auf der Anzahl der Felder
                                                        // festgelegt

    // Main methode
    public static void main(String[] args) {

        EightQueens eq = new EightQueens(); // Neue Instanz von EightQueens wird erstellt
        eq.setUserInterface(); // UI wird aufgerufen
        if (eq.solveBoard(0)) {
            System.out.println("Solved");
            eq.printBoard();
        } else {
            System.out.println("No solution found");
        }

    }

    // Methode zum aufsetzten des UI
    public void setUserInterface() {
        Image image = queen.getImage().getScaledInstance(fieldSize, fieldSize, java.awt.Image.SCALE_SMOOTH);
        queen = new ImageIcon(image);
        frame.setSize(screenSize, screenSize);
        frame.setVisible(true);
        frame.setTitle("EightQueens");
        frame.setLayout(null);
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        for (int x = 0; x < boardUI.length; x++) {
            for (int y = 0; y < boardUI.length; y++) {
                boardUI[x][y] = new JLabel();
                frame.add(boardUI[x][y]);
                boardUI[x][y].setHorizontalAlignment(SwingConstants.CENTER);
                boardUI[x][y].setIcon(board[x][y] ? queen : null);
                boardUI[x][y].setBounds(fieldSize * x + 50, fieldSize * y + 50, fieldSize, fieldSize);
                boardUI[x][y].setBorder(labelBorder);
                boardUI[x][y].setVisible(true);
            }

        }
    }

    // Methode zum updaten der UI
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

    // Methode mit der der Board gelöst wird
    public boolean solveBoard(int row) {

        // wenn alle Queens platziert sind wird True zurückgegeben
        if (currentQueens >= targetQueens) {
            return true;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(row, col)) { // Es wird geprüft ob die Position geschlagen werden kann
                board[row][col] = true; // wenn sie nicht geschlagen wird wird eine Figur dorthin gestellt
                currentQueens++;
                updateUI();
                if (solveBoard(row + 1)) { // Stack wird aufgebaut
                    updateUI();
                    return true;
                }
                board[row][col] = false; // Figur wird wieder weggenommen wenn keine Lösung gefunden wurde
                currentQueens--;
            }
        }
        updateUI();
        return false;

    }

    // Methode zum überprüfen ob ein Feld geschlagen werden kann
    public boolean isSafe(int row, int col) {

        // Spalte Diagonale wird geprüft
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Linke Diagonale wird geprüft
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // Rechte Diagonale wird geprüft
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }
        // Zeile wird nicht geprüft, da nach jedem platzieren die Zeile gewechselt wird
        return true;
    }

    // Methode zum ausdrucken des Feldes in der Konsole
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
