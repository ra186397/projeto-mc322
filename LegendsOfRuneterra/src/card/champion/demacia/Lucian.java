package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Lucian extends Champion {

  private int increasedPower;

  public Lucian() {
    super("Lucian", "Subir de nível\n ", 2, 2, 3, Region.DEMACIA);
    this.traits.add(Trait.DOUBLE_ATTACK);

    this.increasedPower = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Lucian Evoluiu!");
    System.out.println("Lucian - Todo mundo morre, alguns só precisam de uma ajudinha!!");

    this.basePower = 4;
    this.baseHealth = 3;
    this.currentHealth += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (this.increasedPower >= 2) {
      return true;
    }
    return false;
  }

}
