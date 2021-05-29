package game;

public class Game {

    private static Game game;
    Player bluePlayer;
    Player redPlayer;
    Board blueBoard;
    Board redBoard;

    private Game(Player p1, Player p2) {
        this.bluePlayer = p1;
        this.redPlayer = p2;

        blueBoard = new Board(bluePlayer);
        redBoard = new Board(redPlayer);
    }

    public static Game getGame(Player p1, Player p2) {
        if (game == null) {
            game = new Game(p1, p2);
        }
        return game;
    }

    public void startCombat() {

    }

}
