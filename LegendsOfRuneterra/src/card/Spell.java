package card;

import java.util.ArrayList;

public class Spell extends Card{
    
    String name;
    String description;
    int cost;
    ArrayList<Effect> effects;
    Region region;


    public Spell(String name, String description, int cost, Effect[] newEffects, Region region) {
        super(name, description, cost, region, newEffects);
    }

}
