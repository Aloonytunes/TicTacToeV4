public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player();
        Board.printBoard();
        board.fillMap();
        player.runGame();
    }
}