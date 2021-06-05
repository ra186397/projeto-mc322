package game;

import java.util.ArrayList;

import card.Follower;

import java.util.Random;
import java.util.Scanner;

public class Board {

    private ArrayList<Follower> cards;
    private ArrayList<Follower> combatingFollowers;
    private Player player;
    private boolean currentTurn;
    private Scanner scan;
    private Board opponentBoard;
    private Random r;

    public Board(Player player) {
        r = new Random();
        this.player = player;
        this.scan = new Scanner(System.in);
        this.cards = new ArrayList<Follower>();
        this.combatingFollowers = new ArrayList<Follower>();
    }

    public void addCard(Follower card) {
        if (cards.size() >= 6) {
            System.out.println("Escolha uma unidade aliada");
            int follower_num = scan.nextInt();
            Follower follower = cards.get(follower_num);
            if (follower.getCost() < card.getCost()) {
                if (player.getCurrentMana() >= card.getCost() - follower.getCost()) {
                    player.spendMana(card.getCost() - follower.getCost());
                    cards.remove(follower_num);
                    cards.add(card);
                } else {
                    System.out.println("Sem mana o suficiente");
                }
            } else if (player.getCurrentMana() >= card.getCost()) {
                player.spendMana(card.getCost());
                cards.remove(follower_num);
                cards.add(card);
            }
        } else if (player.getCurrentMana() >= card.getCost()) {
            player.spendMana(card.getCost());
            cards.add(card);
        }
    }

    public ArrayList<Follower> getCards() {
        return cards;
    }

    public boolean getCurrentTurn() {
        return this.currentTurn;
    }

    public ArrayList<Follower> getCombatingFollowers() {
        return this.combatingFollowers;
    }

    public Player getPlayer() {
        return player;
    }

    void setTurn(boolean turn) {
        currentTurn = turn;
    }

    public void determineTurn() {
        if (r.nextInt(1) == 0) {
            currentTurn = true;
            opponentBoard.setTurn(false);
        } else {
            currentTurn = false;
            opponentBoard.setTurn(true);
        }
    }

    public void removeCard(int card) {
        cards.remove(card);
    }

    public void moveToCombat(int n) {
        combatingFollowers.add(cards.get(n));
        cards.remove(n);
    }

    public void returnFromCombat(int n) {
        cards.add(combatingFollowers.get(n));
        combatingFollowers.remove(n);
    }

}
