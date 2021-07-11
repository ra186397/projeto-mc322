package card.champion.freljord;

import card.Effect;
import card.Region;
import card.Trait;
import card.Trigger;
import card.champion.Champion;
import javafx.scene.transform.Transform;

public class Anivia extends Champion {

  private Effect unevolvedAOEEffect;
  private Effect unevolvedNexusDamageEffect;
  private Effect evolveEffect;

  public Anivia(String name, String description, int cost, int baseHealth, int basePower, Region region, Effect[] newEffects, Trait[] traits) {
    super(name, description, cost, baseHealth, basePower, region, "/assets/freljord/anivia");

    unevolvedAOEEffect =  new Effect(20, 1, Trigger.ATTACK);
    unevolvedNexusDamageEffect = new Effect(11, 1, Trigger.ATTACK);
    evolveEffect = new Effect(13, Trigger.ENLIGHTENED);
    effects.add(evolveEffect);
    effects.add(unevolvedAOEEffect);
    effects.add(unevolvedNexusDamageEffect);
    effects.add(new Effect(21, Trigger.LAST_BREATH));
  }

  public void transform(){
    baseHealth = 1;
    basePower = 0;
    name = "Ovonivia";
    image = "/assets/freljord/ovonivia.png";//colocar a imagem do ovo
    for (Effect effect : effects){
      effects.remove(effect);
    }
    effects.add(evolveEffect);
  }

  @Override
  public void evolve() {
    System.out.println("Anivia Evoluiu!");
    System.out.println("Anivia - Eu trago a tempestade!!");

    effects.add(new Effect(20, 2, Trigger.ATTACK));
    effects.add(new Effect(11, 2, Trigger.ATTACK));
    effects.remove(evolveEffect);

    this.basePower = 3;
    this.baseHealth = 5;
    this.currentHealth += 1;
  }

  @Override
  public void checkEvolution() {//adicinar enlightened em round end
    if (name == "Ovonivia"){
      effects.add(new Effect(21, Trigger.LAST_BREATH));
      name = "Aninia";
    }
    else {
      effects.remove(unevolvedAOEEffect);
      effects.remove(unevolvedNexusDamageEffect);
    }
    image = "/assets/freljord/anivia-evolved.png";
    evolve();
  }

}
