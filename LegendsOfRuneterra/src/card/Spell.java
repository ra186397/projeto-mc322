package card;

import java.util.ArrayList;

import game.Board;

public class Spell extends Card{
    
    String name;
    String description;
    int cost;
    ArrayList<Effect> effects;
    Region region;


    public Spell(String name, String description, int cost, Effect[] newEffects, Region region) {
        super(name, description, cost, region, newEffects);
    }

    public void playCard(Board myBoard, Board opponentBoard) {
        for (Effect effect : effects){
            effect.checkTrigger(Trigger.PLAY, myBoard, opponentBoard);
        }
    }

}
