public class EightQueens {
    boolean[][] board = new boolean[8][8];

    public static void main(String[] args) {
        EightQueens eq = new EightQueens();

    }

    public void solveBoard(int row){
        int column = 0;
        if (isSafe(row, column)) {
            board[row][column] = true; 
        }
    }

    public boolean isSafe(int row, int col){
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        for (int i = row; i >= 0; i--) {
            for (int j = 0; j >= 0; j--) {
                if (board[i][j]) {
                    return false;
                }
            }
        }

        for (int i = row; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }
}
