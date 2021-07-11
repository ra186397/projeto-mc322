package card.champion.demacia;

import card.CaseEffects;
import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

public class Garen extends Champion {

  int evolutionCounter;

  public Garen() {
    super("Garen", "Subir de nível\n Golpeie duas vezes", 5, 5, 5, Region.DEMACIA, "/assets/demacia/garen.png");
    this.effects.add(new Effect(CaseEffects.EVOLUTION, Trigger.STRIKE));
    this.traits.add(Trait.REGENERATION);
    evolutionCounter = 0;
  }

  @Override
  public void evolve() {
    System.out.println("Garen Evoluiu!");
    System.out.println("Garen - Por demaciaaaa!!");

    image = "/assets/demacia/garen.png";

    this.effects.remove(0);
    this.traits.add(Trait.ELUSIVE);

    this.basePower = 6;
    this.baseHealth = 6;
    this.currentHealth += 1;
  }

  @Override
  public void checkEvolution(){
    evolutionCounter += 1;
    if (evolutionCounter >= 2){
      evolve();
    }
  }

}
