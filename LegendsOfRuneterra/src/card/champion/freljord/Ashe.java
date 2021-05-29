package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Ashe extends Champion {

  private int damageDone;

  public Ashe(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Ashe Evoluiu!");
    System.out.println("Ashe - Eu unirei Freljord!!");

    this.basePower = 6;
    this.baseHealth = 4;
    this.currentHealth += 1;
    this.currentPower += 1;
  }

  @Override
  public boolean checkEvolution() {
    if (this.damageDone >= 12) {
      return true;
    }
    return false;
  }

}
