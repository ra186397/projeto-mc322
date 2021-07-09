package card.champion;

import card.Effect;
import card.Follower;
import card.Region;
import card.Trait;

public abstract class Champion extends Follower {

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects, Trait[] traits, String image) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits, image);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects, String image) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, image);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, Trait[] traits, String image) {
    super(name, description, cost, baseHealth, basePower, region, traits, image);
  }

  public Champion(String name, String description, int cost, int baseHealth, int basePower, Region region, String image) {
    super(name, description, cost, baseHealth, basePower, region, image);
  }

  public abstract void evolve();

  public abstract boolean checkEvolution();

}
