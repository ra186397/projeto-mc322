package card;

import java.util.ArrayList;

import game.Board;

public abstract class Card {

    protected String name;
    protected String description;
    protected int cost;
    protected Region region;
    protected ArrayList<Effect> effects;
    protected String image;

    public Card(String name, String description, int cost, Region region, Effect[] newEffects, String image) { // Carta com efeito
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.region = region;
        this.effects = new ArrayList<Effect>();
        for (int i = 0; i < newEffects.length; i++) {
            this.effects.add(newEffects[i]);
        }
        this.image = image;
    }

    public Card(String name, String description, int cost, Region region, String image) { // Carta sem efeito
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.region = region;
        this.effects = new ArrayList<Effect>();
        this.image = image;

    }

    public abstract boolean playCard(Board myBoard, Board opponentBoard);

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

}