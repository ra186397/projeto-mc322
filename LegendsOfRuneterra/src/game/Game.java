package game;

public class Game {
    
    private static Game game;
    Player p1;
    Player p2;

    private Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public static Game getGame(Player p1, Player p2) {
        if (game == null) {
            game = new Game(p1, p2);
        }
        return game;
    }

}
