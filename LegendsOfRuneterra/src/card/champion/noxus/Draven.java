package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Draven extends Champion {

  private int increasedPower;

  public Draven(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.increasedPower = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Draven Evoluiu!");
    System.out.println("Draven - A festa chegou!!");

    this.basePower = 4;
    this.baseHealth = 4;
    this.currentHealth += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (this.increasedPower >= 3) {
      return true;
    }
    return false;
  }

}
