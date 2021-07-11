package card.champion.demacia;

import card.CaseEffects;
import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

public class Lucian extends Champion {

  private int evolutionCounter;

  public Lucian() {
    super("Lucian", "Subir de nível\n ", 2, 2, 3, Region.DEMACIA, "/assets/demacia/lucian.png");
    this.traits.add(Trait.QUICK_ATTACK);
    this.effects.add(new Effect(CaseEffects.EVOLUTION, Trigger.SEEN_ALLY_DIE));

    this.evolutionCounter = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Lucian Evoluiu!");
    System.out.println("Lucian - Todo mundo morre, alguns só precisam de uma ajudinha!!");

    image = "/assets/demacia/lucian.png";

    effects.remove(0);
    effects.add(new Effect(CaseEffects.SELF_BUFF, 1, 0, Trigger.SEEN_ALLY_DIE));
    traits.remove(Trait.QUICK_ATTACK);
    traits.add(Trait.DOUBLE_ATTACK);

    this.basePower = 4;
    this.baseHealth = 3;
    this.currentHealth += 1;

  }

  @Override
  public void checkEvolution() {
    evolutionCounter += 1;
    if (evolutionCounter >= 4){
      evolve();
    }
  }

}
