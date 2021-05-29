package game;

import java.util.ArrayList;

import card.Follower;

import java.util.Random;
import java.util.Scanner;

public class Board {
<<<<<<< HEAD:LegendsOfRuneterra/src/Board.java
    
    private ArrayList<Follower> cards;
    private Player player;
    private boolean currentTurn;
    private Scanner scan;
    private Board opponentBoard;
    private Random r;

    public Board(Player player, Player bluePlayer) {
        r = new Random();
        this.player = player;
        this.scan = new Scanner(System.in);
        this.cards = new ArrayList<Follower>();
    }

    public void addCard(Follower card) {
        if (cards.size() >= 6){
            System.out.println("Escolha uma unidade aliada");
            int follower_num = scan.nextInt();
            Follower follower = cards.get(follower_num);
            if (follower.getCost() < card.getCost()){
                if (player.getCurrentMana() >= card.getCost() - follower.getCost()){
                    player.spendMana(card.getCost() - follower.getCost());
                    cards.remove(follower_num);
                    cards.add(card);
                }
                else {
                    System.out.println("Sem mana o suficiente");
                }
            }
            else if (player.getCurrentMana() >= card.getCost()){
                player.spendMana(card.getCost());
                cards.remove(follower_num);
                cards.add(card);
            }
        }
        else if (player.getCurrentMana() >= card.getCost()){
            player.spendMana(card.getCost());
            cards.add(card);
        }
    }

    void setTurn(boolean turn){
        currentTurn = turn;
    }

    public void determineTurn(){
        if (r.nextInt(1) == 0){
            currentTurn = true;
            opponentBoard.setTurn(false);
        }
        else {
            currentTurn = false;
            opponentBoard.setTurn(true);
        }
    }

    public void removeCard(int card){
        cards.remove(card);
    }
=======

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
>>>>>>> 6db0d454ee0cb6f245d3f839f216e0b64c0d3896:LegendsOfRuneterra/src/game/Board.java

}
