package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Lux extends Champion {

  private int damageDone;

  public Lux(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super("Lux", "Lul", 5, 5, 3, Region.DEMACIA, "/src/assets/demacia/lux.png");//adicionar imagem
    this.addTrait(Trait.BARRIER);

    this.damageDone = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Lux Evoluiu!");
    System.out.println("Lux - Escondi minha luz por tempo demais!!");

    this.basePower = 5;
    this.baseHealth = 6;
    this.currentHealth += 1;

  }

  @Override
  public boolean checkEvolution() {
    if (this.damageDone >= 12) {
      return true;
    }
    return false;
  }

}
