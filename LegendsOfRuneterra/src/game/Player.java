package game;

import java.util.ArrayList;

import card.Card;

public class Player {

    private ArrayList<Card> hand;
    private int maxMana;
    private int currentMana;
    private int spellMana;
    private int nexusLife;
    private Deck deck;

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

    public void spendMana(int n) {
        currentMana -= n;
    }

}
