package card;
import java.util.Scanner;

import game.Board;
import game.Player;
import card.Follower;
public class Effect {
    
    Trigger trigger;
    int effect;
    int amount1;
    int amount2;
    int ally;
    String cardName;

    public Effect(int effect, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
    }

    public Effect(int effect, int amount1, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.amount1 = amount1;
    }

    public Effect(int effect, int amount1, int amount2, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.amount1 = amount1;
        this.amount2 = amount2;
    }

    public Effect(int effect, String cardName, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.cardName = cardName;
    }

    private void applyEffect(game.Board myBoard, game.Board opponentBoard) {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0: // Dê +n/+m a todas as unidades aliadas evocadas.
            for (Follower follower : myBoard.getCards()) {
                follower.baseHealth += amount1;
                follower.basePower += amount2;//O false e se e temporario ou nao, false e permanente true e temporario
            }

            case 1: // Dê +n/+m a uma unidade aliada nessa rodada.
            System.out.println("Escolha uma unidade aliada");
            //printar a board
            ally = scan.nextInt();
            myBoard.getCards().get(ally).temporaryHealth = amount1;
            myBoard.getCards().get(ally).temporaryHealth = amount2;

            case 2: // Se a carta destruir uma unidade do inimigo nessa rodada, é colocada uma nova carta de uma unidade específica na sua mão.
            myBoard.getPlayer().drawCard(1, cardName);

            case 3: // Cure inteiramente uma unidade aliada.
            System.out.println("Cure inteiramente uma unidade aliada");
            //printar a board
            myBoard.getCards().get(scan.nextInt()).currentHealth = myBoard.getCards().get(scan.nextInt()).baseHealth;

            case 4: // Dobre o ataque e defesa de uma unidade aliada.
            System.out.println("Escolha uma unidade aliada");
            //printar a board
            ally = scan.nextInt();
            myBoard.getCards().get(ally).baseHealth = 2 * myBoard.getCards().get(scan.nextInt()).baseHealth;
            myBoard.getCards().get(ally).currentHealth = 2 * myBoard.getCards().get(scan.nextInt()).currentHealth;
            myBoard.getCards().get(ally).basePower = 2 * myBoard.getCards().get(scan.nextInt()).basePower;

            case 5: // Escolha um aliado e um oponente para um combate imediato.
            System.out.println("Escolha uma unidade aliada");
            ally = scan.nextInt();
            //printar a board
            System.out.println("Escolha uma unidade inimiga");
            myBoard.getCards().get(ally).strike(opponentBoard.getCards().get(scan.nextInt()));

            case 6: // Uma unidade evocada ataca o nexus do adversário.
            System.out.println("Escolha uma unidade aliada");
            //printar a board
            myBoard.getCards().get(scan.nextInt()).strike(opponentBoard.getPlayer());

            case 7: // Um aliado atacante golpeia todos os oponentes defensores.
            System.out.println("Escolha uma unidade aliada");
            //printar a board
            ally = scan.nextInt();
            for (Follower follower : opponentBoard.getCards()) {
                myBoard.getCards().get(ally).strike(follower);
            }

            case 8: // Ao ser destruído, você ganha uma carta.
            myBoard.getPlayer().drawCard(1);

            case 9: // Altera o poder de uma unidade para 0 nesta rodada.
            System.out.println("Escolha uma unidade");
            //printar a board
            myBoard.getCards().get(scan.nextInt()).temporaryPower = 0;

            case 10: // Cria uma barreira que anula o próximo dano que uma unidade aliada levaria. Dura uma rodada.
            System.out.println("Escolha uma unidade");
            //printar a board
            myBoard.getCards().get(scan.nextInt()).addTrait(Trait.BARRIER);

            case 11: //Golpeia o nexus do adversário para n pontos de dano.
            opponentBoard.getPlayer().takeDamage(amount1);
        }
        scan.close();
    }

    public void checkTrigger(Trigger occurredTrigger, Board myBoard, Board opponentBoard){
        if (trigger == occurredTrigger){
            applyEffect(myBoard, opponentBoard);
        }
    }
}
