package game;

import java.util.ArrayList;

import card.Effect;
import card.Follower;
import card.Trigger;

import java.util.Random;
import java.util.Scanner;

public class Board{

    private ArrayList<Follower> cards;
    private ArrayList<Follower> combatingFollowers;
    private Player player;
    private boolean currentTurn;
    private Scanner scan;
    private Random r;

    public Board(Player player) {
        r = new Random();
        this.player = player;
        this.scan = new Scanner(System.in);
        this.cards = new ArrayList<Follower>();
        this.combatingFollowers = new ArrayList<Follower>();
    }

    public void replaceFollower(Follower card, int followerNum, Board opponentBoard){
        cards.remove(followerNum);
        cards.add(card);
        for (Effect effect : card.getEffects()) {
            effect.checkTrigger(Trigger.PLAY, this, opponentBoard, card);
        }
    }

    public boolean addCard(Follower card, Board opponentBoard) { //Retorna true se foi possível jogar a carta, false senão.
        if (cards.size() >= 6) {
            int followerNum;
            if (player.isHuman()){
                System.out.println("Escolha uma unidade aliada para substituir");
                followerNum = scan.nextInt();
            }
            else {
                followerNum = player.getRandomResult(6);//tamanho maximo da board, talvez seja bom tacar um define ou sla
            }
            Follower follower = cards.get(followerNum);
            if (follower.getCost() < card.getCost()) {
                if (player.spendMana(card.getCost() - follower.getCost(), false)) {
                    replaceFollower(card, followerNum, opponentBoard);
                    return true;
                }

            } else if (player.spendMana(card.getCost(), false)) {
                replaceFollower(card, followerNum, opponentBoard);
                return true;
            }

        } else if (player.spendMana(card.getCost(), false)) {
            cards.add(card);
            for (Effect effect : card.getEffects()) {
                effect.checkTrigger(Trigger.PLAY, this, opponentBoard, card);
            }
            return true;
        }

        return false;
    }

    public ArrayList<Follower> getCards() {
        return cards;
    }

    public void determineTurn(Board opponentBoard) {
        if (r.nextInt(1) == 0) {
            currentTurn = true;
            opponentBoard.setTurn(false);
        } else {
            currentTurn = false;
            opponentBoard.setTurn(true);
        }
    }

    public void switchTurn(Board opponentBoard) {
        if (currentTurn) {
            currentTurn = false;
            opponentBoard.setTurn(true);
        }
        else {
            currentTurn = true;
            opponentBoard.setTurn(false);
        }
    }

    public boolean getCurrentTurn() {
        return this.currentTurn;
    }

    private void setTurn(boolean turn) {
        currentTurn = turn;
    }
    

    public ArrayList<Follower> getCombatingFollowers() {
        return this.combatingFollowers;
    }

    public Player getPlayer() {
        return player;
    }

    public void removeCard(int card) {
        cards.remove(card);
    }

    public void moveToCombat(int position, int follower) {
        if (follower == -1 && currentTurn == true){
            combatingFollowers.set(position, null);
        }
        else if (follower > cards.size()) {
            System.out.print("Nao é uma unidade válida");
            combatingFollowers.set(position, null);
        }
        else {
            combatingFollowers.set(position, cards.get(follower));
            cards.remove(follower);
        }
    }

    public void returnFromCombat(int n) {
        cards.add(combatingFollowers.get(n));
        combatingFollowers.remove(n);
    }

}
