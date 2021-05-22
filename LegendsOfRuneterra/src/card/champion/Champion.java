package card.champion;

import card.Follower;

public abstract class Champion extends Follower {

  public abstract void evolve();

  public abstract boolean checkEvolution();

}
