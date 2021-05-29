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
    private Color playerColor;

    public Player(Deck deck, Color playerColor) {
        this.hand = new ArrayList<Card>();
        this.maxMana = 0;
        this.currentMana = 0;
        this.spellMana = 0;
        this.nexusLife = 20;
        this.deck = deck;
        this.playerColor = playerColor;
    }

    public void drawCard(int n) {
        for (int i = 0; i < n; i++) {
            deck 
        }
    }

}
