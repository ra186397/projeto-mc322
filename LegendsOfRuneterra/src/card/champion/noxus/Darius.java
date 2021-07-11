package card.champion.noxus;

import card.CaseEffects;
import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;

// Nao verificado
public class Darius extends Champion {

  private int evolutionCounter;

  public Darius() {
    super("Darius", "Subo de nível se o nexus inimigo tiver 10 ou menos de vida.", 6, 5, 6, Region.NOXUS, "/assets/noxus/darius.png");
    effects.add(new Effect(CaseEffects.EVOLUTION, Trigger.NEXUS_STRIKE));
    traits.add(Trait.TOUGH);

    evolutionCounter = 0;
  }
  
  @Override
  public void evolve() {
    
    System.out.println("Darius Evoluiu!");
    System.out.println("Darius - Testemunhe minha verdadeira força!!");

    effects.remove(0);
    effects.add(new Effect(CaseEffects.DAMAGE_NEXUS, 3, Trigger.ATTACK));
    
    this.basePower = 10;
    this.baseHealth = 6;
    this.currentHealth += 1;
    
  }

  @Override
  public void checkEvolution() {
    evolutionCounter += 1;
    if (evolutionCounter >= 2){
      evolve();
    }
  }
  
}
