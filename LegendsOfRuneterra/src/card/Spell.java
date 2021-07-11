package card;

import game.Board;

public class Spell extends Card{

    public Spell(String name, String description, int cost, Effect[] newEffects, Region region, String image) {
        super(name, description, cost, region, newEffects, image);
    }

    public boolean playCard(Board myBoard, Board opponentBoard) {
        if (myBoard.getPlayer().spendMana(cost, true)){
            for (Effect effect : effects){
                effect.checkTrigger(Trigger.PLAY, myBoard, opponentBoard, null);
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        String dados = String.format("%s | %d\n", name, cost);
        dados += description;
        dados += "\n";
        return dados;
    }

    public Spell makeCopy(){
        Effect[] newEffectList = new Effect[effects.size()];
        for (int i = 0; i < effects.size(); i++){
            try {
                newEffectList[i] = (Effect)effects.get(i).clone();
            }
            catch (CloneNotSupportedException e) {
                System.out.println("Uma carta não pôde ser clonada");
                e.printStackTrace();
            }
        }
        Spell newSpell = new Spell(name, description, cost, newEffectList, region, image);
        return newSpell;
    }

}
