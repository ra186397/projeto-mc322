package card.champion.demacia;

import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

public class Garen extends Champion {

  public Garen() {
    super("Garen", "Subir de nível\n Golpeie duas vezes", 5, 5, 5, Region.DEMACIA, "/assets/demacia/garen.png");
    this.traits.add(Trait.REGENERATION);
    this.effects.add(new Effect(13, 0, Trigger.STRIKE));
  }

  @Override
  public void evolve() {
    System.out.println("Garen Evoluiu!");
    System.out.println("Garen - Por demaciaaaa!!");

    this.effects.remove(0);
    this.traits.add(Trait.ELUSIVE);

    this.basePower = 6;
    this.baseHealth = 6;
    this.currentHealth += 1;
  }

  @Override
  public boolean checkEvolution(){
    return true;//não sendo utilizado, remover da superclasse Champion se não for usada depois.
  }

}
