package card;
import java.util.Scanner;

import game.Board;
import game.Game;
import game.Player;
import card.Follower;
import card.champion.Champion;
import card.champion.freljord.Anivia;
public class Effect implements Cloneable{
    
    Trigger trigger;
    CaseEffects effect;
    int amount1;
    int amount2;
    int ally;
    String cardName;
    Scanner scan;

    public Effect(CaseEffects effect, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        scan = new Scanner(System.in);
    }

    public Effect(CaseEffects effect, int amount1, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.amount1 = amount1;
        scan = new Scanner(System.in);
    }

    public Effect(CaseEffects effect, int amount1, int amount2, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.amount1 = amount1;
        this.amount2 = amount2;
        scan = new Scanner(System.in);
    }

    public Effect(CaseEffects effect, String cardName, Trigger trigger){
        this.trigger = trigger;
        this.effect = effect;
        this.cardName = cardName;
        scan = new Scanner(System.in);
    }

    private void applyEffect(game.Board myBoard, game.Board opponentBoard, Follower selfFollower) {
        Game game = Game.getGame(myBoard.getPlayer(), opponentBoard.getPlayer());
        Scanner scan = game.getScanner();

        switch(effect) {
            case BOARD_BUFF: // Dê +n/+m a todas as unidades aliadas evocadas.
            for (Follower follower : myBoard.getCards()) {
                follower.buff(amount1, amount2);
            }
            break;

            case SINGLE_TEMP_BUFF: // Dê +n/+m a uma unidade aliada nessa rodada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).addTempBuff(amount1, amount2);
            }
            break;

            case CREATE_CARD: //Compra uma carta específica do seu deck.
            myBoard.getPlayer().drawCard(1, cardName);
            break;

            case FULL_HEAL: // Cure inteiramente uma unidade aliada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Cure inteiramente uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).heal(0, true);
            }
            break;

            case DOUBLE_STATS: // Dobre o ataque e defesa de uma unidade aliada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).buff(myBoard.getCards().get(ally).getTemporaryPower(), myBoard.getCards().get(ally).getCurrentHealth());
            }
            break;

            case SINGLE_COMBAT: // Escolha um aliado e um oponente para um combate imediato.
            if (myBoard.getCards().isEmpty() || opponentBoard.getCards().isEmpty()){
                System.out.println("Não há alvos válidos.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    int opponent;
                    System.out.println("Escolha uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                    System.out.println("Escolha uma unidade inimiga.");
                    game.printPlayerBoard(opponentBoard.getPlayer());
                    opponent = scan.nextInt();
                    myBoard.getCards().get(ally).strike(opponentBoard.getCards().get(opponent), myBoard, opponentBoard);
                    opponentBoard.getCards().get(opponent).strike(myBoard.getCards().get(ally), opponentBoard, myBoard);
                }
                else {
                    myBoard.getCards().get(myBoard.getPlayer().getRandomResult(myBoard.getCards().size())).strike(opponentBoard.getCards().get(myBoard.getPlayer().getRandomResult(opponentBoard.getCards().size())), myBoard, opponentBoard);
                }
            }
            break;

            case ALLY_STRIKE_NEXUS: // Uma unidade evocada ataca o nexus do adversário.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).strike(opponentBoard.getPlayer(), myBoard);
            }
            break;

            case JUDGEMENT: // Um aliado golpeia todos os oponentes.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade aliada.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                };
                for (Follower follower : opponentBoard.getCards()) {
                    myBoard.getCards().get(ally).strike(follower, myBoard, opponentBoard);
                }
            }
            break;

            case DRAW_CARD: // Você compra uma carta.
            myBoard.getPlayer().drawCard(1);
            break;

            case ZERO_POWER: // Altera o poder de uma unidade para 0 nesta rodada.
            if (opponentBoard.getCards().isEmpty()){
                System.out.println("Não há nenhum alvo válido.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    game.printPlayerBoard(opponentBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = opponentBoard.getPlayer().getRandomResult(opponentBoard.getCards().size());
                }
                opponentBoard.getCards().get(ally).addTempBuff(-opponentBoard.getCards().get(ally).getTemporaryPower(), 0);
            }
            break;

            case PRISMATIC_BARRIER: // Cria uma barreira que anula o próximo dano que uma unidade aliada levaria. Dura uma rodada.
            if (myBoard.getCards().isEmpty()){
                System.out.println("Você não tem nenhum aliado.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    game.printPlayerBoard(myBoard.getPlayer());
                    ally = scan.nextInt();
                }
                else {
                    ally = myBoard.getPlayer().getRandomResult(myBoard.getCards().size());
                }
                myBoard.getCards().get(ally).addTrait(Trait.BARRIER);
            }
            break;

            case DAMAGE_NEXUS: //Golpeia o nexus do adversário para n pontos de dano.
            opponentBoard.getPlayer().takeDamage(amount1);
            break;
    
            case REMOVE_TEMP_BUFF://Efeito de buff temporário
            selfFollower.buff(-amount1, -amount2);
            selfFollower.getEffects().remove(this);
            break;

            case EVOLUTION://Evolução
            Champion champion = (Champion)selfFollower;
            champion.checkEvolution();
            break;
            
            
            case STRONGEST_ZERO_POWER: //Deixa o inimigo mais forte com 0 de poder nesta rodada.
            if (opponentBoard.getCards().size() != 0){
                Follower mostPowerful = opponentBoard.getCards().get(0);
                for (Follower follower : opponentBoard.getCards()){
                    if (follower.getTemporaryPower() > mostPowerful.getTemporaryPower()){
                        mostPowerful = follower;
                    }
                }
                mostPowerful.addTempBuff(-mostPowerful.getTemporaryPower(), 0);
            }
            break;
            
            case FULL_AOE: //Da n de dano a todas as unidades.
            for (Follower opponent : opponentBoard.getCards()){
                opponent.takeDamage(amount1);
            }
            for (Follower ally : myBoard.getCards()){
                ally.takeDamage(amount1);
            }
            break;
            
            case SELF_BUFF://Da +n/+m a si mesmo.
            selfFollower.buff(amount1, amount2);
            break;

            case AREA_TEMP_BUFF://Da +n/+m a todas as unidades aliadas nessa rodada.
            for (Follower ally : myBoard.getCards()){
                ally.addTempBuff(amount1, amount2);
            }
            break;

            case REMOVE_BARRIER://Remove barreira de si mesmo
            if (selfFollower.hasTrait(Trait.BARRIER)){
                selfFollower.takeDamage(1);
            }
            break;

            case DAMAGE_ANYTHING://Dê n de dano a qualquer coisa inimiga
            if (myBoard.getPlayer().isHuman()){
                System.out.println("Digite -1 para dar dano no nexus ou um índice para dar dano em uma unidade inimiga");
                game.printPlayerBoard(opponentBoard.getPlayer());
                ally = scan.nextInt();
            }
            else {
                ally = opponentBoard.getPlayer().getRandomResult(opponentBoard.getCards().size()+1)-1;
            }
            if (ally == -1){
                opponentBoard.getPlayer().takeDamage(amount1);
            }
            else {
                opponentBoard.getCards().get(ally).addTempBuff(-opponentBoard.getCards().get(ally).getTemporaryPower(), 0);
            }
            break;

            case OPPONENT_AOE://Dê dano a todas as unidades inimigas
            for (Follower opponent : opponentBoard.getCards()){
                opponent.takeDamage(amount1);
            }
            break;

            case ANIVIA_TRANSFORMATION://Efeito de transformação da Anivia em Ovonivia
            Anivia anivia = (Anivia)selfFollower;
            anivia.transform();
            break;

            case SHATTER://Dê n de dano a um inimigo se ele tiver 0 de poder. Senão, deixe ele com 0 de poder nesta rodada.
            if (opponentBoard.getCards().isEmpty()){
                System.out.println("Não há nenhum alvo válido.");
            }
            else {
                if (myBoard.getPlayer().isHuman()){
                    System.out.println("Escolha uma unidade.");
                    game.printPlayerBoard(myBoard.getPlayer());
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
            break;

            case CATALYST://Cura seu nexus em n e ganha uma gema de mana vazia.
            myBoard.getPlayer().addMaxMana(1);
            myBoard.getPlayer().healNexus(amount1);
            break;
        }
    }

    public void checkTrigger(Trigger occurredTrigger, Board myBoard, Board opponentBoard, Follower follower){
        if (trigger == occurredTrigger){
            if (occurredTrigger != Trigger.PLAY){
                System.out.println(String.format("%s ativou seu efeito!", follower.getName()));
            }
            boolean retry = true;
            while (retry){
                try {
                    applyEffect(myBoard, opponentBoard, follower);
                    retry = false;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Alvo inválido, tente de novo.");
                }
            }
        }
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public void closeScan(){
        scan.close();
    }
}
