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

}
