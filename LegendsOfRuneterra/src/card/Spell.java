package card;

import java.util.ArrayList;

import game.Board;

public class Spell extends Card{
    
    String name;
    String description;
    int cost;
    ArrayList<Effect> effects;
    Region region;


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
