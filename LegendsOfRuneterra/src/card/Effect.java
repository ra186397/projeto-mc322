package card;
import java.util.Scanner;

import game.Board;

public class Effect {
    
    int effect;
    int amount1;
    int amount2;
    String cardName;

    public Effect(int effect){
        this.effect = effect;
    }

    public Effect(int effect, int amount1){
        this.effect = effect;
        this.amount1 = amount1;
    }

    public Effect(int effect, int amount1, int amount2){
        this.effect = effect;
        this.amount1 = amount1;
        this.amount2 = amount2;
    }

    public Effect(int effect, String cardName){
        this.effect = effect;
        this.cardName = cardName;
    }

    public void applyEffect(game.Board myBoard, game.Board opponentBoard) {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0:
            if (cardJustPlayed == 1){
                for (Follower follower : myBoard.getCards()) {
                    follower.baseHealth += amount1;
                    follower.basePower += amount2;//O false e se e temporario ou nao, false e permanente true e temporario
                }
            }

            case 1:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                int card = scan.nextInt();
                myBoard.getCards().get(card).temporaryHealth = amount1;
                myBoard.getCards().get(card).temporaryHealth = amount2;
            }

            case 2:
            if (cardKilledUnit == 1){
                myBoard.getPlayer().drawCard(1, cardName);
            }

            case 3:
            if (cardJustPlayed == 1){
                System.out.println("Cure inteiramente uma unidade aliada");
                //printar a board
                myBoard.getCards().get(scan.nextInt()).currentHealth = myBoard.getCards().get(scan.nextInt()).baseHealth;
            }

            case 4:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                int card = scan.nextInt();
                myBoard.getCards().get(card).baseHealth = 2 * myBoard.getCards().get(scan.nextInt()).baseHealth;
                myBoard.getCards().get(card).currentHealth = 2 * myBoard.getCards().get(scan.nextInt()).currentHealth;
                myBoard.getCards().get(card).basePower = 2 * myBoard.getCards().get(scan.nextInt()).basePower;
            }

            case 5:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                int ally = scan.nextInt();
                //printar a board
                System.out.println("Escolha uma unidade inimiga");
                fightIdk(ally, scan.nextInt());
            }

            case 6:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                damageNexus(scan.nextInt().basePower);
            }

            case 7:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                int ally = scan.nextInt();
                for (Follower follower : opponentBoard.getCards()) {
                    attack(ally, follower);
                }
            }

            case 8:
            if (myBoard.getCards().get(scan.nextInt()).currentHealth <= 0){
                myBoard.getPlayer().drawCard(1);
            }

            case 9:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                myBoard.getCards().get(scan.nextInt()).temporaryPower = 0;
            }

            case 10:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                myBoard.getCards().get(scan.nextInt()).addTrait(Trait.BARRIER);
            }

            case 11:
            if (cardJustPlayed == 1){
                damageNexus(amount1);
            }
        }
    }
}
