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

    public ArrayList<Follower> getCombatingFollowers() {
        return this.combatingFollowers;
    }

    public Player getPlayer() {
        return player;
    }

    public void removeCard(int card) {
        cards.remove(card);
    }

    public void moveToCombat(int position, Follower follower) {
        combatingFollowers.set(position, follower);
        cards.remove(follower);
    }

    public void returnFromCombat(Follower follower) {
        cards.add(follower);
        combatingFollowers.remove(follower);
    }

    public void closeScan(){
        scan.close();
    }

}
