package card;

import java.util.ArrayList;
public class Follower extends Card{
    
    protected int baseHealth;
    protected int currentHealth;
    protected int basePower;
    protected int currentPower;
    protected ArrayList<Trait> traits;

    public Follower(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects, Trait[] traits) {
        
        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.basePower = basePower;
        this.currentPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Trait trait : traits) {
            this.traits.add(trait);
        }

    }

    


}
