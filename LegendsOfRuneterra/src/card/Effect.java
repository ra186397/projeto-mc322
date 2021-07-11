package card;
import java.util.Scanner;

import game.Board;
import game.Player;
import card.Follower;
import card.champion.Champion;
import card.champion.freljord.Anivia;
public class Effect implements Cloneable{
    
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

    private void applyEffect(game.Board myBoard, game.Board opponentBoard, Follower selfFollower, int amount3) {
        Scanner scan = new Scanner(System.in);

        switch(effect) {
            case 0: // Dê +n/+m a todas as unidades aliadas evocadas.
            for (Follower follower : myBoard.getCards()) {
                follower.buff(amount1, amount2);//O false e se e temporario ou nao, false e permanente true e temporario
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
                myBoard.getCards().get(ally).buff(myBoard.getCards().get(ally).getTemporaryPower(), myBoard.getCards().get(ally).getCurrentHealth());
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

            case 7: // Um aliado golpeia todos os oponentes.
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
            if (opponentBoard.getCards().isEmpty()){
                System.out.println("Não há nenhum alvo válido.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    //printar a board
                    ally = scan.nextInt();
                }
                else {
                    ally = opponentBoard.getPlayer().getRandomResult(opponentBoard.getCards().size());
                }
                opponentBoard.getCards().get(ally).addTempBuff(-opponentBoard.getCards().get(ally).getTemporaryPower(), 0);
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
    
            case 12://Efeito de buff temporário
            selfFollower.buff(-amount1, -amount2);
            selfFollower.getEffects().remove(this);

            case 13://Evolução
            
            
            case 14: //Deixa o inimigo mais forte com 0 de poder nesta rodada.
            if (opponentBoard.getCards().size() != 0){
                Follower mostPowerful = opponentBoard.getCards().get(0);
                for (Follower follower : opponentBoard.getCards()){
                    if (follower.getTemporaryPower() > mostPowerful.getTemporaryPower()){
                        mostPowerful = follower;
                    }
                }
                mostPowerful.addTempBuff(-mostPowerful.getTemporaryPower(), 0);
            }
            
            case 15: //Da n de dano a todas as unidades.
            for (Follower opponent : opponentBoard.getCards()){
                opponent.takeDamage(amount1);
            }
            for (Follower ally : myBoard.getCards()){
                ally.takeDamage(amount1);
            }
            
            case 16://Da +n/+m a si mesmo.
            selfFollower.buff(amount1, amount2);

            case 17://Da +n/+m a todas as unidades aliadas nessa rodada.
            for (Follower ally : myBoard.getCards()){
                ally.addTempBuff(amount1, amount2);
            }

            case 18://Remove barreira de si mesmo
            if (selfFollower.hasTrait(Trait.BARRIER)){
                selfFollower.takeDamage(1);
            }

            case 19://Evolução com contagem TIRAR SE NÃO USADO
            amount1 += amount3;
            if (amount1 >= amount2){
                Champion champion = (Champion)selfFollower;
                champion.checkEvolution();
            }

            case 20://Dê dano a todas as unidades inimigas
            for (Follower opponent : opponentBoard.getCards()){
                opponent.takeDamage(amount1);
            }

            case 21://Efeito de transformação da Anivia em Ovonivia
            Anivia anivia = (Anivia)selfFollower;
            anivia.transform();

            case 22://Dê n de dano a um inimigo se ele tiver 0 de poder. Senão, deixe ele com 0 de poder nesta rodada.
            if (opponentBoard.getCards().isEmpty()){
                System.out.println("Não há nenhum alvo válido.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    //printar a board
                    ally = scan.nextInt();
                }
                else {
                    ally = opponentBoard.getPlayer().getRandomResult(opponentBoard.getCards().size());
                }
                if (opponentBoard.getCards().get(ally).temporaryPower == 0){
                    opponentBoard.getCards().get(ally).takeDamage(amount1);
                }
                else {
                    opponentBoard.getCards().get(ally).addTempBuff(-opponentBoard.getCards().get(ally).getTemporaryPower(), 0);
                }
            }

            case 23://Cura seu nexus em n e ganha uma gema de mana vazia.
            myBoard.getPlayer().addMaxMana(1);
            myBoard.getPlayer().healNexus(amount1);

            case 24://Dê N de dano em qualquer coisa.
        }
        scan.close();
    }

    public void checkTrigger(Trigger occurredTrigger, Board myBoard, Board opponentBoard, Follower follower, int amount){
        if (trigger == occurredTrigger){
            applyEffect(myBoard, opponentBoard, follower, amount);
        }
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
