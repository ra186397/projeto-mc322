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

    public void applyEffect(Board myBoard, Board opponentBoard) {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0: // Dê +n/+m a todas as unidades aliadas evocadas.
            if (cardJustPlayed == 1){
                for (Card card : myBoard) {
                    changeStats(card, amount1, amount2, false);//O false e se e temporario ou nao, false e permanente true e temporario
                }
            }

            case 1: // Dê +n/+m a uma unidade aliada nessa rodada.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                changeStats(myBoard. scan.nextInt(), amount1, amount2, true);
            }

            case 2: // Se a carta destruir uma unidade do inimigo nessa rodada, é colocada uma nova carta de uma unidade específico na sua mão.
            if (cardKilledUnit == 1){
                hand.addCard(cardName);
            }

            case 3: // Cure inteiramente uma unidade aliada.
            if (cardJustPlayed == 1){
                System.out.println("Cure inteiramente uma unidade aliada");
                //printar a board
                board.curar(scan.nextInt(), card.baseHealth);
            }

            case 4: // Dobre o ataque e defesa de uma unidade aliada.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                changeStats(scan.nextInt(), card.baseHealth, card.currentPower, false);
            }

            case 5: // Escolha um aliado e um oponente para um combate imediato.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                int ally = scan.nextInt();
                //printar a board
                System.out.println("Escolha uma unidade inimiga");
                fightIdk(ally, scan.nextInt());
            }

            case 6: // Uma unidade evocada ataca o nexus do adversário.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                damageNexus(scan.nextInt().currentPower);
            }

            case 7: // Um aliado atacante golpeia todos os oponentes defensores.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade aliada");
                //printar a board
                int ally = scan.nextInt();
                for (int unit : opositeBoard.size) {
                    attack(ally, unit);
                }
            }

            case 8: // Ao ser destruído, você ganha uma carta.
            if (cardDied == 1){
                drawCards(1);
            }

            case 9: // Altera o poder de uma unidade para 0 nesta rodada.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                changeStats(scan.nextInt(), 0, card.currentPower, true);
            }

            case 10: // Cria uma barreira que anula o próximo dano que uma unidade aliada levaria. Dura uma rodada.
            if (cardJustPlayed == 1){
                System.out.println("Escolha uma unidade");
                //printar a board
                giveTrait(scan.nextInt(), barrier /*deve ser um numero mas eu nao sei qual e*/, true);//true nesse caso tambem significa que e temporario
            }

            case 11: //Golpeia o nexus do adversário para n pontos de dano.
            if (cardJustPlayed == 1){
                damageNexus(amount1);
            }
        }
    }
}
