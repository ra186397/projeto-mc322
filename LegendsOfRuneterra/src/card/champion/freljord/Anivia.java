package card.champion.freljord;

import card.CaseEffects;
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

  public Anivia() {
    super("Anivia", "Quando eu atacar, cause 1 de dano ao nexus e aos inimigos. Eu subo de nível quando você tiver 10 gemas de mana.", 6, 4, 2, Region.FRELJORD, "/assets/freljord/anivia.png");

    unevolvedAOEEffect =  new Effect(CaseEffects.OPPONENT_AOE, 1, Trigger.ATTACK);
    unevolvedNexusDamageEffect = new Effect(CaseEffects.DAMAGE_NEXUS, 1, Trigger.ATTACK);
    evolveEffect = new Effect(CaseEffects.EVOLUTION, Trigger.ENLIGHTENED);
    effects.add(evolveEffect);
    effects.add(unevolvedAOEEffect);
    effects.add(unevolvedNexusDamageEffect);
    effects.add(new Effect(CaseEffects.ANIVIA_TRANSFORMATION, Trigger.LAST_BREATH));
  }

  public void transform(){
    baseHealth = 1;
    basePower = 0;
    name = "Ovonivia";
    image = "/assets/freljord/ovonivia.png";
    for (Effect effect : effects){
      effects.remove(effect);
    }
    effects.add(evolveEffect);
  }

  @Override
  public void evolve() {
    System.out.println("Anivia Evoluiu!");
    System.out.println("Anivia - Eu trago a tempestade!!");

    effects.add(new Effect(CaseEffects.OPPONENT_AOE, 2, Trigger.ATTACK));
    effects.add(new Effect(CaseEffects.DAMAGE_NEXUS, 2, Trigger.ATTACK));
    effects.remove(evolveEffect);

    this.basePower = 3;
    this.baseHealth = 5;
    this.currentHealth += 1;
  }

  @Override
  public void checkEvolution() {
    if (name == "Ovonivia"){
      effects.add(new Effect(CaseEffects.ANIVIA_TRANSFORMATION, Trigger.LAST_BREATH));
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
