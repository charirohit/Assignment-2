import java.util.Scanner;

public class TicTacToe {

    private static final char empty = ' ';
    private static final char player_X = 'X';
    private static final char player_O = 'O';

    private char[][] board;
    private char currentPlayer;
    
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = player_X;
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = empty;
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                
                System.out.print(board[i][j] + " | ");
                
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean makeMove(int row, int column) {
        if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == empty) {
            board[row][column] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != empty && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != empty && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        if (board[0][0] != empty && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != empty && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == empty) {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        if(currentPlayer==player_X){
            currentPlayer=player_O;
        }
        else{
            currentPlayer=player_X;
        }
       
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon && !game.isBoardFull()) {
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + "'s turn.");
            System.out.print("Enter row (0, 1, 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1, 2): ");
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                if (game.checkForWin()) {
                    gameWon = true;
                    game.printBoard();
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("This move is not valid.");
            }
        }

        if (!gameWon) {
            game.printBoard();
            System.out.println("The game is a draw.");
        }

        scanner.close();
    }
}
