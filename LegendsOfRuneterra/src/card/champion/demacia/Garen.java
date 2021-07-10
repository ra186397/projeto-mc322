package card.champion.demacia;

import card.Region;
import card.Trait;
import card.champion.Champion;

public class Garen extends Champion {

  private int numStrikes;

  public Garen() {
    super("Garen", "Subir de nível\n Golpeie duas vezes", 5, 5, 5, Region.DEMACIA, "/assets/demacia/garen.png");
    this.traits.add(Trait.REGENERATION);
    this.numStrikes = 0;
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
    if (this.numStrikes >= 2) {
      return true;
    }
    return false;
  }

}
