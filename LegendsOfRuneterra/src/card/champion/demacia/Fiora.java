package card.champion.demacia;

import card.Region;
import card.champion.Champion;

public class Fiora extends Champion {

  private int deadEnemies;

  public Fiora() {
    super("Fiora", "Subir de nível\n Elimine 2 inimigos", 3, 2, 3, Region.DEMACIA, "/assets/demacia/garen.png");
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
