package card.champion.noxus;

import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

// Simplificada
public class Leblanc extends Champion {

  private int evolutionCounter;

  public Leblanc() {
    super("LeBlanc", "Eu subo de nível se causar 10 de dano.", 3, 2, 5, Region.NOXUS, "/assets/noxus/leblanc.png");
    traits.add(Trait.QUICK_ATTACK);
    effects.add(new Effect(13, Trigger.STRIKE));

    this.evolutionCounter = 0;
  }

  @Override
  public void evolve() {

    System.out.println("Leblanc Evoluiu!");
    System.out.println("Leblanc - A rosa negra vai desabrochar novamente!!");

    traits.add(Trait.ELUSIVE);
    effects.remove(0);

    this.basePower = 6;
    this.baseHealth = 3;
    this.currentHealth += 1;

  }

  @Override
  public void checkEvolution() {
<<<<<<< HEAD
    if (damageDone >= 10) {
      return true;
=======
    if (temporaryPower >= 6){
      evolutionCounter += 1;
      if (evolutionCounter >= 2){
        evolve();
      }
>>>>>>> 7f4f2c3d967daf85e11e1f17f0f04994ebfb59df
    }
  }

}
