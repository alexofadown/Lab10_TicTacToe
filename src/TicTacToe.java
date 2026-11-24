import java.util.Scanner;

public class TicTacToe {
    static void main() {
        Scanner scan = new Scanner(System.in);
        String player1 = " X ";
        String player2 = " O ";
        //this boolean HAS to be out here because the most outer do-while depends on it
        boolean playAgain;

        //This do-while loops allows for multiple games to be played
        do {
            //variables initialized within the code so they can get cleared/be set to false every time a new game starts
            int p1row;
            int p1col;
            int p2row;
            int p2col;
            String yn;
            playAgain = false;
            boolean win = false;
            boolean tie = false;

            clearBoard();

            //This do-while loop keeps running while the game is still going
            // Stops once a winner or tie occurs
            do {
                boolean p1valid = false;
                boolean p2valid = false;

                //Player 1 - loops until valid move made
                do {
                    p1row = InputHelper.getRangedInt(scan, "What row do you want to put an X in (1-3)?", 1, 3);
                    p1col = InputHelper.getRangedInt(scan, "What column do you want to put an X in (1-3)?", 1, 3);
                    if (board[p1row-1][p1col-1].equals(" - ")) {
                        board[p1row-1][p1col-1] = " X ";
                        p1valid = true;
                    }
                    else {
                        System.out.println("Not a valid move! Please try again.");
                    }
                } while (!p1valid);

                displayBoard();

                //checks to see if player 1 wins or there is a tie (player 1 always places move 9 so tie is here and not below)
                if (isWin(player1)) {
                    System.out.println("Player 1 wins!");
                    break; //the do while loop only checks if the condition changed at the end so this ends the game to prevent the player 2 items from running again
                }
                else if (isTie()) {
                    System.out.println("It's a tie!");
                    break; //the do while loop only checks if the condition changed at the end so this ends the game to prevent the player 2 items from running again
                }

                //Player 2 - loops until valid move made
                do {
                    p2row = InputHelper.getRangedInt(scan, "What row do you want to put an O in (1-3)?", 1, 3);
                    p2col = InputHelper.getRangedInt(scan, "What column do you want to put an O in (1-3)?", 1, 3);
                    if (board[p2row-1][p2col-1].equals(" - ")) {
                        board[p2row-1][p2col-1] = " O ";
                        p2valid = true;
                    }
                    else {
                        System.out.println("Not a valid move! Please try again.");
                    }
                } while (!p2valid);

                displayBoard();

                //checks to see if player 2 wins, player 1 was already checked and player 2 never does the last move
                if (isWin(player2)) {
                    System.out.println("Player 2 wins!");
                    win = true;
                }

            } while (!win && !tie);

            //asks if they want to play again, restarts if they do
            yn = InputHelper.getYNConfirm(scan, "Do you want to play again? (Y/N)");
            if (yn.equalsIgnoreCase("Y")) {
                playAgain = true;
            }
        } while (playAgain);

    }

    private static String[][] board = new String[3][3];

    private static void clearBoard() {
        //Accesses each row
        for (int r = 0; r < board.length; r++) {

            //Accesses each column in the row
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = " - ";
            }
        }
    }
    //sets all the board elements to "-"

    private static void displayBoard() {
            //Accesses each row
            for(int r = 0; r < board.length; r++){

                //Accesses each column in the row
                for(int c = 0; c < board[r].length; c++){
                    System.out.print(board[r][c]);
                }
                System.out.println();
            }
    }
    //shows the Tic Tac Toe game board in its current state
    //Should appear as a 3x3 grid

    //make sure to add a -1 when you call this
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" - ");
    }
    //returns true if there is a space at the given proposed move coordinates, which means it is a legal move
    //Returns false otherwise

    private static boolean isWin(String player) {
        if (isColWin(player) || isDiagonalWin(player) || isRowWin(player)) {
            return true;
        }
        else {
        return false;
        }
    }
    //checks to see if there is a win state on the current board for the specified player (X or O). This method, in turn, calls three additional methods (isColWin, isRowWin, isDiagonalWin) that break down the 3 possible win types.

    private static boolean isColWin(String player) {
        for(int c = 0; c < board.length; c++){
            if ((board[0][c].equalsIgnoreCase(player)) && (board[0][c].equalsIgnoreCase(board[1][c]) && board[1][c].equalsIgnoreCase(board[2][c]))) {
                return true;
            }
        }
        return false;
    }
    //checks for a col win for the specified player
    //Hint: Check to see if all of the positions in a column are equal to the given player's symbol (X or O). Make sure you check each column!

    private static boolean isRowWin(String player) {
        for(int r = 0; r < board.length; r++){
            if ((board[r][0].equalsIgnoreCase(player)) && (board[r][0].equalsIgnoreCase(board[r][1]) && board[r][1].equalsIgnoreCase(board[r][2]))) {
                return true;
            }
        }
        return false;
    }
    //checks for a row win for the specified player
    //Hint: Check to see if all of the positions in a row are equal to the given player's symbol (X or O). Make sure you check each row!

    private static boolean isDiagonalWin(String player) {
            if ((board[0][0].equalsIgnoreCase(player)) && (board[0][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[2][2]))) {
                return true;
            }
            else if ((board[2][0].equalsIgnoreCase(player)) && (board[2][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[0][2]))) {
                return true;
            }
        return false;
    }
    //checks for a diagonal win for the specified player
    //Hint: There are two different diagonals you need to check!

    private static boolean isTie() {
        //Accesses each row
        for(int r = 0; r < board.length; r++){
//Accesses each column in the row
            for(int c = 0; c < board[r].length; c++){
                if (board[r][c].equals(" - ")) {
                    return false;
                }
            }
        }
        return true;
    }
    //checks for a tie condition: all spaces on the board are filled, and no win has occurred

}
