package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

// Simplificada
public class Leblanc extends Champion {

  private int damageDone;

  public Leblanc(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {

    System.out.println("Leblanc Evoluiu!");
    System.out.println("Leblanc - A rosa negrra vai desabrochar novamente!!");

    this.basePower = 6;
    this.baseHealth = 3;
    this.currentHealth += 1;
    this.currentPower += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (damageDone >= 15) {
      return true;
    }
    return false;
  }

}
