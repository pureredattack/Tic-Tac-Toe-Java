import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    //board creation
    private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char currentPlayer = 'X';
    private static Scanner input = new Scanner(System.in);
    private static Random rand = new Random(); //random sets for AI

    public static void main(String[] args) throws Exception {
        while(!GameOver()){
            System.out.println("Current board: ");
            printBoard();

            if(currentPlayer == 'X'){
                System.out.print("Player X, enter your move (row # [1-3] {space} column # [1-3]): ");
                int row = input.nextInt() - 1;
                int col = input.nextInt() - 1;
                board[row][col] = currentPlayer;
            } else {
                System.out.println("AI's turn");
                getaiMove();
            }

            // change players
            currentPlayer = (currentPlayer == 'X') ? 'O':'X';
        }

        System.out.println("Current board: ");
        printBoard();
        System.out.println("Game Over!");
    }


    private static void getaiMove() {
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = 'O';
    }

    private static int evaluate() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'X') {
                    return -10;
                } else if (board[i][0] == 'O') {
                    return 10;
                }
            }
        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'X') {
                    return -10;
                } else if (board[0][i] == 'O') {
                    return 10;
                }
            }
        }

        // check diagonal wins
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') {
                return -10;
            } else if (board[0][0] == 'O') {
                return 10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') {
                return -10;
            } else if (board[0][2] == 'O') {
                return 10;
            }
        }
        return 0;
    }

    private static boolean isMovesLeft(){
        for(int i = 0;i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean GameOver(){
        if(evaluate() == 10 || evaluate() == -10){
            return true;
        }

        if(isMovesLeft() == false){
            return true;
        }
        return false;
    }

    private static void printBoard(){
        System.out.println("----------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j] + "| ");
            }
            System.out.println();
            System.out.println("----------");
        }
    }
}
