package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

// Nao verificado
public class Darius extends Champion {

  private int nexusLife;

  public Darius() {
    super("Darius", "Subo de nível se o nexus inimigo tiver 10 ou menos de vida.", 6, 5, 6, Region.NOXUS, "/assets/noxus/darius.png");
    traits.add(Trait.TOUGH);
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

  }

}
