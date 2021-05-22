package card;

import java.util.Scanner;

public class Effect {
    
    int effect;
    int amount1;
    int amount2;
    String cardName;

    public void applyEffect() {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0:
            if (cardJustPlayed == 1){
                changeStats(wholeBoard, amount1, amount2, false);//O false e se e temporario ou nao, false e permanente true e temporario
            }

            case 1:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                changeStats(scan.nextInt(), amount1, amount2, true);
            }

            case 2:
            if (cardKilledUnit == 1){
                hand.addCard(cardName);
            }

            case 3:
            if (cardJustPlayed == 1){
                System.out.println("Cure inteiramente uma unidade aliada");
                //printar a board
                board.curar(scan.nextInt(), card.baseHealth);
            }

            case 4:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                changeStats(scan.nextInt(), card.baseHealth, card.currentPower, false);
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
                damageNexus(scan.nextInt().currentPower);
            }

            case 7:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                int ally = scan.nextInt();
                for (int unit : opositeBoard.size) {
                    attack(ally, unit);
                }
            }

            case 8:
            if (cardDied == 1){
                drawCards(1);
            }

            case 9:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                changeStats(scan.nextInt(), 0, card.currentPower, true);
            }

            case 10:
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                giveTrait(scan.nextInt(), barrier /*deve ser um numero mas eu nao sei qual e*/, true);//true nesse caso tambem significa que e temporario
            }

            case 11:
            if (cardJustPlayed == 1){
                damageNexus(amount1);
            }
        }
    }
}
