import java.util.ArrayList;

import card.Follower;

import java.util.Random;

public class Board {

    private ArrayList<Follower> cardsBlue;
    private ArrayList<Follower> cardsRed;
    private Player redPlayer;
    private Player bluePlayer;
    private Color currentTurn;

    public Board(Player redPlayer, Player bluePlayer) {
        Random r = new Random();
        this.redPlayer = redPlayer;
        this.bluePlayer = bluePlayer;
        if (r.nextInt(2) == 0) {
            this.currentTurn = Color.RED;
        } else {
            this.currentTurn = Color.BLUE;
        }

        this.cardsBlue = new ArrayList<Follower>();
        this.cardsRed = new ArrayList<Follower>();

    }

    public void addCardByColor(Follower card, Color color) {
        if (color == Color.RED) {
            cardsRed.add(card);
        } else {
            cardsBlue.add(card);
        }
    }

}
