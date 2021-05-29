package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Anivia extends Champion {

  private int damageDone;

  public Anivia(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Anivia Evoluiu!");
    System.out.println("Anivia - Eu trago a tempestade!!");

    this.basePower = 3;
    this.baseHealth = 5;
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
