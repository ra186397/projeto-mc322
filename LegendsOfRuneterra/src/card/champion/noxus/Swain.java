package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

// simplificado
// Não verificado
public class Swain extends Champion {

  private int damageDone;

  public Swain(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Swain Evoluiu!");
    System.out.println("Swain - Vidinhas descartaveis, curve-se!!");

    this.basePower = 6;
    this.baseHealth = 6;
    this.currentHealth += 1;
    this.currentPower += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (damageDone >= 12) {
      return true;
    }
    return false;
  }

}
