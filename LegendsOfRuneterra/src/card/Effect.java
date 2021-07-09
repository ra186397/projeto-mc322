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

    private void applyEffect(game.Board myBoard, game.Board opponentBoard, Follower self_follower) {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0: // Dê +n/+m a todas as unidades aliadas evocadas.
            for (Follower follower : myBoard.getCards()) {
                follower.baseHealth += amount1;
                follower.basePower += amount2;//O false e se e temporario ou nao, false e permanente true e temporario
            }

            case 1: // Dê +n/+m a uma unidade aliada nessa rodada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                System.out.println("Escolha uma unidade aliada.");
                //printar a board
                if (myBoard.getPlayer().isHuman()){
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).addTempBuff(amount1, amount2);
            }

            case 2: // Se a carta destruir uma unidade do inimigo nessa rodada, compra uma carta específica do seu deck.
            myBoard.getPlayer().drawCard(1, cardName);//colocar o efeito especificamente no relatório.

            case 3: // Cure inteiramente uma unidade aliada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                System.out.println("Cure inteiramente uma unidade aliada.");
                //printar a board
                if (myBoard.getPlayer().isHuman()){
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).heal(0, true);
            }

            case 4: // Dobre o ataque e defesa de uma unidade aliada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                System.out.println("Escolha uma unidade aliada.");
                //printar a board
                if (myBoard.getPlayer().isHuman()){
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).baseHealth = 2 * myBoard.getCards().get(ally).baseHealth;
                myBoard.getCards().get(ally).currentHealth = 2 * myBoard.getCards().get(ally).currentHealth;
                myBoard.getCards().get(ally).basePower = 2 * myBoard.getCards().get(ally).basePower;
            }

            case 5: // Escolha um aliado e um oponente para um combate imediato.
            if (myBoard.getCards().isEmpty() || opponentBoard.getCards().isEmpty()){
                System.out.println("Não há alvos válidos.");
            }
            else {
                System.out.println("Escolha uma unidade aliada.");
                if (myBoard.getPlayer().isHuman()){
                    ally = scan.nextInt();
                    //printar a board
                    System.out.println("Escolha uma unidade inimiga.");
                    myBoard.getCards().get(ally).strike(opponentBoard.getCards().get(scan.nextInt()), myBoard, opponentBoard);
                }
                else {
                    myBoard.getCards().get(myBoard.getPlayer().getRandomResult(myBoard.getCards().size())).strike(opponentBoard.getCards().get(myBoard.getPlayer().getRandomResult(opponentBoard.getCards().size())), myBoard, opponentBoard);
                }
            }

            case 6: // Uma unidade evocada ataca o nexus do adversário.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade aliada.");
                    //printar a board
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).strike(opponentBoard.getPlayer(), myBoard);
            }

            case 7: // Um aliado atacante golpeia todos os oponentes defensores.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                System.out.println("Escolha uma unidade aliada.");
                //printar a board
                if (myBoard.getPlayer().isHuman()){
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                };
                for (Follower follower : opponentBoard.getCards()) {
                    myBoard.getCards().get(ally).strike(follower, myBoard, opponentBoard);
                }
            }

            case 8: // Ao ser destruído, você ganha uma carta.
            myBoard.getPlayer().drawCard(1);

            case 9: // Altera o poder de uma unidade para 0 nesta rodada.
            if (myBoard.getCards().isEmpty() && opponentBoard.getCards().isEmpty()){
                System.out.println("Não há nenhum alvo válido.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    //printar a board
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).temporaryPower = 0;
            }

            case 10: // Cria uma barreira que anula o próximo dano que uma unidade aliada levaria. Dura uma rodada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    //printar a board
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).addTrait(Trait.BARRIER);
            }

            case 11: //Golpeia o nexus do adversário para n pontos de dano.
            opponentBoard.getPlayer().takeDamage(amount1);

            case 12://efeito de buff temporário
            self_follower.buff(-amount1, -amount2);
            self_follower.getEffects().remove(this);


        }
        scan.close();
    }

    public void checkTrigger(Trigger occurredTrigger, Board myBoard, Board opponentBoard, Follower follower){
        if (trigger == occurredTrigger){
            applyEffect(myBoard, opponentBoard, follower);
        }
    }
}
