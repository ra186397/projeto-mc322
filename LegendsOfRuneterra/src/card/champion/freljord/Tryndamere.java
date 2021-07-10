package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Tryndamere extends Champion {

  public Tryndamere() {
    super("Tryndamere", "", 8, 8, 4, Region.FRELJORD, "/assets/freljord/tryndamere.png");
    traits.add(Trait.FURY);
    furyHealth = 0;
    furyPower = 2;

  }

  @Override
  public void evolve() {
    System.out.println("Tryndamere Evoluiu!");
    System.out.println("Tryndareme - Meu braço direito é ainda mais forte que o meu esquerdo!!");

    this.basePower += 1;
    this.baseHealth += 5;
    this.currentHealth = 9;

  }

  @Override
  public void checkEvolution() {
    if (this.basePower >= 1) {
      baseHealth++;
    }
    basePower++;
  }

}
