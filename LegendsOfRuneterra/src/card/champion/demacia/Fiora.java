package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Fiora extends Champion {

  private int deadEnemies;

  public Fiora(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.deadEnemies = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Fiora Evoluiu!");
    System.out.println("Fiora - Anseio por um adversário a altura!!");

    this.basePower = 4;
    this.baseHealth = 3;
    this.currentHealth += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (this.deadEnemies >= 2) {
      return true;
    }
    return false;
  }

}
