package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.champion.Champion;

public class Lux extends Champion {

  private int spentMana;

  public Lux(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super("Lux", "Lul", 5, 5, 3, Region.DEMACIA, "/src/assets/demacia/lux.png");
    this.addTrait(Trait.BARRIER);

    this.spentMana = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Lux Evoluiu!");
    System.out.println("Lux - Escondi minha luz por tempo demais!!");

    effects.remove(0);
    effects.add(new Effect)

    this.basePower = 5;
    this.baseHealth = 6;
    this.currentHealth += 1;

  }

  @Override
  public void checkEvolution() {
    spentMana += 1;
    if (spentMana >= 6){
      evolve();
    }
  }

}
