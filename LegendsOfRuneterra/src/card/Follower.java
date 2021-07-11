package card;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class Follower extends Card {

    protected int baseHealth;//Vida máxima base da carta
    protected int currentHealth;//Vida contando dano atual da carta
    protected int temporaryHealth;//Vida máxima atual da carta
    protected int basePower;//Poder base da carta
    protected int temporaryPower;//Poder atual da carta
    protected ArrayList<Trait> traits;
    protected int furyPower;
    protected int furyHealth;

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Tem traço e efeito mas não tem fúria
            Effect[] newEffects, Trait[] newTraits, String image) {

        super(name, description, cost, region, newEffects, image);
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
        this.furyHealth = 0;
        this.furyPower = 0;
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region,
            Effect[] newEffects, String image) { // Não possui traços

        super(name, description, cost, region, newEffects, image);
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
            Trait[] newTraits, String image) {

        super(name, description, cost, region, image);
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

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region,
            String image) { // Não possui traços nem efeitos

        super(name, description, cost, region, image);
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.temporaryHealth = baseHealth;
        this.basePower = basePower;
        this.temporaryPower = basePower;
        this.traits = new ArrayList<Trait>();
    }

    public Follower(String name, String description, int cost, int basePower, int baseHealth, Region region, // Tem traço (um deles sendo fúria) e efeito.
            Effect[] newEffects, Trait[] newTraits, int furyPower, int furyHealth, String image) {

        super(name, description, cost, region, newEffects, image);
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
            Trait[] newTraits, int furyPower, int furyHealth, String image) {

        super(name, description, cost, region, image);
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

        traits.add(trait);
        if (trait == Trait.BARRIER){
            effects.add(new Effect(18, Trigger.ROUND_END));
        }
    }

    public boolean playCard(Board myBoard, Board opponentBoard) {
        return myBoard.addCard(this, opponentBoard);
    }

    public void strike(Player player, Board myBoard) {
        if (this.temporaryPower > 0) {
            for (Effect effect : effects) {
                effect.checkTrigger(Trigger.STRIKE, myBoard, player.getBoard(), this, 0);
            }
            player.takeDamage(this.temporaryPower);
        }
    }

    public void strike(Follower defender, Board myBoard, Board opponentBoard) {
        if (this.temporaryPower > 0) {
            for (Effect effect : effects) {
                effect.checkTrigger(Trigger.STRIKE, myBoard, opponentBoard, this, 0);
            }
            defender.takeDamage(this.temporaryPower);
            if (defender.getCurrentHealth() <= 0) {
                for (Effect effect : effects) {
                    effect.checkTrigger(Trigger.DESTROY_OPPONENT, myBoard, opponentBoard, this, 0);
                }
            }
        }
    }

    public void takeDamage(int damage) {
        if (!hasTrait(Trait.BARRIER)) {
            if (hasTrait(Trait.TOUGH)){
                damage -= 1;
            }
            this.currentHealth -= damage;
        }
        else {
            traits.remove(Trait.BARRIER);
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getTemporaryPower() {
        return this.temporaryPower;
    }

    public ArrayList<Trait> getTraits() {
        return traits;
    }

    public int getBaseHealth() {
        return this.baseHealth;
    }

    public int getBasePower() {
        return this.basePower;
    }

    public boolean hasTrait(Trait traco) {
        return traits.contains(traco);
    }

    public void triggerFury() {
        basePower += furyPower;
        baseHealth += furyHealth;
    }

    @Override
    public String toString() {
        String dados = String.format("[%d|%d|%d] %s | Traços: ", cost, temporaryPower, temporaryHealth, name);
        for (Trait trait : traits) {
            dados += trait;
            dados += " ";
        }
        dados += "\n" + description + "\n";
        return dados;
    }

    public void heal(int amount, boolean full) {
        if (currentHealth + amount >= temporaryHealth || full == true) {
            currentHealth = temporaryHealth;
        } else {
            currentHealth += amount;
        }
    }

    public void buff(int powerBuff, int healthBuff) {
        temporaryPower += powerBuff;
        temporaryHealth += healthBuff;
    }

    public void addTempBuff(int powerBuff, int healthBuff) {
        buff(powerBuff, healthBuff);
        effects.add(new Effect(12, powerBuff, healthBuff, Trigger.ROUND_END));
    }

    public Follower makeCopy(){
        Effect[] newEffectList = new Effect[effects.size()];
        for (int i = 0; i < effects.size(); i++){
            try {
                newEffectList[i] = (Effect)effects.get(i).clone();
            }
            catch (CloneNotSupportedException e) {
                System.out.println("Uma carta não pôde ser clonada");
                e.printStackTrace();
            }
        }
        Trait[] newTraitList = new Trait[traits.size()];
        for (int i = 0; i < traits.size(); i++){
            newTraitList[i] = traits.get(i);
        }
        Follower newFollower = new Follower(name, description, cost, basePower, baseHealth, region, newEffectList, newTraitList, furyPower, furyHealth, image);
        return newFollower;
    }
}
