package card.champion.noxus;

import org.omg.CORBA.TRANSACTION_MODE;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

// Simplificada
public class Leblanc extends Champion {

  private int damageDone;

  public Leblanc() {
    super("LeBlanc", "Eu subo de nível se causar 10 de dano.", 3, 2, 5, Region.NOXUS, "/assets/noxus/leblanc.png");
    traits.add(Trait.QUICK_ATTACK);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {

    System.out.println("Leblanc Evoluiu!");
    System.out.println("Leblanc - A rosa negra vai desabrochar novamente!!");

    this.basePower = 6;
    this.baseHealth = 3;
    this.currentHealth += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (damageDone >= 10) {
      return true;
    }
    return false;
  }

}
