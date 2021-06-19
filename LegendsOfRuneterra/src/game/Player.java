package game;

import java.util.ArrayList;
import java.util.Scanner;

import card.Card;

public class Player {

    private ArrayList<Card> hand;
    private int maxMana;
    private int currentMana;
    private int spellMana;
    private int nexusLife;
    private Deck deck;
    private Board board;

    public Player(Deck deck) {
        this.hand = new ArrayList<Card>();
        this.maxMana = 0;
        this.currentMana = 0;
        this.spellMana = 0;
        this.nexusLife = 20;
        this.deck = deck;
    }

    public void drawCard(int n) {
        for (int i = 0; i < n; i++) {
            hand.add(deck.getCards().get(0));
            deck.getCards().remove(0);
        }
    }

    public void drawCard(int n, String nameCard) {
        for (int i = 0; i < n; i++) {
            for (Card card : deck.getCards()) {
                if (card.getName() == nameCard){
                    hand.add(card);
                    deck.getCards().remove(card);
                }
            }
        }
    }

    public void takeDamage(int damage) {
        this.nexusLife -= damage;
    }

    public int getNexusLife() {
        return this.nexusLife;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setBoard(Board newBoard) {
        this.board = newBoard;
    }

    public Card selectCard() {
        Card nextCard;
        int numCard = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número da carta que deseja jogar, ou -1 se deseja passar o turno: ");
        for (Card card : hand) {
            System.out.println(numCard + " - " + card.getName());
            numCard++;
        }
        System.out.println();
        numCard = sc.nextInt();
        if (numCard == -1) {
            nextCard = null;
        }
        else {
            nextCard = hand.get(numCard);
        }
        sc.close();

        return nextCard;
    }

    public void playCard(Card nextCard) {
        nextCard.playCard(board);
    }

    public void updateMana() {
        spellMana = spellMana + currentMana;
        if (spellMana > 3) {
            spellMana = 3;
        }

        maxMana++;
        currentMana = maxMana;
    }

    public boolean spendMana(int n, boolean isSpell) {
        
        if (isSpell) {
            if (currentMana + spellMana >= n) {
                if (spellMana >= n) {
                    spellMana -= n;
                }
                else {
                    n -= spellMana;
                    currentMana -= n;
                }
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (currentMana >= n) {
                currentMana -= n;
                return true;
            }
            else {
                return false;
            }
        }

        }

}
