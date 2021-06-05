package card;

import java.util.ArrayList;

import game.Player;

public class Follower extends Card {

    protected int baseHealth;
    protected int currentHealth;
    protected int temporaryHealth;
    protected int basePower;
    protected int temporaryPower;
    protected ArrayList<Trait> traits;

    public Follower(String name, String description, int cost, int baseHealth, int basePower, Region region,
            Effect[] newEffects, Trait[] traits) {

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Trait trait : traits) {
            this.traits.add(trait);
        }
        for (Effect effect : effects) {
            this.effects.add(effect);
        }

    }

    public Follower(String name, String description, int cost, int baseHealth, int basePower, Region region,
            Effect[] newEffects) {

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Effect effect : effects) {
            this.effects.add(effect);
        }

    }

    public Follower(String name, String description, int cost, int baseHealth, int basePower, Region region,
            Trait[] traits) {

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Trait trait : traits) {
            this.traits.add(trait);
        }

    }

    public Follower(String name, String description, int cost, int baseHealth, int basePower, Region region) {

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
    }

    public void strike(Player player) {
        if (this.temporaryPower > 0) {
            player.takeDamage(this.temporaryPower);
        }
    }

    public void strike(Follower defender) {
        if (this.temporaryPower > 0) {
            defender.takeDamage(this.temporaryPower);
        }
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }
}
