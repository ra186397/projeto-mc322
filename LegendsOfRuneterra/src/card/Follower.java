package card;

import java.util.ArrayList;

import game.Board;
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
            Effect[] newEffects, Trait[] newTraits) {

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Trait trait : newTraits) {
            this.traits.add(trait);
        }
        for (Effect effect : newEffects) {
            this.effects.add(effect);
        }
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, Effect[] newEffects) { //Não possui traços

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Effect effect : newEffects) {
            this.effects.add(effect);
        }
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Não possui efeitos nem fúria
            Trait[] newTraits) {

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        for (Trait trait : newTraits) {
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
            Effect[] newEffects, Trait[] newTraits, int furyPower, int furyHealth) {

        super(name, description, cost, region, newEffects);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        this.furyHealth = furyHealth;
        this.furyPower = furyPower;
        for (Trait trait : newTraits) {
            this.traits.add(trait);
        }
        for (Effect effect : newEffects) {
            this.effects.add(effect);
        }
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Possui apenas traços, um deles sendo fúria.
            Trait[] newTraits, int furyPower, int furyHealth) {

        super(name, description, cost, region);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
        this.furyHealth = furyHealth;
        this.furyPower = furyPower;
        for (Trait trait : newTraits) {
            this.traits.add(trait);
        }
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
    }

    public boolean playCard(Board myBoard, Board opponentBoard) {
        return myBoard.addCard(this, opponentBoard);
    }

    public void strike(Player player, Board myBoard) {
        if (this.temporaryPower > 0) {
            for (Effect effect : effects){
                effect.checkTrigger(Trigger.STRIKE, myBoard, player.getBoard());
            }
            player.takeDamage(this.temporaryPower);
        }
    }

    public void strike(Follower defender, Board myBoard, Board opponentBoard) {
        if (this.temporaryPower > 0) {
            for (Effect effect : effects){
                effect.checkTrigger(Trigger.STRIKE, myBoard, opponentBoard);
            }
            defender.takeDamage(this.temporaryPower);
            if (defender.getCurrentHealth() <= 0){
                for (Effect effect : effects){
                    effect.checkTrigger(Trigger.DESTROY_OPPONENT, myBoard, opponentBoard);
                }
            }
        }
    }

    public void takeDamage(int damage) {
        if (!hasTrait(Trait.BARRIER)){
            this.currentHealth -= damage;
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public boolean hasTrait(Trait traco){
        return traits.contains(traco);
    }

    public void triggerFury(){
        basePower += furyPower;
        baseHealth += furyHealth;
    }

    public void heal(int amount, boolean full){
        if (currentHealth + amount >= baseHealth || full == true){
            currentHealth = baseHealth;
        }
        else {
            currentHealth += amount;
        }
    }
}
