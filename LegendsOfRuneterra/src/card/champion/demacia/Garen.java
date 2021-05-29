package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Garen extends Champion {

  private int numAttacks;

  public Garen(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.numAttacks = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Garen Evoluiu!");
    System.out.println("Garen - Por demaciaaaa!!");

    this.basePower = 5;
    this.baseHealth = 5;
    this.currentHealth += 1;
    this.currentPower += 1;
  }

  @Override
  public boolean checkEvolution() {
    if (this.numAttacks >= 2) {
      return true;
    }
    return false;
  }

}
