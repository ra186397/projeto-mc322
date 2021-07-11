package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;
import javafx.scene.transform.Transform;

public class Anivia extends Champion {

  private int damageDone;
  private Effect transformEffect;

  public Anivia(String name, String description, int cost, int baseHealth, int basePower, Region region,
      Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, newEffects, traits);

    effects.add(new Effect(20, 1, Trigger.ATTACK)); 
    transformEffect = new Effect(21, Trigger.LAST_BREATH);
    effects.add(transformEffect);

    this.damageDone = 0;
  }

  public void transform(){
    baseHealth = 1;
    basePower = 0;
    name = "Ovonivia";
    image = "ovo";//colocar a imagem do ovo
    for (Effect effect : effects){
      effects.remove(effect);
    }
  }

  @Override
  public void evolve() {
    System.out.println("Anivia Evoluiu!");
    System.out.println("Anivia - Eu trago a tempestade!!");

    this.basePower = 3;
    this.baseHealth = 5;
    this.currentHealth += 1;
  }

  @Override
  public void checkEvolution() {//adicinar enlightened em round end
    if (name == "Ovonivia"){
      effects.add(transformEffect);
      name = "Aninia";
      image = "ovo";
      evolve();
    }
    else {
      effects.remove
    }
  }

}
