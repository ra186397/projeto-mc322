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
    private Region region1;
    private Region region2;

    public Deck (int numCards, Region region1, Region region2) {
        this.numCards = numCards;
        this.region1 = region1;
        this.region2 = region2;
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card newCard) {
        if (Collections.frequency(cards, newCard) == 3) {
            System.out.println("Você já tem 3 cópias dessa carta no seu deck!");
        } 
        else {
            cards.add(newCard);
        }
    }
}