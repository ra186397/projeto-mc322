package card;

import java.util.ArrayList;

public abstract class Card {

    protected String name;
    protected String description;
    protected int cost;
    protected Region region;
    protected ArrayList<Effect> effects;

    public Card(String name, String description, int cost, Region region, Effect[] newEffects) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.region = region;
        this.effects = new ArrayList<Effect>();
        for (int i = 0; i < newEffects.length; i++) {
            this.effects.add(newEffects[i]);
        }
    }

}