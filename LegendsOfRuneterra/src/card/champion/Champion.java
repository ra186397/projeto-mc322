package card.champion;

import card.Effect;
import card.Follower;
import card.Region;
import card.Trait;

public abstract class Champion extends Follower {

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects) {
    super(name, description, cost, baseHealth, basePower, region, newEffects);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, traits);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region) {
    super(name, description, cost, baseHealth, basePower, region);
  }

  public abstract void evolve();

  public abstract boolean checkEvolution();

}
