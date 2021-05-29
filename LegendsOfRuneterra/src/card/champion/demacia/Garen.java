package card.champion.demacia;

import java.util.ArrayList;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Garen extends Champion {

  private int numAttacks;

  public Garen() {
    super("Garen", "Subir de nível\n Golpeie duas vezes", 5, 5, 5, Region.DEMACIA);
    this.traits.add(Trait.REGENERATION);
    this.numAttacks = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Garen Evoluiu!");
    System.out.println("Garen - Por demaciaaaa!!");

    this.traits.add(Trait.ELUSIVE);

    this.basePower = 6;
    this.baseHealth = 6;
    this.currentHealth += 1;
  }

  @Override
  public boolean checkEvolution() {
    if (this.numAttacks >= 2) {
      return true;
    }
    return false;
  }

}
