import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    //Board board = new Board();
    private ArrayList<Integer> usedNums = new ArrayList<>();


    private void p1Turn() {
        System.out.println();
        System.out.println("Pick a number 1-9");
        Scanner scanner = new Scanner(System.in);
        try {
            int p1Input = scanner.nextInt();
            //checks if the number is 1-9 and has not been used
            if (!usedNums.contains(p1Input) && (0 < p1Input) && (p1Input <= 9)) {
                usedNums.add(p1Input);
                int[] rowCol = Board.posToBoardPos.get(p1Input);
                Board.gameBoard[rowCol[0]][rowCol[1]] = 'X';
                Board.printBoard();
            } else {
                System.out.println("Pick an Available Number Number");
                p1Turn();
            }
        }catch (InputMismatchException e){
            p1Turn();
        }
    }


    private void p2Turn() {
        System.out.println();
        System.out.println("Pick a number 1-9");
        Scanner scanner = new Scanner(System.in);
        try {
            int p2Input = scanner.nextInt();
            //checks if the number is 1-9 and has not been used
            if (!usedNums.contains(p2Input) && (0 < p2Input) && (p2Input <= 9)) {
                usedNums.add(p2Input);
                int[] rowCol = Board.posToBoardPos.get(p2Input);
                Board.gameBoard[rowCol[0]][rowCol[1]] = 'O';
                Board.printBoard();
            } else {
                System.out.println("Pick an Available Number");
                p2Turn();
            }
        }catch (InputMismatchException e){
            p2Turn();
        }
    }
//game loop
    public void runGame() {
        while (true) {
            if (!winCheck()) {
                p1Turn();
            } else {
                break;
            }
            if (!winCheck()) {
                p2Turn();
            } else {
                break;
            }

        }
    }

    private boolean winCheck() {
        //checks rows for wins
        for (int i = 1; i <= 7; i += 3) {
            int[] inputToBoardPos = Board.posToBoardPos.get(i);
            int row = inputToBoardPos[0];
            int col = inputToBoardPos[1];
            if ((Board.gameBoard[row][col] != ' ') && (Board.gameBoard[row][col] == Board.gameBoard[row][col + 2]) &&
                    (Board.gameBoard[row][col + 2] == Board.gameBoard[row][col + 4])) {
                System.out.println();
                System.out.println(Board.gameBoard[row][col] + " wins");
                return true;

            }

        }
        //checks columns for wins
        for (int i = 1; i <= 3; i++) {
            int[] inputToBoardPos = Board.posToBoardPos.get(i);
            int row = inputToBoardPos[0];
            int col = inputToBoardPos[1];
            if ((Board.gameBoard[row][col] != ' ') && (Board.gameBoard[row][col] == Board.gameBoard[row + 2][col]) &&
                    (Board.gameBoard[row + 2][col] == Board.gameBoard[row + 4][col])) {
                System.out.println();
                System.out.println(Board.gameBoard[row][col] + " wins");
                return true;
            }
        }
        //checks diagonals for win
        if ((Board.gameBoard[2][2] != ' ')) {
            if ((Board.gameBoard[0][0] == Board.gameBoard[2][2]) && (Board.gameBoard[2][2] == Board.gameBoard[4][4]) ||
                    (Board.gameBoard[0][4] == Board.gameBoard[2][2]) && (Board.gameBoard[2][2] == Board.gameBoard[4][0])) {
                System.out.println();
                System.out.println(Board.gameBoard[2][2] + " wins");
                return true;
            }
        }
        //checks for a draw
        if (usedNums.size() == 9) {
            System.out.println();
            System.out.println("Draw");
            return true;

        }
        return false;
    }
}
