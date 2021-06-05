package game;

import java.util.ArrayList;

import card.Follower;

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

        ArrayList<Follower> attackers = redBoard.getCombatingFollowers();
        ArrayList<Follower> defenders = blueBoard.getCombatingFollowers();
        Board attackingBoard = redBoard;
        Board defendingBoard = blueBoard;
        Player defender = bluePlayer;

        if (blueBoard.getCurrentTurn()) {

            attackers = blueBoard.getCombatingFollowers();
            defenders = redBoard.getCombatingFollowers();

            attackingBoard = blueBoard;
            defendingBoard = redBoard;

            defender = redPlayer;

        }

        for (int i = 0; i < attackers.size(); i++) {
            if (defenders.get(i) == null) {
                attackers.get(i).strike(defender);
            } else {
                attackers.get(i).strike(defenders.get(i));
                defenders.get(i).strike(attackers.get(i));
            }
        }

        for (int i = 0; i < attackers.size(); i++) {
            if (attackers.get(i).getCurrentHealth() > 0 && attackers.get(i) != null) {
                attackingBoard.returnFromCombat(i);
            }
            if (defenders.get(i).getCurrentHealth() > 0 && attackers.get(i) != null) {
                defendingBoard.returnFromCombat(i);
            }
        }

    }

}
