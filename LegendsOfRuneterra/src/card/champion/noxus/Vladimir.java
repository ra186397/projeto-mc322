package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

// SIMPLIFICADO
// Não verificado
public class Vladimir extends Champion {

  private int deadEnemies;

  public Vladimir(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.deadEnemies = 0;
  }

  @Override
  public void evolve() {

    System.out.println("Vladimir Evoluiu!");
    System.out.println("Vladimir - Eu sou o senhor e o mestre!!");

    this.basePower = 6;
    this.baseHealth = 6;
    this.currentHealth += 1;
    this.currentPower += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (deadEnemies >= 5) {
      return true;
    }
    return false;
  }

}
