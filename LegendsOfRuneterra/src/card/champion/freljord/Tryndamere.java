package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Tryndamere extends Champion {

  private int deadEnemies;

  public Tryndamere(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.deadEnemies = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Tryndamere Evoluiu!");
    System.out.println("Tryndareme - Meu braço direito é ainda mais forte que o direito!!");

    this.basePower = 9;
    this.baseHealth = 9;
    this.currentHealth += 5;
    this.currentPower += 1;
  }

  @Override
  public boolean checkEvolution() {
    if (this.deadEnemies >= 1) {
      return true;
    }
    return false;
  }

}
