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
    protected int furyPower;
    protected int furyHealth;

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, //Tem traço e efeito mas não tem fúria
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

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Não possui traços
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

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Não possui efeitos nem fúria
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

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region) { // Não possui traços nem efeitos

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, //Tem traço (um deles sendo fúria) e efeito.
            Effect[] newEffects, Trait[] traits, int furyPower, int furyHealth) {

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        this.furyHealth = furyHealth;
        this.furyPower = furyPower;
        for (Trait trait : traits) {
            this.traits.add(trait);
        }
        for (Effect effect : effects) {
            this.effects.add(effect);
        }
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Possui apenas traços, um deles sendo fúria.
            Trait[] traits, int furyPower, int furyHealth) {

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        this.furyHealth = furyHealth;
        this.furyPower = furyPower;
        for (Trait trait : traits) {
            this.traits.add(trait);
        }
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
