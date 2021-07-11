package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

public class Tryndamere extends Champion {

  public Tryndamere() {
    super("Tryndamere", "", 8, 8, 4, Region.FRELJORD, "/assets/freljord/tryndamere.png");
    traits.add(Trait.FURY);
    furyPower = 2;
    furyHealth = 0;
    effects.add(new Effect(13, Trigger.LAST_BREATH));

  }

  @Override
  public void evolve() {
    System.out.println("Tryndamere Evoluiu!");
    System.out.println("Tryndareme - Meu braço direito é ainda mais forte que o meu esquerdo!!");

    image = "/assets/freljord/tryndamere-evolved.png";

    furyPower = 3;

    this.basePower = 9;
    this.baseHealth = 9;
    this.currentHealth = 9;

  }

  @Override
  public void checkEvolution() {
    if (currentHealth <= 0){
      evolve();
    }
  }

}
