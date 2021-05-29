package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Braum extends Champion {

  private int increasedPower;

  public Braum(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.increasedPower = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Braum Evoluiu!");
    System.out.println("Braum - Coração é o musculo mais forte!!");

    this.basePower = 1;
    this.baseHealth = 6;
    this.currentHealth += 1;
    this.currentPower += 1;
  }

  @Override
  public boolean checkEvolution() {
    if (this.increasedPower >= 3) {
      return true;
    }
    return false;
  }

}
