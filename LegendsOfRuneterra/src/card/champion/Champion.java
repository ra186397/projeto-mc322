package card.champion;

import card.Effect;
import card.Follower;
import card.Region;
import card.Trait;

public abstract class Champion extends Follower {

  public Champion(String name, String description, int cost, int basePower, int baseHealth, Region region, String image) {
    super(name, description, cost, basePower, baseHealth, region, image);
  }

  public abstract void evolve();

  public abstract boolean checkEvolution();

}
