package game;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.System;

import card.Card;
import card.Region;

public class Deck {

    private int numCards;
    private ArrayList<Card> cards;
    private String name;
    private Region region1;
    private Region region2;

    public Deck(Region region1, Region region2, String name) {
        this.numCards = 0;
        this.region1 = region1;
        if (region1 == region2) {
            this.region2 = Region.NONE;
        } else {
            this.region2 = region2;
        }
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    public void shuffle() {

        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card newCard) {

        cards.add(newCard);

    }

    public String getName() {
        return name;
    }

    public Region getFirstRegion(){
        return region1;
    }

    public Region getSecondRegion(){
        return region2;
    }
}
