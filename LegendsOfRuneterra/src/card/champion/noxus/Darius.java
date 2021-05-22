package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Darius extends Champion {

  private int nexusLife;

  public Darius(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);
  }

  @Override
  public boolean checkEvolution() {
    if (nexusLife <= 10) {
      return true;
    }
    return false;
  }

  @Override
  public void evolve() {

    System.out.println("Darius Evoluiu!");
    System.out.println("Darius - Testemunhe minha verdadeira força!!");

    this.basePower = 10;
    this.baseHealth = 6;
    this.currentHealth += 1;
    this.currentPower += 4;

  }

}
