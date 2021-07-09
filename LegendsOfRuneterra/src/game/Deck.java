package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
        if (region1 == region2){
            this.region2 = null;
        }
        else {
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
        if (Collections.frequency(cards, newCard) == 3) {
            System.out.println("Você já tem 3 cópias dessa carta no seu deck!");
        } else {
            cards.add(newCard);
        }
    }

    public String getName() {
        return name;
    }
}
